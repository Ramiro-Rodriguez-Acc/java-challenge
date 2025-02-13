package com.javachallenge.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cost {
    private static final String POINT_OF_SALE_TO_ID ="point_of_sale_to_id";
    private static final String POINT_OF_SALE_FROM_ID ="point_of_sale_from_id";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = POINT_OF_SALE_FROM_ID, nullable = false)
    @ToString.Exclude
    private PointOfSale pointOfSaleFrom;

    @ManyToOne
    @JoinColumn(name = POINT_OF_SALE_TO_ID, nullable = false)
    @ToString.Exclude
    private PointOfSale pointOfSaleTo;
    private Integer cost;

    public Cost(PointOfSale pointOfSaleFrom, PointOfSale pointOfSaleTo, Integer cost) {
        this.pointOfSaleFrom = pointOfSaleFrom;
        this.pointOfSaleTo = pointOfSaleTo;
        this.cost = cost;
    }
}
