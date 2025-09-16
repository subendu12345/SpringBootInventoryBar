package com.prod.GreenValley.restController;

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
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Map<String, String>>  createTransaction(@RequestBody TransactionRequest request) {
        Map<String, String> jsonResponse = new HashMap<>();
        try {
            transactionService.saveTransaction(request);
            jsonResponse.put("message", "sucess");
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
    
}
