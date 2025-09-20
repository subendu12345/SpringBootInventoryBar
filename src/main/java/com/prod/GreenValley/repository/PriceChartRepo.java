package com.prod.GreenValley.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prod.GreenValley.Entities.PriceChart;

public interface PriceChartRepo extends JpaRepository<PriceChart, Long> {
    
    @Query("SELECT pc FROM PriceChart pc WHERE lower(pc.productName) LIKE lower(concat('%', :name, '%'))")
    List<PriceChart> findByNameContainingIgnoreCase(String name);


    @Query(value = "SELECT * FROM price_chart", nativeQuery = true)
    List<PriceChart> getFirstTenRecords();

}
