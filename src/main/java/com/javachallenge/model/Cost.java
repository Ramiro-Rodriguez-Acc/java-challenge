package com.javachallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.Min;

import static com.javachallenge.utils.Constants.*;


public class Cost {
    @JsonProperty(ID_A)
    private Integer idA;
    @JsonProperty(ID_B)
    private Integer idB;
    @JsonProperty(COSTO)
    @Min(value = 1, message = EL_COSTO_DEBE_SER_ENTERO_POSITIVO)
    private Integer cost;

    public Cost(Integer idA, Integer idB, Integer cost) {
        this.idA = idA;
        this.idB = idB;
        this.cost = cost;
    }

    public Integer getIdA() {
        return idA;
    }

    public void setIdA(Integer idA) {
        this.idA = idA;
    }

    public Integer getIdB() {
        return idB;
    }

    public void setIdB(Integer idB) {
        this.idB = idB;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
