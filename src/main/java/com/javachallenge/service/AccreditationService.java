package com.javachallenge.service;

import com.javachallenge.model.Accreditation;
import com.javachallenge.model.PointOfSale;
import com.javachallenge.repository.AccreditationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

import static com.javachallenge.utils.Constants.ERROR_MESSAGE_ACREDITACION_NO_ENCONTRADA;

@Service
public class AccreditationService {

    private final PointOfSaleService pointOfSaleService;
    private final AccreditationRepository repository;

    public AccreditationService(PointOfSaleService pointOfSaleService, AccreditationRepository repository) {
        this.pointOfSaleService = pointOfSaleService;
        this.repository = repository;
    }
    @Transactional
    public void create(int amount, int id) throws Exception {
        PointOfSale pointOfSale = pointOfSaleService.get(id);
        Accreditation accreditation = new Accreditation(amount, id, pointOfSale.getName());
        repository.save(accreditation);
    }
    @Transactional(readOnly = true)
    public Accreditation get(int id)  {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(ERROR_MESSAGE_ACREDITACION_NO_ENCONTRADA));
    }
}
