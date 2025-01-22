package com.javachallenge.controller;

import com.javachallenge.model.Accreditation;
import com.javachallenge.service.AccreditationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.javachallenge.utils.Constants.*;

@RestController
@RequestMapping(ACCREDITATION_URL)
public class AccreditationController {
    private final AccreditationService service;

    public AccreditationController(AccreditationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestParam(IMPORTE) int amount, @RequestParam int id) throws Exception {
        service.create(amount, id);
        return ResponseEntity.ok(ACREDITACION_GUARDADA);
    }

    @GetMapping(ID_PARAM)
    public ResponseEntity<Accreditation> get(@PathVariable int id) {
        return ResponseEntity.ok(service.get(id));
    }
}
