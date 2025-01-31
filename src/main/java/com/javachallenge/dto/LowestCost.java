package com.javachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static com.javachallenge.utils.Constants.*;
@Data
public class LowestCost {
    @JsonProperty(COSTO)
    private int cost;
    @JsonProperty(RUTA)
    private List<String> route = new ArrayList<>();

    public LowestCost() {
    }


}
