package com.javachallenge.service;


import com.javachallenge.dto.CostDTO;
import com.javachallenge.dto.impl.PointOfSaleGetOne;
import com.javachallenge.model.Cost;
import com.javachallenge.model.PointOfSale;
import com.javachallenge.repository.CostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CostServiceTest {

    @Mock
    private PointOfSaleService pointOfSaleService;

    @Mock
    private CostRepository repository;

    @InjectMocks
    private CostService costService;

    private PointOfSale pointOfSale;
    private PointOfSale pointOfSaleB;

    private CostDTO costDTO;
    private Cost cost;

    @BeforeEach
    void setUp() {
        pointOfSale = new PointOfSale(1, "Ciudad A");
        costDTO = new CostDTO(2, 100);

        pointOfSaleB = new PointOfSale(2, "Ciudad B");
        cost = new Cost(pointOfSale, pointOfSaleB, 100);
    }

    @Test
    void add_ShouldAddCostAndReturnPointOfSale() {
        Cost costCreate = new Cost(pointOfSale, pointOfSaleB, 100);;
        when(repository.save(any(Cost.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(pointOfSaleService.addCost(eq(1), any(Cost.class)))
                .thenReturn(new PointOfSaleGetOne(1,"Ciudad A"));

        costService.add(1, costDTO);

        verify(repository, times(1)).save(any(Cost.class));
        verify(pointOfSaleService, times(1)).addCost(eq(1), any(Cost.class));
    }

    @Test
    void delete_ShouldDeleteCostFromPointOfSale() {
        pointOfSale.setCosts(List.of(cost));
        int idFrom = 1;
        int idTo = 2;
        when(pointOfSaleService.getFromRepository(idFrom)).thenReturn(pointOfSale);
        when(pointOfSaleService.getFromRepository(idTo)).thenReturn(pointOfSaleB);
        when(repository.findCostByPointOfSaleFromAndPointOfSaleTo(pointOfSale,pointOfSaleB))
                .thenReturn(Optional.ofNullable(cost));
        costService.delete(idFrom, idTo);

        verify(repository, times(1)).delete(cost);
    }
}