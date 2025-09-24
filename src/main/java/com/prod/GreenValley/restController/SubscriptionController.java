package com.prod.GreenValley.restController;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.prod.GreenValley.DTO.SubscriptionDTO;
import com.prod.GreenValley.service.SubscriptionService;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;
    

    @PostMapping("/new/save")
    public ResponseEntity<Map<String, String>> createSubscription(@RequestBody SubscriptionDTO subscriptionDTO){
        Map<String, String> jsonResponse = new HashMap<>();
        String responseMessage = subscriptionService.createSubscription(subscriptionDTO);
        jsonResponse.put("message", responseMessage);
        return new ResponseEntity<>(jsonResponse, HttpStatus.CREATED);
    }
}
