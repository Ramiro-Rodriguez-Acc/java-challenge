package com.javachallenge.service;

import com.javachallenge.cache.CacheService;
import com.javachallenge.dto.DirectRoutes;
import com.javachallenge.model.Cost;
import com.javachallenge.model.PointOfSale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CostServiceTest {
    @Mock
    private CacheService cache;

    @Mock
    private RouteCostService routeCostService;

    @Mock
    private PointOfSaleService pointOfSaleService;

    @InjectMocks
    private CostService costService;

    private static final String COST_MAP = "costMap";
    private static final String POINT_OF_SALE_MAP = "pointOfSaleMap";
    private static final String ERROR_MESSAGE_COSTO_ENTRE_PUNTOS_NO_ENCONTRADO = "Costo entre puntos seleccionados no encontrado";
    private static final String ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO = "Punto de venta no encontrado";
    private static final String ERROR_MESSAGE_PUNTO_DE_VENTA_NO_TIENE_RUTA_ASOCIADA = "El Punto de Venta no tiene rutas asociadas";
    private static final String EL_COSTO_DEBE_SER_ENTERO_POSITIVO = "El costo debe ser entero positivo";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMap() {
        costService.validateCost(1);
        verify(cache, times(1)).putMap(eq(COST_MAP), any(Map.class));
    }

    @Test
    public void testAdd() throws Exception {
        doNothing().when(cache).add(any(Cost.class), eq(COST_MAP));
        when(cache.getLength(POINT_OF_SALE_MAP)).thenReturn(10);

        costService.add(1, 2, 100);

        verify(cache, times(1)).add(any(Cost.class), eq(COST_MAP));
        //verify(routeCostService, times(1)).newLowerCost(eq(1), eq(2), eq(100), eq(10));
    }

    @Test
    public void testAdd_InvalidCost() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            costService.add(1, 2, -100);
        });

        assertEquals(EL_COSTO_DEBE_SER_ENTERO_POSITIVO, exception.getMessage());
    }

    @Test
    public void testDelete() throws Exception {
        Map<Integer, Object> mockMap = new HashMap<>();
        Cost mockCost = new Cost(1, 2, 100);
        mockMap.put(1, mockCost);
        when(cache.getMap(COST_MAP)).thenReturn(mockMap);

        costService.delete(1, 2);

        verify(cache, times(1)).delete(1, COST_MAP);
    }

    @Test
    public void testDeleteIfExist() throws Exception {
        Map<Integer, Cost> mockMap = new HashMap<>();
        Cost mockCost = new Cost(1, 2, 100);
        mockMap.put(1, mockCost);

        costService.deleteIfExist(mockMap, 1, 2);


        verify(cache, times(1)).delete(1, COST_MAP);
    }

    @Test
    public void testDeleteIfExist_NotFound() {
        Map<Integer, Cost> mockMap = new HashMap<>();

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            costService.deleteIfExist(mockMap, 1, 2);
        });

        assertEquals(ERROR_MESSAGE_COSTO_ENTRE_PUNTOS_NO_ENCONTRADO, exception.getMessage());
    }

    @Test
    public void testGetDirectRoutes() throws Exception {
        Map<Integer, Object> mockCostMap = new HashMap<>();
        mockCostMap.put(1, new Cost(1, 2, 100));
        when(cache.getMap(COST_MAP)).thenReturn(mockCostMap);

        Map<Integer, PointOfSale> mockPointOfSaleMap = new HashMap<>();
        mockPointOfSaleMap.put(1, new PointOfSale("POS 1"));
        mockPointOfSaleMap.put(2, new PointOfSale("POS 2"));
        when(pointOfSaleService.getAll()).thenReturn(mockPointOfSaleMap);

        DirectRoutes result = costService.getDirectRoutes(1);

        assertNotNull(result);
        assertEquals("POS 1", result.getOrigin());
        assertTrue(result.getRouteCost().containsKey("POS 2"));
        assertEquals(100, result.getRouteCost().get("POS 2"));
    }

    @Test
    public void testGetDirectRoutes_NoRoutes() throws Exception {
        Map<Integer, Object> mockCostMap = new HashMap<>();
        when(cache.getMap(COST_MAP)).thenReturn(mockCostMap);

        Map<Integer, PointOfSale> mockPointOfSaleMap = new HashMap<>();
        mockPointOfSaleMap.put(1, new PointOfSale("POS 1"));
        when(pointOfSaleService.getAll()).thenReturn(mockPointOfSaleMap);

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            costService.getDirectRoutes(1);
        });

        assertEquals(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_TIENE_RUTA_ASOCIADA, exception.getMessage());
    }

    @Test
    public void testGetDirectRoutes_PointOfSaleNotFound() throws Exception {
        Map<Integer, Object> mockCostMap = new HashMap<>();
        when(cache.getMap(COST_MAP)).thenReturn(mockCostMap);

        Map<Integer, PointOfSale> mockPointOfSaleMap = new HashMap<>();
        mockPointOfSaleMap.put(1, new PointOfSale("POS 1"));
        when(pointOfSaleService.getAll()).thenReturn(mockPointOfSaleMap);

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            costService.getDirectRoutes(1);
        });

        assertEquals(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_TIENE_RUTA_ASOCIADA, exception.getMessage());
    }
}
