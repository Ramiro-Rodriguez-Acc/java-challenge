package com.javachallenge.service;

import com.javachallenge.cache.CacheService;
import com.javachallenge.model.PointOfSale;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


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
        return cache.getMap(POINT_OF_SALE_MAP);
    }

    public PointOfSale get(int id) throws Exception {
        Map<Integer, PointOfSale> pointOfSaleMap = cache.getMap(POINT_OF_SALE_MAP);
        return Optional.ofNullable(pointOfSaleMap.get(id))
                .orElseThrow(() ->new NoSuchElementException(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO));
    }

    public void add(String name) throws Exception {
       cache.add(new PointOfSale(name), POINT_OF_SALE_MAP);
    }

    public void update(int id, String name) throws Exception {
        get(id).setName(name);
    }

    public void delete(int id) throws Exception {
        cache.delete(id, POINT_OF_SALE_MAP);
    }
}
