package br.com.brunocarol.transpco.dijkstra;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Define os calculos de rota da aplicação(encomendas)
 * Usado como base https://github.com/fernandogodoy/algoritmo-dijkstra
 *
 */
public class Dijkstra {

private static Logger log = LoggerFactory.getLogger(Dijkstra.class);
	
	/**
	 * Calcula do menor caminho possível
	 * @param graph mapa para calculo de rota feitos pelos nós
	 * @param source nós da aplicação
	 * @return grafo com o menor caminho
	 */
	public static Grafo calculateShortestPathFromSource(Grafo graph, Node source) {

        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
        	Node currentNode = getLowestDistanceNode(unsettledNodes);
            log.info("Remoção currentNode foi um sucesso",currentNode);
            unsettledNodes.remove(currentNode);
            for (Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
            	Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    log.info("Calculo realizado{}", adjacentNode);
                	log.info("Inserido com sucesso {}", adjacentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    /**
    * Calcula a distancia minima dos nós 
    * @param evaluationNode nó inicial utilizado para calculo
    * @param edgeWeigh peso dos caminho existentes para rota
    * @param sourceNode proximo no visto o menor caminho
    */
    private static void CalculateMinimumDistance(Node evaluationNode, Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            log.info("Inserido com sucesso {}", sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    /**
    * Calcula a distancia do menor nó
    * @param unsettledNodes mapeamento dos nós que ainda não foram colocados no grafo
    * @return distancia por no (menor distancia)
    */
    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
    	Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        log.info("Retornado com sucesso");
       return lowestDistanceNode;
    }
}
