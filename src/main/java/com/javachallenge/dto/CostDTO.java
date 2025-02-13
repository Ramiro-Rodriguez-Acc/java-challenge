package com.javachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.javachallenge.utils.Constants.COSTO;
import static com.javachallenge.utils.Constants.ID_HASTA;


@Data
    @AllArgsConstructor
    public class CostDTO {


        @JsonProperty(ID_HASTA)
        private Integer idTo;
        @JsonProperty(COSTO)
        private Integer cost;

    }


