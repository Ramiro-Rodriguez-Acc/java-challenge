package com.javachallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javachallenge.model.Accreditation;
import lombok.Data;

import static com.javachallenge.utils.Constants.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
@Data
public class AccreditationGetDTO {
    private static final String PUNTO_DE_VENTA = "puntoDeVenta";
    private Integer id;
    @JsonProperty(IMPORTE)
    private Long amount;
    @JsonProperty(FECHA_DE_CREACION)
    private LocalDate creationDate;
    @JsonProperty(PUNTO_DE_VENTA_ID)
    private int pointOfSaleId;
    @JsonProperty(PUNTO_DE_VENTA_NOMBRE)
    private String pointOfSaleName;
    @JsonProperty(ENLACES)
    private Map<String, String> links= new HashMap<>();

    public AccreditationGetDTO(Accreditation accreditation) {
        this.id = accreditation.getId();
        this.amount = accreditation.getAmount();
        this.creationDate = accreditation.getCreationDate();
        this.pointOfSaleId = accreditation.getPointOfSaleId();
        this.pointOfSaleName = accreditation.getPointOfSaleName();
    }

    public void createLinks(){
        links.put(PUNTO_DE_VENTA, PUNTOS_DE_VENTA_PATH+"/"+pointOfSaleId);
    }
}
