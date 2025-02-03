package com.javachallenge.service;

import com.javachallenge.model.Cost;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.javachallenge.utils.Constants.*;

@Component
public class RouteCostService {

    private final int[][] lowerCost = new int[MAX_POINT_OF_SALE][MAX_POINT_OF_SALE];

    private final RouteService routeService;

    public RouteCostService(RouteService routeService) {
        this.routeService = routeService;
        initData();
    }
    public void initData(){
        for(int i = 1; i < MAX_POINT_OF_SALE; i++){
            for(int j = i; j < MAX_POINT_OF_SALE; j++){
                lowerCost[i][j] = MAX_COST;
            }
        }

    }
    public void newDirectCost(int idx, int idy, int cost, int length){
        newLowerCost(idx, idy, cost, length, idy);
    }

    public void newLowerCost(int idx, int idy, int cost, int length, int idConnection){
        if(lowerCost[idx][idy] > cost){
            lowerCost[idx][idy] = cost;

            routeService.newLowerCostRoute( idx,idy, idConnection);
            checkIndirectCosts(idx, idy, cost, length);
        }
    }

    public void checkIndirectCosts(int idx, int idy, int cost, int length) {
        for (int i = 1; i < MAX_POINT_OF_SALE; i++) {
            if (i != idx && i != idy) {
                int minIdx = Math.min(i, idx);
                int maxIdx = Math.max(i, idx);

                int minIdy = Math.min(i, idy);
                int maxIdy = Math.max(i, idy);

                int costViaIdx = lowerCost[minIdx][maxIdx] + cost;

                if (costViaIdx < lowerCost[minIdy][maxIdy]) {
                    newLowerCost(minIdy, maxIdy, costViaIdx, length, idx);
                }

                int costViaIdy = lowerCost[minIdy][maxIdy] + cost;

                if (costViaIdy < lowerCost[minIdx][maxIdx]) {
                    newLowerCost(minIdx, maxIdx, costViaIdy, length, idy);
                }
            }
        }}


            public int getLowerCost ( int idx, int idy){
                return lowerCost[idx][idy];
            }

            public void restart (Map<Integer, Cost> costMap){
                initData();
                routeService.clearMap();
                int length = costMap.size();
                for (Map.Entry<Integer, Cost> entry : costMap.entrySet()) {
                    Cost cost = entry.getValue();
                    newDirectCost(Integer.min(cost.getIdA(), cost.getIdB()), Integer.max(cost.getIdA(), cost.getIdB()), cost.getCost(), length);
                }
            }

    }
