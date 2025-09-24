package com.prod.GreenValley.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prod.GreenValley.DTO.SubscriptionDTO;
import com.prod.GreenValley.Entities.Subscription;
import com.prod.GreenValley.repository.SubscriptionRepo;

@Service
public class SubscriptionService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SubscriptionRepo subscriptionRepo;

    public String createSubscription(SubscriptionDTO subscriptionDTO) {
        try {
            Subscription subscription = new Subscription();
            subscription.setAdminKey(passwordEncoder.encode(subscriptionDTO.getAdminKey()));
            subscription.setStartDate(subscriptionDTO.getStartDate());
            subscription.setEndDate(subscription.getEndDate());
            subscription.setIsActive(subscriptionDTO.getIsActive());

            // insert subscription data.
            subscriptionRepo.save(subscription);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "success";
    }

    public String updateSubscriptionData(SubscriptionDTO subscriptionDTO) {
        try {
            Subscription subscription = subscriptionRepo.findById(subscriptionDTO.getId()).orElse(null);
            if (subscription != null) {
                if (isMatchAdminKey(subscriptionDTO.getAdminKey(), subscription)) {
                    subscription.setStartDate(subscriptionDTO.getStartDate());
                    subscription.setEndDate(subscriptionDTO.getEndDate());
                    subscription.setIsActive(subscriptionDTO.getIsActive());
                    subscriptionRepo.save(subscription);
                } else {
                    return "Admin key not matching";
                }
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return "success";

    }

    private Boolean isMatchAdminKey(String adminKey, Subscription subscription) {
        return passwordEncoder.matches(adminKey, subscription.getAdminKey());
    }

}
