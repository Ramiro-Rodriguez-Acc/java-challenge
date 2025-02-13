package com.javachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.javachallenge.utils.Constants.*;
@Data
public class LowestCost {
    private static final String RUTA= "ruta";

    @JsonProperty(COSTO)
    private int cost;
    @JsonProperty(RUTA)
    private List<String> route = new ArrayList<>();

    public LowestCost() {
    }


}
