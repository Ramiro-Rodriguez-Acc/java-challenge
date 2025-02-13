package com.javachallenge.controller;

import com.javachallenge.dto.CostDTO;
import com.javachallenge.dto.LowestCost;
import com.javachallenge.dto.PointOfSaleCreateDTO;
import com.javachallenge.dto.PointOfSaleDTO;
import com.javachallenge.dto.impl.PointOfSaleGetAll;
import com.javachallenge.dto.impl.PointOfSaleGetOne;
import com.javachallenge.service.CostService;
import com.javachallenge.service.GraphService;
import com.javachallenge.service.PointOfSaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.javachallenge.utils.Constants.*;

@RestController
@RequestMapping(PUNTOS_DE_VENTA_PATH)
public class PointOfSaleController {

    private static final String OK_MESSAGE_PUNTO_DE_VENTA_ELIMINADO = "Punto de Venta Eliminado";
    private static final String OK_MESSAGE_COSTO_REMOVDO = "Costo removido";


    private final  PointOfSaleService service;
    private final CostService costService;
    private final GraphService graphService;
    private final PointOfSaleService pointOfSaleService;

    public PointOfSaleController(PointOfSaleService service, CostService costService, GraphService graphService, PointOfSaleService pointOfSaleService) {
        this.service = service;
        this.costService = costService;
        this.graphService = graphService;
        this.pointOfSaleService = pointOfSaleService;
    }


    @GetMapping
    public ResponseEntity<List<PointOfSaleGetAll>> getAll()  {
        return
                ResponseEntity.ok(service.getAll());
    }

    @GetMapping(ID_PARAM+ RUTAS_DIRECTAS_RESOURCE)
    public ResponseEntity<PointOfSaleGetOne> get(@PathVariable int id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PostMapping
    public ResponseEntity<PointOfSaleDTO> add(@RequestBody PointOfSaleCreateDTO pointOfSaleCreateDTO) {
       return new ResponseEntity<>(service.add(pointOfSaleCreateDTO),HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<PointOfSaleDTO> update(@RequestBody PointOfSaleCreateDTO pointOfSaleCreateDTO)  {
        return new ResponseEntity<>(service.update(pointOfSaleCreateDTO),HttpStatus.OK);
    }

    @DeleteMapping(ID_PARAM)
    public ResponseEntity<String> delete(@PathVariable int id)  {
        pointOfSaleService.delete(id);
        return new ResponseEntity<>(OK_MESSAGE_PUNTO_DE_VENTA_ELIMINADO, HttpStatus.NO_CONTENT);

    }

    @PostMapping(ID_DESDE_PARAM+COSTOS_RESOURCE)
    public ResponseEntity<PointOfSaleDTO> addCost(@PathVariable(ID_DESDE)int idFrom,@RequestBody CostDTO costDTO) {
        return new ResponseEntity<>(costService.add(idFrom, costDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(ID_DESDE_PARAM+COSTOS_RESOURCE+ID_HASTA_PARAM)
    public ResponseEntity<String> deleteCost(@PathVariable(ID_DESDE) int idFrom, @PathVariable(ID_HASTA) int idTo)  {
        costService.delete(idFrom, idTo);
        return new ResponseEntity<>(OK_MESSAGE_COSTO_REMOVDO, HttpStatus.NO_CONTENT);
    }

    @GetMapping(ID_DESDE_PARAM+RUTA_MENOR_COSTO_RESOURCE+ID_HASTA_PARAM)
    public ResponseEntity<LowestCost> getLowerCost(@PathVariable (ID_DESDE) int idFrom, @PathVariable (ID_HASTA) int idTo) {
        return ResponseEntity.ok(graphService.getShortestPath(idFrom, idTo));
    }

}
