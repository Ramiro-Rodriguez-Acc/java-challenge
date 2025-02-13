package com.javachallenge.service;

import com.javachallenge.dto.LowestCost;
import com.javachallenge.model.Cost;
import com.javachallenge.model.PointOfSale;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GraphServiceTest {
    @Mock
    private PointOfSaleService pointOfSaleService;

    @InjectMocks
    private GraphService graphService;


    @Test
    void testGetShortestPath_() {
        PointOfSale pointOfSaleA = new PointOfSale(1, "Ciudad A");
        PointOfSale pointOfSaleB = new PointOfSale(2, "Ciudad B");
        PointOfSale pointOfSaleC = new PointOfSale(3,"Ciudad C");
        Cost costAtoB = new Cost(pointOfSaleA, pointOfSaleB, 75);
        Cost costAtoC = new Cost(pointOfSaleA,pointOfSaleC, 200);
        Cost costBtoC = new Cost(pointOfSaleB, pointOfSaleC, 50);
        pointOfSaleB.setCosts(List.of(costBtoC));
        pointOfSaleA.setCosts(List.of(costAtoB, costAtoC));
        List<PointOfSale> mockPointOfSaleList = List.of(
               pointOfSaleA, pointOfSaleB, pointOfSaleC
        );

        when(pointOfSaleService.getAllFromRepository()).thenReturn(mockPointOfSaleList);

        LowestCost result = graphService.getShortestPath(1, 3);

        assertEquals(125, result.getCost());
        assertTrue(result.getRoute().contains("Ciudad B"));
    }


}
