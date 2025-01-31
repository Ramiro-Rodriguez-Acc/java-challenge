package com.javachallenge.controller;

import com.javachallenge.model.PointOfSale;
import com.javachallenge.service.CostService;
import com.javachallenge.service.PointOfSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.javachallenge.utils.Constants.*;

@RestController
@RequestMapping("/puntos-de-venta")
public class PointOfSaleController {

    private final  PointOfSaleService service;
    private final CostService costService;

    public PointOfSaleController(PointOfSaleService service, CostService costService) {
        this.service = service;
        this.costService = costService;
    }


    @GetMapping
    public ResponseEntity<Map<Integer, PointOfSale>> getAll() throws Exception {
        return
                ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestParam(NOMBRE) String name) throws Exception {
        service.add(name);
        return ResponseEntity.ok(OK_MESSAGE_PUNTO_DE_VENTA_GUARDADO);
    }

    @PutMapping(ID_PARAM)
    public ResponseEntity<String> update(@PathVariable int id, @RequestParam(NOMBRE) String name) throws Exception {
        service.update(id, name);
        return ResponseEntity.ok(OK_MESSAGE_PUNTO_DE_VENTA_ACTUALIZADO);

    }

    @DeleteMapping(ID_PARAM)
    public ResponseEntity<String> delete(@PathVariable int id) throws Exception {
        service.delete(id);
        costService.deletePointOfSale(id);
        return ResponseEntity.ok(OK_MESSAGE_PUNTO_DE_VENTA_ELIMINADO);

    }

}
