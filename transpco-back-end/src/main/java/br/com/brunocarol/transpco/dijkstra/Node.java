package br.com.brunocarol.transpco.dijkstra;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.math.BigDecimal;
import br.com.brunocarol.transpco.model.Franquia;

/**
 * Classe que faz a ligação dos nós ao restante da estrutura
 * Usado como base https://github.com/fernandogodoy/algoritmo-dijkstra
 *
 */
public class Node {

	private Franquia franq;

    private LinkedList<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private Map<Node, Integer> adjacentNodes = new HashMap<>();
    
    /**
    * Linkagem de franquia a um nó para ser feito o calculo
    * @param franq franquia que se tornará um nó para calculo de rota
    */
    public Node(Franquia franq) {
        this.franq = franq;
    }

    /**
    * Método que adiciona um destino e uma distancia para o calculo
    * @param destination franquia de destino da encomenda
    * @param distance distancia entre os nós disponíveis
    */
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Franquia getFranq() {
		return franq;
	}

	public void setFranq(Franquia franq) {
		this.franq = franq;
	}

	public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(LinkedList<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }
    
    /**
     * Método que forma um nó e uma distancia para o calculo (juntamente com weight)
     * @param origem franquia de saida da encomenda
     * @param destino distancia entre os nós disponíveis
     * @param peso numero para calculo das distancias
     */
    public Node(String origem, String destino, String peso) {
		new Vertice(origem);
		new Vertice(destino);
		new BigDecimal(peso);
	}
}
