package com.javachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.javachallenge.utils.Constants.*;

public class DirectRoutes {
    @JsonProperty(PUNTO_DE_VENTA_ORIGEN)
    String origin;
    @JsonProperty(COSTO_POR_DESTINO)
    Map<String, Integer> routeCost;
    public DirectRoutes() {
        this.routeCost = new HashMap<>();
    }

    public Map<String, Integer> getRouteCost() {
        return routeCost;
    }

    public void setRouteCost(Map<String, Integer> routeCost) {
        this.routeCost = routeCost;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
