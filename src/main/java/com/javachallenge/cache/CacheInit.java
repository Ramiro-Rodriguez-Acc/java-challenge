package com.javachallenge.cache;

import com.javachallenge.model.PointOfSale;
import com.javachallenge.service.CostService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.javachallenge.utils.Constants.*;

@Component
public class CacheInit implements CommandLineRunner {

    private final CacheService cache;
    private final CostService costService;
    public CacheInit(CacheService cache, CostService costService) {
        this.cache = cache;
        this.costService = costService;
    }

    @Override
    public void run(String... args) throws Exception {

        Map<Integer, Object> pointOfSaleMap = new HashMap<>();
        pointOfSaleMap.put(1, new PointOfSale("CABA"));
        pointOfSaleMap.put(2, new PointOfSale("GBA_1"));
        pointOfSaleMap.put(3, new PointOfSale("GBA_2"));
        pointOfSaleMap.put(4, new PointOfSale("Santa Fe"));
        pointOfSaleMap.put(5, new PointOfSale("Cordoba"));
        pointOfSaleMap.put(6, new PointOfSale("Misiones"));
        pointOfSaleMap.put(7, new PointOfSale("Salta"));
        pointOfSaleMap.put(8, new PointOfSale("Chubut"));
        pointOfSaleMap.put(9, new PointOfSale("Santa Cruz"));
        pointOfSaleMap.put(10, new PointOfSale("Catamarca"));

        cache.putMap(POINT_OF_SALE_MAP, pointOfSaleMap);
        costService.add(1, 2, 2);
        costService.add(1, 3, 3);
        costService.add(2, 3, 5);
        costService.add(2, 4, 10);
        costService.add(1, 4, 11);
        costService.add(4, 5, 5);
        costService.add(2, 5, 14);
        costService.add(6, 7, 32);
        costService.add(8, 9, 11);
        costService.add(10, 7, 5);
        costService.add(3, 8, 10);
        costService.add(5, 8, 30);
        costService.add(10, 5, 5);
        costService.add(4, 6, 6);



    }
}