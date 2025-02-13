package com.javachallenge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PointOfSale  {
    private static final String POINT_OF_SALE_FROM ="pointOfSaleFrom";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = POINT_OF_SALE_FROM, cascade =  CascadeType.REMOVE, orphanRemoval = true)
    @ToString.Exclude
    private List<Cost> costs = new ArrayList<>();

    public PointOfSale(String name) {
        this.name = name;
    }

    public PointOfSale(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
