package com.javachallenge.service;

import com.javachallenge.cache.CacheService;
import com.javachallenge.dto.DirectRoutes;
import com.javachallenge.dto.LowerCost;
import com.javachallenge.model.Cost;
import com.javachallenge.model.PointOfSale;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.javachallenge.utils.Constants.*;

@Service
public class CostService {
    private final CacheService cache;
    private final RouteCostService routeCostService;
    private final PointOfSaleService pointOfSaleService;
    private final RouteService routeService;

    public CostService(CacheService cache, RouteCostService routeCostService, PointOfSaleService pointOfSaleService, RouteService routeService) {
                this.cache = cache;
                this.routeCostService = routeCostService;
                this.pointOfSaleService = pointOfSaleService;
                this.routeService = routeService;
                createMap();
    }
    public void createMap(){
        Map<Integer, Object> costMap = new HashMap<>();
        cache.putMap(COST_MAP, costMap);
    }
    public void add(int idA, int idB, int cost) throws Exception {
        validateCost(cost);
        int length = cache.getLength(POINT_OF_SALE_MAP);
        cache.add(new Cost(idA, idB, cost), COST_MAP);
        routeCostService.newDirectCost(Integer.min(idA, idB), Integer.max(idA, idB), cost, length);
    }


    public void delete(int idA, int idB) throws Exception {
        Map<Integer, Cost> costMap = cache.getMap(COST_MAP);
        deleteIfExist(costMap, idA, idB);

    }

    public void deleteIfExist(Map<Integer, Cost> costMap, int idA, int idB ) throws Exception {

        for (Map.Entry<Integer, Cost> entry : costMap.entrySet()) {
            Cost cost = entry.getValue();
            if (cost.getIdA() == idA && cost.getIdB() == idB) {
                int amount = cost.getCost();
                cache.delete(entry.getKey(), COST_MAP);
                Map<Integer, Cost> newCostMap = cache.getMap(COST_MAP);
                if (amount == routeCostService.getLowerCost(Integer.min(idA, idB), Integer.max(idA, idB))) {
                    routeCostService.restart(newCostMap);
                }
                return;
            }
        }
        throw new NoSuchElementException(ERROR_MESSAGE_COSTO_ENTRE_PUNTOS_NO_ENCONTRADO);
    }

    public DirectRoutes getDirectRoutes(int id) throws Exception {
        Map<Integer, Cost> costMap = cache.getMap(COST_MAP);
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


    public LowerCost getLowerCost(int idA, int idB) throws Exception {
        LowerCost lowerCost = new LowerCost();
        Map<Integer, PointOfSale> pointOfSaleMap = cache.getMap(POINT_OF_SALE_MAP);
        List<Integer> route;
        StringBuffer sb = new StringBuffer();
        if (idA < idB){
            lowerCost.setCost(routeCostService.getLowerCost(idA, idB));
            route = routeService.getRoute(idA, idB);
            for (int i = 0; i < route.size(); i++) {
                sb.append(pointOfSaleMap.get(route.get(i)).getName());
                sb.append(" -> ");
            }
            sb.delete(sb.length()-3, sb.length());
            lowerCost.setRoute(sb.toString());
            return lowerCost;
        }

        lowerCost.setCost(routeCostService.getLowerCost(idA, idB));
        route = routeService.getRoute(idA, idB);
        for (int i = route.size()-1; i >= 0; i--) {
            sb.append(pointOfSaleMap.get(route.get(i)).getName());
            sb.append(" -> ");
        }
        sb.delete(sb.length()-2, sb.length());
        lowerCost.setRoute(sb.toString());
        return lowerCost;
    }
}
