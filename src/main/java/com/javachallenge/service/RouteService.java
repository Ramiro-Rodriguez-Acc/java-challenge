package com.javachallenge.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteService {
    private final Map<String, List<Integer>> routes= new HashMap<>();

    public void newLowerCostRoute(int idx, int idy, int idNewConnection) {
        if (idy==idNewConnection){
            newDirectCost(idx, idy);
        } else{
            update(idx, idy, idNewConnection);
        }

    }
   public void newDirectCost(int idx, int idy) {
       List<Integer> route = new ArrayList<>();
       route.add(idx);
       route.add(idy);
       routes.put(idx+"->"+idy,route);
   }

    public List<Integer> getRoute(int idx, int idy) {
        return Optional.ofNullable(routes.get(Integer.min(idx, idy) + "->" + Integer.max(idx, idy)))
                .map(ArrayList::new)
                .orElse(new ArrayList<>());    }

    public void update(int idx, int idy, int idNewConnection) {
        List<Integer> route = getRoute(idx,idy);

        int indexIdx = Math.max(route.indexOf(idx),0);
        int indexIdy = Math.max(route.indexOf(idy),0);
        if (!route.isEmpty()){
            route.subList(Math.min(indexIdx,indexIdy), Math.max(indexIdx,indexIdy)+1).clear();
        }
        List<Integer> routeNewConnection1 = getRoute(idx,idNewConnection);
        if (idx>idNewConnection){
            Collections.reverse(routeNewConnection1);
        }
        if (routeNewConnection1.isEmpty()){
            routeNewConnection1.add(idx);
            routeNewConnection1.add(idNewConnection);
        }
        List<Integer> routeNewConnection2 = getRoute(idNewConnection,idy);
        if (idy<idNewConnection){
            Collections.reverse(routeNewConnection2);
        }
        if (routeNewConnection2.isEmpty()){
            routeNewConnection2.add(idy);
        } else {
            routeNewConnection2.remove((Integer) idNewConnection);
        }

        route.addAll(Math.min(indexIdx,indexIdy) ,routeNewConnection1);
        route.addAll(routeNewConnection2);
        routes.put((idx+"->"+idy),route);
    }


    public void clearMap() {
        routes.clear();
    }
}
