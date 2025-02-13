package com.javachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.javachallenge.utils.Constants.IMPORTE;
import static com.javachallenge.utils.Constants.PUNTO_DE_VENTA_ID;

@Data
public class AccreditationCreateDTO {
    @JsonProperty(IMPORTE)
    private Long amount;
    @JsonProperty(PUNTO_DE_VENTA_ID)
    private int pointOfSaleId;
}
