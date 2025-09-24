package com.prod.GreenValley.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prod.GreenValley.DTO.CounterStockInDetail;
import com.prod.GreenValley.Entities.Transction;
import com.prod.GreenValley.Entities.TransctionItem;
import com.prod.GreenValley.repository.TransactionRepo;
import com.prod.GreenValley.wrapper.TransactionRequest;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transctionRepository;

        /**
     * Maps the incoming DTO to JPA entities and persists them to the database.
     *
     * @param request The TransactionRequest DTO from the JSON payload.
     * @return The persisted Transction entity.
     */
    public Transction saveTransaction(TransactionRequest request) {
        // 1. Create a new Transction entity from the request DTO.
        Transction transction = new Transction();
        transction.setTableNo(request.getTableNo());
        transction.setTotalAmount(request.getTotalAmount());
        transction.setDiscountAmount(request.getDiscountAmount());
        transction.setDiscount(request.getDiscount());
        // The totalVolume is not in the JSON, so we will calculate it here.
        BigDecimal totalVolume = request.getItems().stream()
                .map(item -> new BigDecimal(item.getSize()).multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        transction.setTotalVolume(totalVolume);

        // 2. Persist the main Transction entity. This will generate an ID.
        // The @PrePersist method will automatically set the transctionDate.
        Transction savedTransction = transctionRepository.save(transction);

        // 3. Map each item from the request to a TransctionItem entity.
        List<TransctionItem> transctionItems = request.getItems().stream()
                .map(itemRequest -> {
                    TransctionItem transctionItem = new TransctionItem();
                    transctionItem.setProductName(itemRequest.getProductName());
                    transctionItem.setSize(itemRequest.getSize());
                    transctionItem.setExtraSize(itemRequest.getExtraSize());
                    transctionItem.setPrice(itemRequest.getPrice());
                    transctionItem.setQuantity(itemRequest.getQuantity());
                    transctionItem.setTransction(savedTransction); // Link to the parent transaction
                    transctionItem.setIsExtraItem(itemRequest.getIsExtraItem());
                    return transctionItem;
                }).collect(Collectors.toList());

        // 4. Set the items list on the saved transaction and save it again.
        // Due to cascade settings, this will save the child items as well.
        savedTransction.setItems(transctionItems);
        return transctionRepository.save(savedTransction);
    }

    /**
     * Retrieves all transactions from the database, eagerly fetching items.
     * @return A list of all Transction entities.
     */
    public List<Transction> getAllTransactions() {
        return transctionRepository.findAllByOrderByIdDesc();
    }

    public List<CounterStockInDetail> getTotalTranctionVolume(){
        return transctionRepository.getTotalTransctionVolume();
    }


    public List<Transction> filterTransctionByStartDateEndDate(LocalDate startDate, LocalDate endDate){
        if(startDate == null){
            return transctionRepository.filterTransctionByEndDate(endDate);
        }else{
            return transctionRepository.filterTransctionByStartEndDate(startDate, endDate);
        }
    }

    public List<Object> getTransctionReportByTimeFrame(LocalDate startDate, LocalDate endDate){
        return transctionRepository.getTransctionReportByTimeFrame(startDate, endDate);
    }
    
}
