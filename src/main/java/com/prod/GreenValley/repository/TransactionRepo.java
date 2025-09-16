package com.prod.GreenValley.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.prod.GreenValley.Entities.Transction;

public interface TransactionRepo extends JpaRepository<Transction, Long>{
    
}
