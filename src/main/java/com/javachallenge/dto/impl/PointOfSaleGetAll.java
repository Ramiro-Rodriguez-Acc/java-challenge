package com.javachallenge.dto.impl;

import com.javachallenge.dto.PointOfSaleDTO;
import com.javachallenge.model.PointOfSale;

import static com.javachallenge.utils.Constants.*;

public class PointOfSaleGetAll extends PointOfSaleDTO {
    private final static String OBTENER_RUTAS_DIRECTAS = "Obtener Rutas directas";

    public PointOfSaleGetAll(Integer id, String name) {
        super(id, name);
    }
    @Override
    public void createLinks(PointOfSale pointOfSale) {
        addLink(OBTENER_RUTAS_DIRECTAS, PUNTOS_DE_VENTA_PATH+"/"+pointOfSale.getId()+RUTAS_DIRECTAS_RESOURCE);
    }


}
