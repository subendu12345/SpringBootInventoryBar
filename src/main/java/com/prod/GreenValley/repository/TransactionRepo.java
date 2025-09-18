package com.prod.GreenValley.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prod.GreenValley.DTO.CounterStockInDetail;
import com.prod.GreenValley.Entities.Transction;

public interface TransactionRepo extends JpaRepository<Transction, Long>{

    public List<Transction> findAllByOrderByIdDesc();


    String GET_TOTAL_VOL="""
            SELECT product_name as productName, sum(size) as totalCounterVolume  from transction_item where is_extra_item = false group by product_name;
        """;

    
    @Query(value = GET_TOTAL_VOL, nativeQuery = true)
    List<CounterStockInDetail> getTotalTransctionVolume();
    
}
