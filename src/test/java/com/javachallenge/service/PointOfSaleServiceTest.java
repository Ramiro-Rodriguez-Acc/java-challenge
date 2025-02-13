package com.javachallenge.service;


import com.javachallenge.dto.PointOfSaleCreateDTO;
import com.javachallenge.dto.impl.PointOfSaleGetAll;
import com.javachallenge.dto.impl.PointOfSaleGetOne;
import com.javachallenge.model.Cost;
import com.javachallenge.model.PointOfSale;
import com.javachallenge.repository.PointOfSaleRepository;
import com.javachallenge.utils.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class PointOfSaleServiceTest {

    @Mock
    private PointOfSaleRepository repository;

    @Mock
    private Mapper mapper;

    @InjectMocks
    private PointOfSaleService service;

    private PointOfSale pointOfSale;
    private PointOfSale pointOfSaleB;

    private List<PointOfSale> pointOfSaleLists = new ArrayList<>();
    private PointOfSaleCreateDTO pointOfSaleCreateDTO;
    private Cost cost;

    @BeforeEach
    void setUp() {
        pointOfSaleLists = List.of(new PointOfSale(1, "Ciudad A"), new PointOfSale(2, "Ciudad B"));
        pointOfSale = new PointOfSale(1, "Ciudad A");
        pointOfSaleB = new PointOfSale(2, "Ciudad B");

        pointOfSaleCreateDTO = new PointOfSaleCreateDTO(1,"Ciudad A");
        cost = new Cost(pointOfSale,pointOfSaleB, 125);
    }

    @Test
    void getAll_ShouldReturnMappedList_WhenRepositoryHasData() {
        List<PointOfSaleGetAll> expectedDtos = List.of(
                new PointOfSaleGetAll(1, "Ciudad A"),
                new PointOfSaleGetAll(2, "Ciudad B")
        );

        when(repository.findAll()).thenReturn(pointOfSaleLists);
        when(mapper.PointOfSaleToGetDto(any(PointOfSale.class), eq(true))).thenAnswer(invocation -> {
            PointOfSale pos = invocation.getArgument(0);
            return new PointOfSaleGetAll(pos.getId(), pos.getName());
        });

        List<PointOfSaleGetAll> result = service.getAll();

        assertEquals(2, result.size());
        assertEquals(expectedDtos.getFirst().getId(), result.getFirst().getId());
        assertEquals(expectedDtos.getFirst().getName(), result.getFirst().getName());
        verify(repository, times(1)).findAll();
        verify(mapper, times(2)).PointOfSaleToGetDto(any(PointOfSale.class), eq(true));
    }

    @Test
    void get_ShouldReturnPointOfSale_WhenValidId() {
        when(repository.findById(1)).thenReturn(Optional.of(pointOfSale));
        when(mapper.PointOfSaleToGetDto(pointOfSale, false)).thenReturn(new PointOfSaleGetOne(1, "Ciudad A"));

        PointOfSaleGetOne result = service.get(1);

        assertEquals(1, result.getId());
        assertEquals("Ciudad A", result.getName());
        verify(repository, times(1)).findById(1);
        verify(mapper, times(1)).PointOfSaleToGetDto(pointOfSale, false);
    }

    @Test
    void get_ShouldThrowException_WhenPointOfSaleNotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.get(1));
        verify(repository, times(1)).findById(1);
    }

    @Test
    void add_ShouldReturnPointOfSaleGetOne_WhenValidInput() {
        PointOfSaleGetOne pointOfSaleGetOne = new PointOfSaleGetOne(1, "Ciudad A");
        PointOfSale pointOfSaleNoId = new PointOfSale("Ciudad A");
        when(repository.save(pointOfSaleNoId)).thenReturn(pointOfSale);
        when(mapper.PointOfSaleToGetDto(pointOfSale, false)).thenReturn(pointOfSaleGetOne);

        PointOfSaleGetOne result = service.add(pointOfSaleCreateDTO);

        assertEquals(1, result.getId());
        assertEquals("Ciudad A", result.getName());
        verify(repository, times(1)).save(pointOfSaleNoId);
    }

    @Test
    void update_ShouldReturnUpdatedPointOfSale_WhenValidInput() {
        PointOfSaleCreateDTO pointOfSaleCreateDTO = new PointOfSaleCreateDTO(1, "Ciudad A - Actualizada");
        PointOfSale updatedPointOfSale = new PointOfSale(1, "Ciudad A - Actualizada");
        PointOfSaleGetOne pointOfSaleGetOne = new PointOfSaleGetOne(1, "Ciudad A - Actualizada");

        when(repository.findById(1)).thenReturn(Optional.of(pointOfSale));
        when(repository.save(updatedPointOfSale)).thenReturn(updatedPointOfSale);
        when(mapper.PointOfSaleToGetDto(updatedPointOfSale, false)).thenReturn(pointOfSaleGetOne);

        PointOfSaleGetOne result = service.update(pointOfSaleCreateDTO);

        assertEquals(1, result.getId());
        assertEquals("Ciudad A - Actualizada", result.getName());
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).save(updatedPointOfSale);
        verify(mapper, times(1)).PointOfSaleToGetDto(updatedPointOfSale, false);

    }
    @Test
    void delete_ShouldDeletePointOfSale_WhenValidId() {
        service.delete(1);
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void addCost_ShouldAddCostToPointOfSale() {
        when(repository.findById(1)).thenReturn(java.util.Optional.of(pointOfSale));
        when(repository.save(any(PointOfSale.class))).thenReturn(pointOfSale);
        when(mapper.PointOfSaleToGetDto(any(PointOfSale.class), anyBoolean())).thenReturn(new PointOfSaleGetOne(1,"Ciudad A"));

        service.addCost(1, cost);

        assertTrue(pointOfSale.getCosts().contains(cost));
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).save(pointOfSale);
    }

    @Test
    void addCost_ShouldThrowException_WhenPointOfSaleNotFound() {

        when(repository.findById(1)).thenReturn(java.util.Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.addCost(1, cost));
        verify(repository, times(1)).findById(1);
        verify(repository, never()).save(any(PointOfSale.class));
    }
}





