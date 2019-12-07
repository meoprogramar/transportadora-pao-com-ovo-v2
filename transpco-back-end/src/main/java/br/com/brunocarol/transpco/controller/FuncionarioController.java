package br.com.brunocarol.transpco.controller;

import java.util.List;

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

import br.com.brunocarol.transpco.model.Funcionario;
import br.com.brunocarol.transpco.service.FuncionarioService;

/**
 * Define as rotas e ações para interagir com a entidade Funcionario
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/funcionarios2")
public class FuncionarioController {

	/**
     * Servico responsavel por interagir com a base de dados da entidade Funcionario
     */
	@Autowired
	private FuncionarioService funcionarioService;

	private static final Logger logger = LoggerFactory.getLogger(FuncionarioController.class);

	/**
     * Responsavel por listar todos os funcionarios cadastrados.
     *
     * @return List<Funcionario>
     */
	@GetMapping
	public List<Funcionario> listarFuncionarios() {
		logger.info("Listing 'Funcionario' on data source");
		return funcionarioService.buscarTodos(); 
	}
	
	/**
     * Responsavel por salvar um funcionario, pode ser novo ou editado.
     * 
     * @param Objeto funcionario que sera cadastrado/editado.
     */
	@PostMapping()
	public void salvarFuncionario(@RequestBody Funcionario funcionario) {
		logger.info("Creating/Editing 'Funcionario' on data source");
		funcionarioService.salvar(funcionario);
	}
	
	/**
     * Responsavel por remover um funcionario.
     * 
     * @param Id do funcionario que sera removido.
     */
	@DeleteMapping("/{id}")
	public void removerFuncionario(@PathVariable("id") Long id) {
		logger.info("Removing 'Funcionario' on data source");
		funcionarioService.deletar(id);	
	}	
}
