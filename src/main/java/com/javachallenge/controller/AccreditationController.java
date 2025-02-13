package com.javachallenge.controller;

import com.javachallenge.dto.AccreditationCreateDTO;
import com.javachallenge.dto.AccreditationGetDTO;
import com.javachallenge.service.AccreditationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.javachallenge.utils.Constants.ID_PARAM;


@RestController
@RequestMapping("/acreditacion")
public class AccreditationController {

    private final AccreditationService service;

    public AccreditationController(AccreditationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AccreditationGetDTO> create(@RequestBody AccreditationCreateDTO accreditationCreateDTO)  {
        return new ResponseEntity<>(service.create(accreditationCreateDTO), HttpStatus.CREATED);
    }

    @GetMapping(ID_PARAM)
    public ResponseEntity<AccreditationGetDTO> get(@PathVariable int id) {
        return ResponseEntity.ok(service.get(id));
    }
}
