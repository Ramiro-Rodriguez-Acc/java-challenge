package com.javachallenge.repository;

import com.javachallenge.model.Cost;
import com.javachallenge.model.PointOfSale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CostRepository extends JpaRepository<Cost, Integer> {
     Optional<Cost> findCostByPointOfSaleFromAndPointOfSaleTo(PointOfSale pointOfSaleFrom, PointOfSale pointOfSaleTo);
}
