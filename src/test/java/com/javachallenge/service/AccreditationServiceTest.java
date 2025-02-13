package com.javachallenge.service;

import com.javachallenge.dto.AccreditationCreateDTO;
import com.javachallenge.dto.AccreditationGetDTO;
import com.javachallenge.model.Accreditation;
import com.javachallenge.model.PointOfSale;
import com.javachallenge.repository.AccreditationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccreditationServiceTest {

    @Mock
    private PointOfSaleService pointOfSaleService;

    @Mock
    private AccreditationRepository repository;

    @InjectMocks
    private AccreditationService accreditationService;

    private PointOfSale pointOfSale;
    private AccreditationCreateDTO accreditationCreateDTO;
    private Accreditation accreditation;

    @BeforeEach
    void setUp() {
        pointOfSale = new PointOfSale(5, "Ciudad A");
        accreditationCreateDTO = new AccreditationCreateDTO();
        accreditationCreateDTO.setAmount(1000L);
        accreditationCreateDTO.setPointOfSaleId(5);
        accreditation = new Accreditation(1000L, 5, "Ciudad A");
        accreditation.setId(1);
    }

    @Test
    void create_ShouldCreateAccreditationAndReturnDTO() {
        Accreditation accreditationNoId = new Accreditation(1000L, 5, "Ciudad A");
        accreditationNoId.setCreationDate(accreditation.getCreationDate());
        when(pointOfSaleService.getFromRepository(5)).thenReturn(pointOfSale);
        when(repository.save(accreditationNoId)).thenReturn(accreditation);

        AccreditationGetDTO result = accreditationService.create(accreditationCreateDTO);

        assertEquals(1000L, result.getAmount());
        assertEquals(5, result.getPointOfSaleId());
        verify(repository, times(1)).save(accreditationNoId);
        verify(pointOfSaleService, times(1)).getFromRepository(5);
    }


    @Test
    void get_ShouldReturnAccreditationDTO() {
        when(repository.findById(1)).thenReturn(Optional.of(accreditation));

        AccreditationGetDTO result = accreditationService.get(1);


        assertEquals(1000L, result.getAmount());
        assertEquals(5, result.getPointOfSaleId());
        verify(repository, times(1)).findById(1);
    }

}
