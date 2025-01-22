package com.javachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.javachallenge.utils.Constants.*;

public class LowerCost {
    @JsonProperty(COSTO)
    private int cost;
    @JsonProperty(RUTA)
    private String route;

    public LowerCost() {
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
