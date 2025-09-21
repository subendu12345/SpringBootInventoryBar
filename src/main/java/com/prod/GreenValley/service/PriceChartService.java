package com.prod.GreenValley.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prod.GreenValley.DTO.PriceChartDTO;
import com.prod.GreenValley.Entities.PriceChart;
import com.prod.GreenValley.repository.PriceChartRepo;

@Service
public class PriceChartService {

    @Autowired
    private PriceChartRepo priceChartRepo;

    @Autowired 
    private PricaeBookService pricaeBookService;

    public void saveBulkPriceChart(List<PriceChartDTO> priceChartList){
        Map<String, PriceChart> nameSizeToMap = getExistingPriceChart();
        List<PriceChart> pcList = new ArrayList<>();
        for(PriceChartDTO pChartDTO : priceChartList){

            PriceChart pc = new PriceChart();
            PriceChart exPriceChart = isFindPriceChart(pChartDTO, nameSizeToMap);
            if(exPriceChart != null){
                pc = exPriceChart;
            }
            pc.setProductName(pChartDTO.getProductName());
            pc.setSize(pChartDTO.getSize());
            pc.setPrice(pChartDTO.getPrice());
            pc.setIsBeer(pChartDTO.getIsBeer());
            pcList.add(pc);
        }
        priceChartRepo.saveAll(pcList);
    }

    public void updatePriceChart(PriceChartDTO priceChartDTO){
        PriceChart priceChart = priceChartRepo.findById(priceChartDTO.getId()).orElse(null);
        priceChart.setPrice(priceChartDTO.getPrice());
        priceChart.setSize(priceChartDTO.getSize());
        priceChartRepo.save(priceChart);
    }


    public List<PriceChartDTO> findAllPriceChart(){
        List<PriceChartDTO> priceChartDTOs = new ArrayList<>();
        for (PriceChart pc : priceChartRepo.getFirstTenRecords()) {
            PriceChartDTO chartDTO = new PriceChartDTO();
            chartDTO.setId(pc.getId());
            chartDTO.setProductName(pc.getProductName());
            chartDTO.setPrice(pc.getPrice());
            chartDTO.setSize(pc.getSize());
            priceChartDTOs.add(chartDTO);
        }

        return priceChartDTOs;
    }


    public List<PriceChartDTO> getSearchResult(String searchStr){
        List<PriceChartDTO> priceChartDTOs = new ArrayList<>();
        for (PriceChart pc : priceChartRepo.findByNameContainingIgnoreCase(searchStr)) {
            priceChartDTOs.add(new PriceChartDTO(pc.getId(), pc.getProductName(), pc.getSize(), pc.getPrice()));
           
        }

        // for (Object[] row : pricaeBookService.getSearchResult(searchStr)) {
        //     Long id = (Long)row[0];
        //     String productName = (String) row[1];
        //     Integer size = (Integer) row[2];
        //     Double doubleValue = (Double) row[3];
        //     Integer price = doubleValue.intValue();
        //     priceChartDTOs.add(new PriceChartDTO(id, productName, size, price));
        // }
        return priceChartDTOs;
    }

    public void deletePriceChartById(Long id){
        priceChartRepo.deleteById(id);;
    }
    

    private PriceChart isFindPriceChart(PriceChartDTO pc, Map<String, PriceChart> nameSizeToMap ){
        String key = pc.getProductName()+pc.getSize();

        if(nameSizeToMap.size() > 0 && nameSizeToMap.get(key) !=null){
            return nameSizeToMap.get(key);
        }

        return null;
    }

    private Map<String, PriceChart> getExistingPriceChart(){
        Map<String, PriceChart> nameSizeToMap = new HashMap<>();
        for (PriceChart pc : priceChartRepo.findAll()) {
            String key = pc.getProductName()+pc.getSize();
            nameSizeToMap.put(key, pc);
        }

        return nameSizeToMap;
    }
}
