package com.javachallenge.service;

import com.javachallenge.cache.CacheService;
import com.javachallenge.dto.DirectRoutes;
import com.javachallenge.dto.LowestCost;
import com.javachallenge.model.Cost;
import com.javachallenge.model.PointOfSale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.javachallenge.utils.Constants.*;

@Service
public class CostService {
    private final CacheService cache;
    private final PointOfSaleService pointOfSaleService;

    public CostService(CacheService cache, PointOfSaleService pointOfSaleService) {
                this.cache = cache;
                this.pointOfSaleService = pointOfSaleService;
                createMap();
    }

    public void createMap(){
        Map<Integer, Cost> costMap = new HashMap<>();
        cache.putMap(COST_MAP, costMap, Cost.class);
    }
    @Transactional
    public void add(int idA, int idB, int cost) throws Exception {
        validateCost(cost);
        cache.add(new Cost(idA, idB, cost), COST_MAP, Cost.class);
    }

    @Transactional
    public void delete(int idA, int idB) throws Exception {
        Map<Integer, Cost> costMap = cache.getMap(COST_MAP, Cost.class);
        deleteIfExist(costMap, idA, idB);
    }

    public void deletePointOfSale(int idA) throws Exception {
        Map<Integer, Cost> costMap = cache.getMap(COST_MAP, Cost.class);
        for (Map.Entry<Integer, Cost> entry : costMap.entrySet()) {
            Cost cost = entry.getValue();
            if (cost.getIdA() == idA || cost.getIdB() == idA) {
                cache.delete(entry.getKey(), COST_MAP, Cost.class);
            }
        }
    }

    public void deleteIfExist(Map<Integer, Cost> costMap, int idA, int idB ) throws Exception {
        for (Map.Entry<Integer, Cost> entry : costMap.entrySet()) {
            Cost cost = entry.getValue();
            if (cost.getIdA() == idA && cost.getIdB() == idB) {
                cache.delete(entry.getKey(), COST_MAP, Cost.class);
                return;
            }
        }
        throw new NoSuchElementException(ERROR_MESSAGE_COSTO_ENTRE_PUNTOS_NO_ENCONTRADO);
    }

    @Transactional
    public DirectRoutes getDirectRoutes(int id) throws Exception {
        Map<Integer, Cost> costMap = cache.getMap(COST_MAP, Cost.class);
        Map<Integer, PointOfSale> pointOfSaleMap = pointOfSaleService.getAll();

        DirectRoutes directRoutes = new DirectRoutes();
        Map<String, Integer> routeCost= new HashMap<>();
        directRoutes
                .setOrigin(Optional.ofNullable(pointOfSaleMap.get(id).getName())
                        .orElseThrow(() ->new NoSuchElementException(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO)));
        for (Map.Entry<Integer, Cost> entry : costMap.entrySet()) {
            Cost cost = entry.getValue();
            if (cost.getIdA() == id) {
                routeCost.put(pointOfSaleMap.get(cost.getIdB()).getName(), cost.getCost());
            }
            if (cost.getIdB() == id) {
                routeCost.put(pointOfSaleMap.get(cost.getIdA()).getName(), cost.getCost());
            }
        }
        if (routeCost.isEmpty()){
            throw new NoSuchElementException(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_TIENE_RUTA_ASOCIADA);
        }
        directRoutes.setRouteCost(routeCost);
        return directRoutes;
    }

    public void validateCost (int cost){
        if (cost <= 0){
            throw new IllegalArgumentException(EL_COSTO_DEBE_SER_ENTERO_POSITIVO);
        }
    }

    @Transactional(readOnly = true)
    public Map<Integer, Cost> getAll() throws Exception {
        return cache.getMap(COST_MAP, Cost.class);
    }
}
