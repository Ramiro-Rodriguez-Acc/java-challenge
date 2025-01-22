package com.javachallenge.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteService {
    private final Map<String, List<Integer>> routes= new HashMap();

    public void newLowerCost(int idx, int idy, int idNewConnection) {
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
        return Optional.ofNullable(routes.get(idx+"->"+idy)).orElse(new ArrayList<>());
    }

    public void update(int idx, int idy, int idNewConnection) {
        List<Integer> route = getRoute(idx,idy);

        int indexIdx = Integer.max(route.indexOf(idx),0);
        if (!route.isEmpty()){
            int indexIdy = route.indexOf(idy);
            route.subList(indexIdx,indexIdy+1).clear();
        }
        List<Integer> routeNewConnection = getRoute(idx,idNewConnection);

        if (routeNewConnection.isEmpty()){
            routeNewConnection.add(idx);
            routeNewConnection.add(idNewConnection);
        }

        route.addAll(indexIdx,routeNewConnection);
        route.add(idy);
        routes.put((idx+"->"+idy),route);
    }


    public void clearMap() {
        routes.clear();
    }
}
