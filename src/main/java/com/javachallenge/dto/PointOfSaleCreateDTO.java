package com.javachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.javachallenge.utils.Constants.NOMBRE;

@Data
@AllArgsConstructor
public class PointOfSaleCreateDTO {
    private Integer id;
    @JsonProperty(NOMBRE)
    private String name;


}
