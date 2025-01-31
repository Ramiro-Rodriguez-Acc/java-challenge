package com.javachallenge.service;

import com.javachallenge.cache.CacheService;
import com.javachallenge.model.PointOfSale;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.javachallenge.utils.Constants.*;

@Service
public class PointOfSaleService {

    private final CacheService cache;

    public PointOfSaleService(CacheService cache)    {
        this.cache = cache;
    }

    public Map<Integer, PointOfSale> getAll() throws Exception {
        return cache.getMap(POINT_OF_SALE_MAP, PointOfSale.class);
    }

    @Transactional(readOnly = true)
    public PointOfSale get(int id) throws Exception {
        Map<Integer, PointOfSale> pointOfSaleMap = cache.getMap(POINT_OF_SALE_MAP, PointOfSale.class);
        return Optional.ofNullable(pointOfSaleMap.get(id))
                .orElseThrow(() ->new NoSuchElementException(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO));
    }
    @Transactional
    public void add(String name) throws Exception {
       cache.add(new PointOfSale(name), POINT_OF_SALE_MAP, PointOfSale.class);
    }

    @Transactional
    public void update(int id, String name) throws Exception {
        PointOfSale pointOfSale = get(id);
        pointOfSale.setName(name);
        cache.update(id, pointOfSale,POINT_OF_SALE_MAP, PointOfSale.class);
    }

    @Transactional
    public void delete(int id) throws Exception {
        cache.delete(id, POINT_OF_SALE_MAP, PointOfSale.class);
    }
}
