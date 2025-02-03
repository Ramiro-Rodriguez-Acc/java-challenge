package com.javachallenge.controller;

import com.javachallenge.dto.DirectRoutes;
import com.javachallenge.dto.LowerCost;
import com.javachallenge.service.CostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.javachallenge.utils.Constants.*;

@RestController
@RequestMapping("/costo")
public class CostController {

    private final CostService service;

    public CostController(CostService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestParam int idA,
                                      @RequestParam int idB,
                                          @RequestParam(COSTO) int cost) throws Exception {
        service.add(idA, idB, cost);
        return ResponseEntity.ok(OK_MESSAGE_PUNTO_DE_VENTA_GUARDADO);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam int idA, @RequestParam int idB) throws Exception {
        service.delete(idA, idB);
        return ResponseEntity.ok(OK_MESSAGE_COSTO_REMOVDO);
    }

    @GetMapping(RUTA_DIRECTA_URL)
    public ResponseEntity<DirectRoutes> getDirectRoutes(@PathVariable int id) throws Exception {
        return ResponseEntity.ok(service.getDirectRoutes(id));
    }

    @GetMapping(MENOR_COSTO_URL)
    public ResponseEntity<LowerCost> getLowerCost(@RequestParam int idA, @RequestParam int idB) throws Exception {
        return ResponseEntity.ok(service.getLowerCost(idA, idB));
    }

}
