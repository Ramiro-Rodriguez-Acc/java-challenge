package com.javachallenge.init;

import com.javachallenge.dto.CostDTO;
import com.javachallenge.dto.PointOfSaleCreateDTO;
import com.javachallenge.model.PointOfSale;

import com.javachallenge.service.CostService;
import com.javachallenge.service.PointOfSaleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInit implements CommandLineRunner {

    private final CostService costService;
    private final PointOfSaleService pointOfSaleService;

    public DataInit(CostService costService,  PointOfSaleService pointOfSaleService) {
        this.costService = costService;
        this.pointOfSaleService = pointOfSaleService;
    }

    @Override
    public void run(String... args)  {
        List<PointOfSale> pointOfSaleList = pointOfSaleService.getAllFromRepository();
       if (!pointOfSaleList.isEmpty()) {
            return;
        }
        initializeCache();
    }

    public void initializeCache()  {

        pointOfSaleService.add(new PointOfSaleCreateDTO(1, "CABA"));
        pointOfSaleService.add(new PointOfSaleCreateDTO(2, "GBA_1"));
        pointOfSaleService.add(new PointOfSaleCreateDTO(3, "GBA_2"));
        pointOfSaleService.add(new PointOfSaleCreateDTO(4, "Santa Fe"));
        pointOfSaleService.add(new PointOfSaleCreateDTO(5, "Cordoba"));
        pointOfSaleService.add(new PointOfSaleCreateDTO(6, "Misiones"));
        pointOfSaleService.add(new PointOfSaleCreateDTO(7, "Salta"));
        pointOfSaleService.add(new PointOfSaleCreateDTO(8, "Chubut"));
        pointOfSaleService.add(new PointOfSaleCreateDTO(9, "Santa Cruz"));
        pointOfSaleService.add(new PointOfSaleCreateDTO(10, "Catamarca"));



        costService.add(1, new CostDTO(2, 2));
        costService.add(1, new CostDTO(3, 3));
        costService.add(2, new CostDTO(3, 5));
        costService.add(2, new CostDTO(4, 10));
        costService.add(1, new CostDTO(4, 11));
        costService.add(4, new CostDTO(5, 5));
        costService.add(2, new CostDTO(5, 14));
        costService.add(6, new CostDTO(7, 32));
        costService.add(8, new CostDTO(9, 11));
        costService.add(10, new CostDTO(7, 5));
        costService.add(3, new CostDTO(8, 10));
        costService.add(5, new CostDTO(8, 30));
        costService.add(10, new CostDTO(5, 5));
        costService.add(4, new CostDTO(6, 6));
    }
}