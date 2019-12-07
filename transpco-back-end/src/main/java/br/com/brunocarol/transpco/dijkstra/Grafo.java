package br.com.brunocarol.transpco.dijkstra;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe que faz a gestão dos nós disponíveis no grafo
 * Usado como base https://github.com/fernandogodoy/algoritmo-dijkstra
 *
 */
public class Grafo {

	private static Logger log = LoggerFactory.getLogger(Grafo .class);
	private List<Node> nodes = new ArrayList<Node>();

	/**
	* Estabelece o nó para calculo da rota
	* @param nodeA nó para calculo de rota para encomenda
	*/
    public void addNode(Node nodeA) {
    	log.info("Adicionado com sucesso" , nodeA);
    	nodes.add(nodeA);
    }

	/**
	* Estabelece uma lista dos nós disponíveis para realizar o calculo
	* @return lista de nós para calculo da rota
	*/
    public List<Node> getNodes() {
    	log.info("Lista de nós retornada com sucesso" , nodes);
    	return nodes;
    }

	/**
	* Seta os valores encontrados no nó
	* @param nodes valor de cada nó sendo parametro,distancia e destino
	*/
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public Integer getQtdArestas() {
		return nodes.size();
	}
}
