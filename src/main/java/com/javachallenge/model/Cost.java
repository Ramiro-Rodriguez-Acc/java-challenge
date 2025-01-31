package com.javachallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.validation.constraints.Min;

import static com.javachallenge.utils.Constants.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cost {
    @JsonProperty(ID_A)
    private Integer idA;
    @JsonProperty(ID_B)
    private Integer idB;
    @JsonProperty(COSTO)
    private Integer cost;

}
