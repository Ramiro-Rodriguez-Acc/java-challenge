package com.javachallenge.service;

import com.javachallenge.dto.AccreditationCreateDTO;
import com.javachallenge.dto.AccreditationGetDTO;
import com.javachallenge.model.Accreditation;
import com.javachallenge.model.PointOfSale;
import com.javachallenge.repository.AccreditationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;


@Service
public class AccreditationService {
    private static final String ERROR_MESSAGE_ACREDITACION_NO_ENCONTRADA = "Acreditacion no encontrada";

    private final PointOfSaleService pointOfSaleService;
    private final AccreditationRepository repository;

    public AccreditationService(PointOfSaleService pointOfSaleService, AccreditationRepository repository) {
        this.pointOfSaleService = pointOfSaleService;
        this.repository = repository;
    }
    @Transactional
    public AccreditationGetDTO create(AccreditationCreateDTO accreditationCreateDTO)  {
        PointOfSale pointOfSale = pointOfSaleService.getFromRepository(accreditationCreateDTO.getPointOfSaleId());
        Accreditation accreditation = new Accreditation(accreditationCreateDTO.getAmount(), accreditationCreateDTO.getPointOfSaleId(), pointOfSale.getName());
        AccreditationGetDTO accreditationGetDTO = new AccreditationGetDTO(repository.save(accreditation));
        accreditationGetDTO.createLinks();
        return accreditationGetDTO;
    }
    public AccreditationGetDTO get(int id)  {
        AccreditationGetDTO accreditationGetDTO = new AccreditationGetDTO(repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ERROR_MESSAGE_ACREDITACION_NO_ENCONTRADA)));
        accreditationGetDTO.createLinks();
        return accreditationGetDTO;
    }
}
