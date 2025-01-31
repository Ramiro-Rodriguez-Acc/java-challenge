package com.javachallenge.controller;

import com.javachallenge.dto.DirectRoutes;
import com.javachallenge.dto.LowestCost;
import com.javachallenge.service.CostService;
import com.javachallenge.service.GraphService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.javachallenge.utils.Constants.*;

@RestController
@RequestMapping("/costo")
public class CostController {

    private final CostService service;
    private final GraphService graphService;

    public CostController(CostService service, GraphService graphService) {
        this.graphService = graphService;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestParam int idA,
                                      @RequestParam int idB,
                                          @RequestParam(COSTO) int cost) throws Exception {
        service.add(idA, idB, cost);
        return ResponseEntity.ok(OK_MESSAGE_COSTO_GUARDADO);
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
    public ResponseEntity<LowestCost> getLowerCost(@RequestParam int idA, @RequestParam int idB) throws Exception {
        return ResponseEntity.ok(graphService.getShortestPath(idA, idB));
    }

}
