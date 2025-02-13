package com.javachallenge.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.javachallenge.model.PointOfSale;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

import static com.javachallenge.utils.Constants.*;

@Data
public abstract class PointOfSaleDTO {

    private Integer id;
    @JsonProperty(NOMBRE)
    private String name;
    @JsonProperty(ENLACES)
    private Map<String, String> links= new HashMap<>();

    public PointOfSaleDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void createLinks(PointOfSale pointOfSale);
    public void addLink(String description, String path ){
        links.put(description, path);
    }


}
