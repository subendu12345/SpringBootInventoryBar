package com.prod.GreenValley.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prod.GreenValley.Entities.Subscription;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long>{
    
}
