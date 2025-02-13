package com.javachallenge.dto.impl;

import com.javachallenge.dto.CostDTO;
import com.javachallenge.dto.PointOfSaleDTO;
import com.javachallenge.model.Cost;
import com.javachallenge.model.PointOfSale;


import java.util.ArrayList;
import java.util.List;

import static com.javachallenge.utils.Constants.*;

public class PointOfSaleGetOne extends PointOfSaleDTO {
 private final static String OBTENER_RUTA_MAS_CORTA = "Obtener ruta mas corta";
 private final static String OBTENER_TODOS_LOS_PUNTOS_DE_VENTA = "Obtener todos los puntos de venta";

 private  List<CostDTO> costs = new ArrayList<>();
    public PointOfSaleGetOne(Integer id, String name) {
        super(id, name);
    }

    public PointOfSaleGetOne(Integer id, String name, Cost cost) {
        super(id, name);
        this.costs.add(new CostDTO(cost.getPointOfSaleTo().getId(), cost.getCost()));
    }

    public List<CostDTO> getCosts() {
        return costs;
    }

    public void setCosts(List<CostDTO> costs) {
        this.costs = costs;
    }

    @Override
    public void createLinks(PointOfSale pointOfSale) {
        addLink(OBTENER_TODOS_LOS_PUNTOS_DE_VENTA, PUNTOS_DE_VENTA_PATH);
        List<Cost> costs = pointOfSale.getCosts();
        if (!costs.isEmpty()){
            addLink(OBTENER_RUTA_MAS_CORTA, PUNTOS_DE_VENTA_PATH+"/"+pointOfSale.getId()+RUTA_MENOR_COSTO_RESOURCE+"/"+costs.getFirst().getPointOfSaleTo().getId());
        }
    }
}
