package br.com.brunocarol.transpco.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import br.com.brunocarol.transpco.model.Franquia;
import br.com.brunocarol.transpco.service.FranquiaService;

/**
 * Define as rotas e ações para interagir com a entidade Franquia
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/franquias2")
public class FranquiaController {

	/**
	 * Servico responsavel por interagir com a base de dados da entidade Franquia
	 */
	@Autowired
	private FranquiaService franquiaService;

	private static final Logger logger = LoggerFactory.getLogger(FranquiaController.class);

	/**
     * Responsavel por listar todas as franquias cadastradas.
     *
     * @return List<Franquia>
     */
	@GetMapping
	public List<Franquia> buscarTodas() {
		logger.info("Listing 'Franquia' on data source");
		return franquiaService.buscarTodos();
	}
	
	/**
     * Responsavel por verificar se uma franquia tem dependencias como
     * funcionarios cadastrados, encomendas pendentes ou rotas.
     *
     * @return List<Franquia>
     */
	@GetMapping("/dependencias/{id}")
	public boolean dependenciasFranquias(@PathVariable("id") Long id) {
		logger.info("Verify dependencies 'Franquia'");
		if((franquiaService.listarEncomendas(franquiaService.buscarPeloId(id)).size()>0) || 
			(franquiaService.listarRotas(franquiaService.buscarPeloId(id)).size()>0) ||
			(franquiaService.listarFranquiasDependentes(franquiaService.buscarPeloId(id)).size()>0) ||
			(franquiaService.listarFuncionarios(franquiaService.buscarPeloId(id)).size()>0))
			return false;
			else
				return true;
	}
	
	/**
     * Responsavel por salvar uma franquia, pode ser nova ou editada.
     * 
     * @param Objeto franquia que sera cadastrada/editada.
     */
	@PostMapping()
	public void salvarFranquia(@RequestBody Franquia franquia) {		
		logger.info("Creating/Editing 'Franquia' on data source");
		franquiaService.salvar(franquia);
	}
	
	/**
     * Responsavel por remover uma franquia.
     * 
     * @param Id da franquia que sera removido.
     */
	@DeleteMapping("/{id}")
	public void removerFranquia(@PathVariable("id") Long id) {
		logger.info("Removing 'Franquia' on data source");
		franquiaService.deletar(id);
	}
}
