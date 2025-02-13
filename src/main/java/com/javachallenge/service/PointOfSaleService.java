package com.javachallenge.service;

import com.javachallenge.dto.PointOfSaleCreateDTO;
import com.javachallenge.model.Cost;
import com.javachallenge.repository.CostRepository;
import com.javachallenge.utils.Mapper;
import com.javachallenge.dto.impl.PointOfSaleGetAll;
import com.javachallenge.dto.impl.PointOfSaleGetOne;
import com.javachallenge.model.PointOfSale;

import com.javachallenge.repository.PointOfSaleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.NoSuchElementException;

import static com.javachallenge.utils.Constants.*;

@Slf4j
@Service
public class PointOfSaleService {
    private final PointOfSaleRepository repository;
    private final CostRepository costRepository;
    private final Mapper mapper;

    public PointOfSaleService( PointOfSaleRepository repository, Mapper mapper, CostRepository costRepository)
    {
        this.repository = repository;
        this.mapper = mapper;
        this.costRepository = costRepository;
    }
    @Cacheable(value = POINT_OF_SALE_LIST)
    public List<PointOfSaleGetAll> getAll() {
        return getAllFromRepository()
                .stream()
                .map(p -> (PointOfSaleGetAll) mapper.PointOfSaleToGetDto(p, true)).toList();
    }

    @Cacheable(value = POINT_OF_SALE, key = "#id")
    public PointOfSaleGetOne get(int id){
        return (PointOfSaleGetOne)
                mapper.PointOfSaleToGetDto(getFromRepository(id), false);
    }

    @CacheEvict(value =POINT_OF_SALE_LIST,  allEntries = true)
    @Transactional
    public PointOfSaleGetOne add(PointOfSaleCreateDTO pointOfSaleCreateDTO)  {
       return (PointOfSaleGetOne)
               mapper.PointOfSaleToGetDto(save
                       (new PointOfSale(pointOfSaleCreateDTO.getName())),false);
    }

    @Caching(evict = {
            @CacheEvict(value = POINT_OF_SALE_LIST, allEntries = true),
            @CacheEvict(value = POINT_OF_SALE, key = "#pointOfSaleCreateDTO.id")
    })
    @Transactional
    public PointOfSaleGetOne update(PointOfSaleCreateDTO pointOfSaleCreateDTO)  {
        PointOfSale pointOfSale = getFromRepository(pointOfSaleCreateDTO.getId());
        pointOfSale.setName(pointOfSaleCreateDTO.getName());
        return (PointOfSaleGetOne)
        mapper.PointOfSaleToGetDto(save
                (pointOfSale),false);
    }


    public void delete(int id) {
        repository.deleteById(id);
    }

    public PointOfSale getFromRepository(int id){
        return repository.findById(id)
                .orElseThrow(() ->new NoSuchElementException(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO));
    }

    @CacheEvict(value = POINT_OF_SALE, key = "#id")
    public PointOfSaleGetOne addCost(int id, Cost cost) {
        PointOfSale pointOfSale = getFromRepository(id);
        pointOfSale.getCosts().add(cost);
        save(pointOfSale);
        return (PointOfSaleGetOne) mapper.PointOfSaleToGetDto(pointOfSale, false);
    }

    public List<PointOfSale> getAllFromRepository() {
        return  repository.findAll();
    }

    public PointOfSale save(PointOfSale pointOfSale){
        return repository.save(pointOfSale);
    }

}
