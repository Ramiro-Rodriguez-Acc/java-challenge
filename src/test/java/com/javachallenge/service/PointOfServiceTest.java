package com.javachallenge.service;

import com.javachallenge.cache.CacheService;
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
import static org.mockito.Mockito.*;

public class PointOfServiceTest {
  /*  @Mock
    private CacheService cache;

    @InjectMocks
    private PointOfSaleService pointOfSaleService;

    private static final String POINT_OF_SALE_MAP = "pointOfSaleMap";
    private static final String ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO = "Punto de venta no encontrado";

    private Map<Integer, PointOfSale> mockMap;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMap = new HashMap<>();
        mockMap.put(1, new PointOfSale("POS 1"));
        mockMap.put(2, new PointOfSale("POS 2"));
        doReturn(mockMap).when(cache).getMap(POINT_OF_SALE_MAP);
    }

    @Test
    public void testGetAll() throws Exception {
        Map<Integer, PointOfSale> result = pointOfSaleService.getAll();

        assertEquals(mockMap, result);
        assertEquals(2, result.size());
        assertTrue(result.containsKey(1));
        assertTrue(result.containsKey(2));
    }

    @Test
    public void testGet() throws Exception {
        PointOfSale result = pointOfSaleService.get(1);

        assertNotNull(result);
        assertEquals("POS 1", result.getName());
        verify(cache, times(1)).getMap(POINT_OF_SALE_MAP);
    }

    @Test
    public void testGet_NotFound() throws Exception {
        Exception exception = assertThrows(NoSuchElementException.class,
                () -> pointOfSaleService.get(3));

        assertEquals(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO, exception.getMessage());
        verify(cache, times(1)).getMap(POINT_OF_SALE_MAP);
    }

    @Test
    public void testAdd() throws Exception {
        pointOfSaleService.add("New POS");

        verify(cache, times(1)).add(any(PointOfSale.class), eq(POINT_OF_SALE_MAP));
    }

    @Test
    public void testUpdate() throws Exception {
        pointOfSaleService.update(1, "New Name");

        assertEquals("New Name", mockMap.get(1).getName());
        verify(cache, times(1)).getMap(POINT_OF_SALE_MAP);
    }

    @Test
    public void testDelete() throws Exception {
        pointOfSaleService.delete(1);

        verify(cache, times(1)).delete(1, POINT_OF_SALE_MAP);
    }*/
}
