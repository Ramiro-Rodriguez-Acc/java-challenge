package com.javachallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

import static com.javachallenge.utils.Constants.*;
@Data
@Entity
public class Accreditation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(ID)
    private Integer id;
    @JsonProperty(IMPORTE)
    private int amount;
    @JsonProperty(FECHA_DE_CREACION)
    private LocalDate creationDate;
    @JsonProperty(PUNTO_DE_VENTA_ID)
    private int pointOfSaleId;
    @JsonProperty(PUNTO_DE_VENTA_NOMBRE)
    private String pointOfSaleName;

    public Accreditation() {
        this.creationDate = LocalDate.now();
    }

    public Accreditation(int amount, int pointOfSaleId, String pointOfSaleName) {
        this.amount = amount;
        this.pointOfSaleId = pointOfSaleId;
        this.pointOfSaleName = pointOfSaleName;
        this.creationDate = LocalDate.now();
    }


}
