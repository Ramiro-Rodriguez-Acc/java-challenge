package com.javachallenge.service;

import com.javachallenge.model.Accreditation;
import com.javachallenge.model.PointOfSale;
import com.javachallenge.repository.AccreditationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccreditationServiceTest {
    @Mock
    private PointOfSaleService pointOfSaleService;

    @Mock
    private AccreditationRepository repository;

    @InjectMocks
    private AccreditationService accreditationService;

    private static final String ERROR_MESSAGE_ACREDITACION_NO_ENCONTRADA = "Acreditacion no encontrado";
    private Accreditation mockAccreditation;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockAccreditation = new Accreditation(100, 1, "Test POS");
        mockAccreditation.setId(1);

    }

    @Test
    public void testCreate() throws Exception {
        PointOfSale mockPointOfSale = new PointOfSale("Test POS");
        when(pointOfSaleService.get(1)).thenReturn(mockPointOfSale);

        accreditationService.create(100, 1);

        verify(pointOfSaleService, times(1)).get(1);
        verify(repository, times(1)).save(any(Accreditation.class));
    }

    @Test
    public void testGet() {
        when(repository.findById(1)).thenReturn(Optional.of(mockAccreditation));

        Accreditation result = accreditationService.get(1);

        assertNotNull(result);
        assertEquals(100, result.getAmount());
        assertEquals(1, result.getId());
        assertEquals("Test POS", result.getPointOfSaleName());
        verify(repository, times(1)).findById(1);
    }

    @Test
    public void testGet_NotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            accreditationService.get(1);
        });

        assertEquals(ERROR_MESSAGE_ACREDITACION_NO_ENCONTRADA, exception.getMessage());
        verify(repository, times(1)).findById(1);
    }

}
