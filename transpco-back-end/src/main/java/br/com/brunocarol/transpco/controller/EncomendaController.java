package br.com.brunocarol.transpco.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunocarol.transpco.model.Encomenda;
import br.com.brunocarol.transpco.model.Franquia;
import br.com.brunocarol.transpco.service.EncomendaService;

/**
 * Define as rotas e ações para interagir com a entidade Encomenda
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/encomendas2")
public class EncomendaController { 

	/**
     * Servico responsavel por interagir com a base de dados da entidade Encomenda
     */
	@Autowired
	private EncomendaService encomendaService;

	private static final Logger logger = LoggerFactory.getLogger(EncomendaController.class);
	
	/**
     * Responsavel por listar todas as encomendas cadastradas.
     *
     * @return List<Encomenda>
     */
	@GetMapping
	public List<Encomenda> listarEncomenda() {
		logger.info("Listing 'Encomenda' on data source");
		return encomendaService.buscarTodos(); 
	}
	
	/**
     * Responsavel por calcular a rota de uma encomenda.
     *
     * @return List<Encomenda>
     */
	@PostMapping("/rota")
	public List<Franquia> calcularRota(@RequestBody Encomenda encomenda) {
		logger.info("Calculating route 'Encomenda' on data source");
		return encomendaService.calcularRota(encomenda); 
	}
	
	/**
     * Responsavel por cadastrar uma nova encomenda.
     * 
     * @param Objeto Encomenda que sera cadastrada.
     */
	@PostMapping()
	public void salvarEncomenda(@RequestBody Encomenda encomenda) {		
		logger.info("Creating 'Encomenda' on data source");
		encomendaService.salvar(encomenda);
	}
}
