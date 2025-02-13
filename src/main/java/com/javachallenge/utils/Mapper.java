package com.javachallenge.utils;

import com.javachallenge.dto.CostDTO;
import com.javachallenge.dto.PointOfSaleDTO;
import com.javachallenge.dto.impl.PointOfSaleGetAll;
import com.javachallenge.dto.impl.PointOfSaleGetOne;
import com.javachallenge.model.PointOfSale;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    public PointOfSaleDTO PointOfSaleToGetDto(PointOfSale pointOfSale, boolean getAll){
        PointOfSaleDTO pointOfSaleDTO;
        if (getAll){
            pointOfSaleDTO = new PointOfSaleGetAll(pointOfSale.getId(), pointOfSale.getName());
        } else{
            PointOfSaleGetOne pointOfSaleGetOne =  new PointOfSaleGetOne(pointOfSale.getId(), pointOfSale.getName());
            pointOfSaleGetOne.setCosts(pointOfSale.getCosts().stream().map(c-> new CostDTO(c.getPointOfSaleTo().getId(), c.getCost())).toList());
            pointOfSaleDTO = pointOfSaleGetOne;
        }

        pointOfSaleDTO.createLinks(pointOfSale);
        return pointOfSaleDTO;
    }


}
