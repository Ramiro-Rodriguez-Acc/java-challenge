package com.javachallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import static com.javachallenge.utils.Constants.NOMBRE;

public class PointOfSale  {


    @JsonProperty(NOMBRE)
    private String name;

    public PointOfSale( String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
