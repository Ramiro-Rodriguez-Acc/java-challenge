package com.javachallenge.service;

import com.javachallenge.dto.LowestCost;
import com.javachallenge.model.Cost;
import com.javachallenge.model.PointOfSale;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class GraphService {
    private  Graph<Integer, DefaultWeightedEdge> graph;
    private final PointOfSaleService pointOfSaleService;
    private final CostService costService;

    public GraphService(PointOfSaleService pointOfSaleService, CostService costService) {
        this.pointOfSaleService = pointOfSaleService;
        this.costService = costService;
    }

    public LowestCost getShortestPath(int idStart, int idEnd) throws Exception {
        Map<Integer, PointOfSale> pointOfSaleMap = pointOfSaleService.getAll();

        createGraph(pointOfSaleMap);
        GraphPath<Integer, DefaultWeightedEdge> shortestPath = shortestPath(idStart, idEnd);
        LowestCost lowestCost = new LowestCost();
        lowestCost.setCost(((Double) shortestPath.getWeight()).intValue());
        shortestPath.getVertexList().forEach(id -> lowestCost.getRoute().add(pointOfSaleMap.get(id).getName()));

        return lowestCost;
    }

    public void createGraph(Map<Integer, PointOfSale> pointOfSaleMap) throws Exception {
        graph = new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdge.class);
        pointOfSaleMap.forEach((id, pointOfSale) -> graph.addVertex(id));

        Map<Integer, Cost> costMap = costService.getAll();
        costMap.forEach((k, cost) -> {
            DefaultWeightedEdge edge = graph.addEdge(cost.getIdA(), cost.getIdB());
            if (edge != null) {
                graph.setEdgeWeight(edge, cost.getCost());
            }
        });
    }

    public GraphPath<Integer, DefaultWeightedEdge> shortestPath(int start, int end) {
        DijkstraShortestPath<Integer, DefaultWeightedEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
        return dijkstraAlg.getPath(start, end);
    }
}
