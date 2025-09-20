package com.prod.GreenValley.restController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prod.GreenValley.DTO.CounterStockInDetail;
import com.prod.GreenValley.Entities.Transction;
import com.prod.GreenValley.Entities.TransctionItem;
import com.prod.GreenValley.service.TransactionService;
import com.prod.GreenValley.wrapper.TransactionItemRequest;
import com.prod.GreenValley.wrapper.TransactionRequest;

@RestController
@RequestMapping("/api/transction")
public class TransctionController {
    @Autowired
    private TransactionService transactionService;
    
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>>  createTransaction(@RequestBody TransactionRequest request) {
        Map<String, Object> jsonResponse = new HashMap<>();
        try {
            TransactionRequest transactionRequest = getTransctionObject(transactionService.saveTransaction(request));
            jsonResponse.put("message", "sucess");
            jsonResponse.put("trans", transactionRequest);
        } catch (Exception e) {
            jsonResponse.put("message", e.getMessage());
        }
        return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
    }


    /**
     * Endpoint to retrieve all transactions.
     * @return A ResponseEntity containing a list of all transactions.
     */
    @GetMapping("/all")
    public ResponseEntity<List<TransactionRequest>> getAllTransactions() {
        List<TransactionRequest> transactionRequests = new ArrayList<>();


        List<Transction> transactions = transactionService.getAllTransactions();
        for (Transction transction : transactions) {
            transactionRequests.add(getTransctionObject(transction));
            
        }

        System.out.println("=====> "+ transactionRequests.size());
        return new ResponseEntity<>(transactionRequests, HttpStatus.OK);
    }

    private TransactionRequest getTransctionObject(Transction transction){
        TransactionRequest trns = new TransactionRequest();
        List<TransactionItemRequest> itemRequests = new ArrayList<>();
        for (TransctionItem  transctionItem : transction.getItems()) {
            System.out.println("isExtra ---------------------------------- "+transctionItem.getIsExtraItem());
            TransactionItemRequest itemRequest = new TransactionItemRequest();
            itemRequest.setTransItemId(transctionItem.getId());
            itemRequest.setIsExtraItem(transctionItem.getIsExtraItem());
            itemRequest.setPrice(transctionItem.getPrice());
            itemRequest.setProductName(transctionItem.getProductName());
            itemRequest.setQuantity(transctionItem.getQuantity());
            itemRequest.setSize(transctionItem.getSize());
            itemRequest.setExtraSize(transctionItem.getExtraSize());
            itemRequests.add(itemRequest);

        }
        trns.setTransId(transction.getId());
        trns.setDiscount(transction.getDiscount());
        trns.setDiscountAmount(transction.getDiscountAmount());
        trns.setTableNo(transction.getTableNo());
        trns.setTotalAmount(transction.getTotalAmount());
        trns.setItems(itemRequests);
        return trns;
    }

    @GetMapping("/get/transction-volume")
    public List<CounterStockInDetail> getTransctionVolume(){
        return transactionService.getTotalTranctionVolume();
    }

    @GetMapping("/get/detail/date")
    public List<TransactionRequest> getDataByStartDateEndDate(
        @RequestParam("startDate") LocalDate startDate,
        @RequestParam("endDate") LocalDate endDate){
            System.out.println("=================================================sDate "+startDate+" endDate "+ endDate);
        List<TransactionRequest> transactionRequests = new ArrayList<>();

        List<Transction> transactions = transactionService.filterTransctionByStartDateEndDate(startDate, endDate);
        for (Transction transction : transactions) {
            transactionRequests.add(getTransctionObject(transction));
        }

        return transactionRequests;

    }


    
    @GetMapping("/get/detail/end-date")
    public List<TransactionRequest> getDataByEndDate(
        @RequestParam("endDate") LocalDate endDate){
        List<TransactionRequest> transactionRequests = new ArrayList<>();

        List<Transction> transactions = transactionService.filterTransctionByStartDateEndDate(null, endDate);
        for (Transction transction : transactions) {
            transactionRequests.add(getTransctionObject(transction));
        }

        return transactionRequests;

    }
    
}
