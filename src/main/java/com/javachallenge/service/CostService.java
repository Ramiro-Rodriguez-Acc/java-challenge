package com.javachallenge.service;

import com.javachallenge.dto.CostDTO;
import com.javachallenge.dto.impl.PointOfSaleGetOne;
import com.javachallenge.model.Cost;
import com.javachallenge.model.PointOfSale;
import com.javachallenge.repository.CostRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.javachallenge.utils.Constants.*;


@Service
public class CostService {

    private static final String EL_COSTO_DEBE_SER_ENTERO_POSITIVO ="El costo debe ser entero positivo";
    private static final String ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO = "Costo no encontrado";
    private final PointOfSaleService pointOfSaleService;
    private final CostRepository repository;

    public CostService( PointOfSaleService pointOfSaleService, CostRepository repository) {
                this.pointOfSaleService = pointOfSaleService;
                this.repository = repository;
    }

    @Transactional
    public PointOfSaleGetOne add(int idFrom, CostDTO costDTO)  {
        validateCost(costDTO.getCost());
        Cost cost = repository.save(new Cost(pointOfSaleService.getFromRepository(idFrom), pointOfSaleService.getFromRepository(costDTO.getIdTo()), costDTO.getCost()));
        return pointOfSaleService.addCost(idFrom, cost);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = POINT_OF_SALE_LIST, allEntries = true),
            @CacheEvict(value = POINT_OF_SALE, key = "#idFrom"),
            @CacheEvict(value= GRAPH, allEntries = true)
    })
    public void delete(int idFrom, int idTo)  {
        PointOfSale pointOfSaleFrom = pointOfSaleService.getFromRepository(idFrom);
        PointOfSale pointOfSaleTo = pointOfSaleService.getFromRepository(idTo);
        Cost cost = repository.findCostByPointOfSaleFromAndPointOfSaleTo(pointOfSaleFrom,pointOfSaleTo)
                .orElseThrow(() -> new NoSuchElementException(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO));

        repository.delete(cost);

    }

    public void validateCost (int cost){
        if (cost <= 0){
            throw new IllegalArgumentException(EL_COSTO_DEBE_SER_ENTERO_POSITIVO);
        }
    }


}
