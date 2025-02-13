package com.javachallenge.service;

import com.javachallenge.dto.LowestCost;
import com.javachallenge.model.PointOfSale;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static com.javachallenge.utils.Constants.ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO;
import static com.javachallenge.utils.Constants.GRAPH;


@Service
public class GraphService {
    private Graph<Integer, DefaultWeightedEdge> graph;
    private final PointOfSaleService pointOfSaleService;

    public GraphService(PointOfSaleService pointOfSaleService) {
        this.pointOfSaleService = pointOfSaleService;
    }
    @Cacheable(value = GRAPH)
    public LowestCost getShortestPath(int idFrom, int idTo) {
        List<PointOfSale> pointOfSaleList = pointOfSaleService.getAllFromRepository();

        createGraph(pointOfSaleList);
        GraphPath<Integer, DefaultWeightedEdge> shortestPath = shortestPath(idFrom, idTo);
        LowestCost lowestCost = new LowestCost();
        lowestCost.setCost(((Double) shortestPath.getWeight()).intValue());
        shortestPath.getVertexList().forEach(id -> lowestCost.getRoute().add(pointOfSaleList.stream()
                .filter(p -> Objects.equals(p.getId(), id)).findFirst()
                .orElseThrow(() -> new NoSuchElementException(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO))
                .getName()));
        return lowestCost;
    }


    public void createGraph(List<PointOfSale> pointOfSaleList)  {
        graph = new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdge.class);
        pointOfSaleList.forEach((pointOfSale) -> graph.addVertex(pointOfSale.getId()));
        pointOfSaleList.forEach((pointOfSale) -> pointOfSale.getCosts().forEach(cost -> {
            DefaultWeightedEdge edge = graph.addEdge(pointOfSale.getId(), cost.getPointOfSaleTo().getId());
            graph.setEdgeWeight(edge, cost.getCost());
        }));
    }

    public GraphPath<Integer, DefaultWeightedEdge> shortestPath(int idFrom, int idTo) {
        DijkstraShortestPath<Integer, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
        return dijkstraAlg.getPath(idFrom, idTo);
    }
}
