package com.prod.GreenValley.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.prod.GreenValley.DTO.PriceChartDTO;
import com.prod.GreenValley.service.PriceChartService;

@RequestMapping("/api")
@RestController
public class PriceChartRESTApi {
    @Autowired
    private PriceChartService priceChartService;

    @PostMapping("/pricechart/save")
    public String savePriceChart(@RequestBody List<PriceChartDTO> priceChartList){
        try {
            priceChartService.saveBulkPriceChart(priceChartList);
        } catch (Exception e) {
            return e.getMessage();
            // TODO: handle exception
        }
        return "success";
    }

    @GetMapping("/pricehart/get")
    public List<PriceChartDTO> findLimitedPricechart(){
        return priceChartService.findAllPriceChart();
    }

    @GetMapping("/pricehart/getall")
    public List<PriceChartDTO> getAllPriceChart(){
        return priceChartService.getAllPriceChart();
    }

    @GetMapping("/priceChart/search/{searchParam}")
    public List<PriceChartDTO> getSearchData(@PathVariable String searchParam){
        System.out.println("searchParam   "+ searchParam);
        return priceChartService.getSearchResult(searchParam);
    }

    @PutMapping("/pricechart/update/")
    public String updatePriceChart(@RequestBody PriceChartDTO priceChartDTO){
        try {
            priceChartService.updatePriceChart(priceChartDTO);
            return "success";
        } catch (Exception e) {
           return e.getMessage();
        }
    }

    @DeleteMapping("/pricechart/delete/{id}")
    public String deletePriceChartById(@PathVariable Long id){
        try {
            priceChartService.deletePriceChartById(id);
            return "success";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
}
