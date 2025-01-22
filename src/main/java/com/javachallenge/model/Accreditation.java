package com.javachallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

import static com.javachallenge.utils.Constants.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getPointOfSaleId() {
        return pointOfSaleId;
    }

    public void setPointOfSaleId(int pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }

    public String getPointOfSaleName() {
        return pointOfSaleName;
    }

    public void setPointOfSaleName(String pointOfSaleName) {
        this.pointOfSaleName = pointOfSaleName;
    }
}
