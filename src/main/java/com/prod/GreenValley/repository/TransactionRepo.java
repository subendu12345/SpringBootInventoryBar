package com.prod.GreenValley.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prod.GreenValley.DTO.CounterStockInDetail;
import com.prod.GreenValley.Entities.Transction;

public interface TransactionRepo extends JpaRepository<Transction, Long>{

    public List<Transction> findAllByOrderByIdDesc();


    @Query("SELECT DISTINCT t FROM Transction t JOIN FETCH t.items ti WHERE t.transctionDate >= :starDate AND t.transctionDate <= :endDate ORDER BY ti.id DESC")
    public List<Transction> filterTransctionByStartEndDate(LocalDate starDate, LocalDate endDate);



    @Query("SELECT DISTINCT t FROM Transction t JOIN FETCH t.items ti WHERE t.transctionDate <= :endDate ORDER BY ti.id DESC")
    public List<Transction> filterTransctionByEndDate(LocalDate endDate);


    String GET_TOTAL_VOL="""
            SELECT product_name as productName, sum(size * quantity) as totalCounterVolume  from transction_item where is_extra_item = false group by product_name;
        """;

    
    @Query(value = GET_TOTAL_VOL, nativeQuery = true)
    List<CounterStockInDetail> getTotalTransctionVolume();

    String GET_TRANS_REPORT = """
        SELECT t.transction_date, product_name, size, quantity, price, sum(size * quantity) as totaVolumeSale, SUM(price*quantity) as totalSalePrice  
        FROM transction_item ti inner join transction as t on t.id = ti.transction_id
        WHERE is_extra_item = false
        AND t.transction_date >= :startDate
        AND t.transction_date <= :endDate
        group by t.transction_date, product_name, size, quantity, price;
            """;

    @Query(value = GET_TRANS_REPORT, nativeQuery = true)
    List<Object> getTransctionReportByTimeFrame(LocalDate startDate, LocalDate endDate);
    
}
