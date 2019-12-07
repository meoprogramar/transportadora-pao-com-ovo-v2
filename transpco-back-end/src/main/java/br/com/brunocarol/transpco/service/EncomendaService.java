package br.com.brunocarol.transpco.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunocarol.transpco.model.Encomenda;
import br.com.brunocarol.transpco.model.Franquia;
import br.com.brunocarol.transpco.dijkstra.Grafo;
import br.com.brunocarol.transpco.dijkstra.Dijkstra;
import br.com.brunocarol.transpco.dijkstra.Node;
import br.com.brunocarol.transpco.repository.EncomendaRepository;
import br.com.brunocarol.transpco.repository.FranquiaRepository;
import br.com.brunocarol.transpco.service.exceptions.ObjectNotFoundException;

/**
 * Representa a camada de comunicação entre o Controller das rotas da entidade Encomenda e o repositorio da entidade Encomenda
 *
 */
@Service
public class EncomendaService {

	private static Logger log = LoggerFactory.getLogger(EncomendaService.class);
	@Autowired
	private EncomendaRepository repo;
	@Autowired
	private FranquiaRepository repoFranquia;
	@Autowired
	private FranquiaService franquiaService;

	/**
     * Recupera todas as Encomendas da base de dados
     *
     * @return Uma lista com todas as Encomendas
     */
	public List<Encomenda> buscarTodos() {
		log.info("Lista com todas as Encomendas");
		return this.repo.findAll();
	}

	/**
     * Recupera uma encomenda da base de dados
     *
     * @param id Idenficador da encomenda a ser recuperada
     * @return Container que encapsula a encomenda
     */
	public Encomenda buscarPeloId(Long id) {
		Optional<Encomenda> obj = repo.findById(id);
		log.info("Encomenda retornada  {}" , obj);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	/**
     * Cria ou atualiza uma Encomenda
     *
     * @param obj Objeto com as informações a serem transformadas em Encomenda
     * @return Uma nova ou atualizada Encomenda
     */
	public Encomenda salvar(Encomenda obj) {
		log.info("Encomenda salva com sucesso");
		return this.repo.save(obj);
	}
	
	/**
     * Recupera to horário atual do sistema para salvar
     *
     * @return Data no horario certo com o formato setado
     */
	public String horaAtual() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		log.debug("dada a hora atual {} ", dateFormat);
		return dateFormat.format(new Date());
	}

	/**
     * Faz a seleção do tipo de calculo de rota dependendo do tipo de encomenda
     *
     * @param encomenda Seleciona o tipo de enconda para a rota ser calculada
     * @return Escolha int do usuario para calculo de rota
     */
	public List<Franquia> calcularRota(Encomenda encomenda) {
		List<Franquia> rota = new ArrayList<>();
		switch (encomenda.getTipo_encomenda()) {
		case 0:
			rota = calculoRotaNormal(encomenda);
			log.debug("Rota que foi selecionada {}", rota);
			log.info("Tipo de Encomenda Normal {}", encomenda);

			break;
		case 1:
			rota = calcularRotaExpressa(encomenda);
			log.debug("Rota que foi selecionada {}", rota);
			log.info("Tipo de Encomenda Expressa {}", encomenda);
			break;
		case 2:
			rota = calcularRota24hrs(encomenda);
			log.debug("Rota que foi selecionada {}", rota);
			log.info("Tipo de Encomenda24H {}", encomenda);
			break;
		}
		log.info("A Rota foi calculada com sucesso");
		return rota;
	}

	/**
	 * Encomenda do tipo normal selecionada para calculo de rota
	 * Dado que o algoritmo de dijkstra é utilizado para calculo, é notório se trabalhas com as listas para auxilio do calculo
	 * 
	 * @param encomenda Tipo de encomenda selecionado
	 * @return Rota normal calculada com sucesso
	 */
	private List<Franquia> calculoRotaNormal(Encomenda encomenda) {
		if (encomenda.getFranquiaDestino().equals(encomenda.getFranquiaRemetente())) {
			log.debug("Lista de Encomendas {}", encomenda);
			return new ArrayList<>();
		}
		Random ran = new Random();
		List<Franquia> rotaParcial = new ArrayList<>();
		List<Franquia> rotaFinal = new ArrayList<>();
		List<Long> franquiasVisitadas = new ArrayList<Long>();
		Franquia franquiaAtual = encomenda.getFranquiaRemetente();
		List<Franquia> franquiasProximasAuxiliar = new ArrayList<>(franquiasProximas(franquiaAtual)); 
		Franquia franquiaAtualAuxiliar;

		franquiasVisitadas.add(franquiaAtual.getId());
		rotaParcial.add(franquiaAtual);
		log.info("Adicionado com sucesso a Rota Parcial{}", franquiaAtual);
		rotaFinal.add(franquiaAtual);

		while (encomenda.getFranquiaDestino().getId() != franquiaAtual.getId()) {
			if (franquiasProximasAuxiliar.size() != 0) {
				franquiaAtualAuxiliar = franquiasProximasAuxiliar.get(ran.nextInt(franquiasProximasAuxiliar.size()));
				franquiasProximasAuxiliar.remove(franquiaAtualAuxiliar);
				log.info("Removido com sucesso {}", franquiaAtualAuxiliar);
				if (!franquiasVisitadas.contains(franquiaAtualAuxiliar.getId())) {
					franquiasProximasAuxiliar = new ArrayList<>(franquiasProximas(franquiaAtualAuxiliar));
					franquiasProximasAuxiliar.remove(franquiaAtual);
					franquiaAtual = franquiaAtualAuxiliar;
					franquiasVisitadas.add(franquiaAtual.getId());
					log.info("Adicionado com sucesso", franquiaAtual);
					rotaParcial.add(franquiaAtual);
					rotaFinal.add(franquiaAtual);

				}
			} else {
				if (encomenda.getFranquiaRemetente().equals(franquiaAtual) || (rotaFinal.size() == 0))
					return new ArrayList<>();

				rotaFinal.remove(rotaFinal.size() - 1);
				franquiaAtual = rotaParcial.get(rotaParcial.indexOf(franquiaAtual) - 1);
				franquiasProximasAuxiliar = new ArrayList<>(franquiasProximas(franquiaAtual));
				franquiasProximasAuxiliar.remove(rotaParcial.get(rotaParcial.indexOf(franquiaAtual) + 1));
				log.info("Removido com sucesso{}", rotaParcial);
			}
		}
		return rotaFinal;
	}

	/**
	 * Encomenda do tipo expressa selecionada para calculo de rota
	 * Dado que o algoritmo de dijkstra é utilizado para calculo, é notório se trabalhas com as listas para auxilio do calculo
	 * 
	 * @param encomenda Tipo de encomenda selecionado
	 * @return Rota expressa calculada com sucesso
	 */
	private List<Franquia> calcularRotaExpressa(Encomenda encomenda) {
		List<Franquia> franquias = repoFranquia.findAll();
		List<Franquia> rotaFinal = new ArrayList<Franquia>();
		List<Node> nos = new ArrayList<Node>();
		Node noInicial = null, noSave;
		Grafo grafo = new Grafo();

		if (encomenda.getFranquiaDestino().equals(encomenda.getFranquiaRemetente()))
			return new ArrayList<>();
		for (Franquia franquia : franquias) {
			if (encomenda.getFranquiaRemetente().equals(franquia)) {
				noInicial = new Node(franquia);
				noSave = noInicial;
			} else
				noSave = new Node(franquia);
			nos.add(noSave);
			log.info("Nós adicionado com sucesso {}", noSave);
			grafo.addNode(noSave);
			log.info("Grafo salvo com os nós com sucesso{}", noSave);
		}
		for (Node noAux : nos) {
			for (Node noAux2 : nos) {
				if (noAux.getFranq().getFranquiasProximas().containsKey(noAux2.getFranq().getId())) {
					int distancia = noAux.getFranq().getFranquiasProximas().get(noAux2.getFranq().getId()).intValue();
					noAux.addDestination(noAux2, distancia);
				}
			}
		}
		grafo = Dijkstra.calculateShortestPathFromSource(grafo, noInicial);
		for (Node no : grafo.getNodes()) {
			if (no.getFranq().equals(encomenda.getFranquiaDestino())) {
				for (Node noRota : no.getShortestPath())
					rotaFinal.add(noRota.getFranq());
				if (!rotaFinal.isEmpty())
					rotaFinal.add(no.getFranq());
				break;
			}
		}
		log.info("Rota retornada com sucesso {}", rotaFinal);
		return rotaFinal;
	}

	/**
	 * Encomenda do tipo 24H selecionada para calculo de rota
	 * Dado que o algoritmo de dijkstra é utilizado para calculo, é notório se trabalhas com as listas para auxilio do calculo
	 * 
	 * @param encomenda Tipo de encomenda selecionado
	 * @return Rota 24H calculada com sucesso
	 */
	private List<Franquia> calcularRota24hrs(Encomenda encomenda) {
		List<Franquia> rotaFinal = new ArrayList<Franquia>();

		if (encomenda.getFranquiaDestino().equals(encomenda.getFranquiaRemetente()))
			return new ArrayList<>();

		rotaFinal.add(encomenda.getFranquiaRemetente());
		Franquia franquiaAtual = encomenda.getFranquiaDestino();
		if (!franquiaAtual.isAeroporto()) {
			Double menorDist = Double.MAX_VALUE;
			for (Entry<Franquia, Double> franqProximas : franquiasProximasComDistancia(franquiaAtual).entrySet())
				if ((franqProximas.getValue() < menorDist) & (franqProximas.getKey().isAeroporto())
						& (!franqProximas.getKey().equals(encomenda.getFranquiaRemetente()))) {
					franquiaAtual = franqProximas.getKey();
					menorDist = franqProximas.getValue();
				}

			if (franquiaAtual.equals(encomenda.getFranquiaDestino()))
				rotaFinal.clear();
		}
		if (!rotaFinal.isEmpty())
			rotaFinal.add(franquiaAtual);
		log.info("Rota retornada com sucesso{}", rotaFinal);
		return rotaFinal;
	}
	
	/**
	 * Retornar franquias proximas em forma de um objeto Franquia.
	 * 
	 * @param franquia que se deseja obter as franquias proximas
	 * @return listagem das Franquias proximas (Franquias Proximas)
	 */	
	private List<Franquia> franquiasProximas(Franquia franquia){
		List<Franquia> franquiasProximas = new ArrayList<>(); 
		for(Long fpaux: franquia.getFranquiasProximas().keySet())
			franquiasProximas.add(franquiaService.buscarPeloId(fpaux));
		return franquiasProximas;
	}
	
	/**
	 * Retornar franquias proximas em forma de um map entre a franquia proxima e sua distancia.
	 * 
	 * @param franquia que se deseja obter as franquias proximas
	 * @return listagem das Franquias proximas (Franquias Proximas)
	 */	
	private Map<Franquia, Double> franquiasProximasComDistancia(Franquia franquia){
		Map<Franquia, Double> franquiasProximas = new HashMap<Franquia, Double>(); 
		for(Entry<Long, Double> fpaux: franquia.getFranquiasProximas().entrySet())
			franquiasProximas.put(franquiaService.buscarPeloId(fpaux.getKey()), fpaux.getValue());
		return franquiasProximas;
	}

}
