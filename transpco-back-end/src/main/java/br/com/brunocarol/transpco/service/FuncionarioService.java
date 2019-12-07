package br.com.brunocarol.transpco.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunocarol.transpco.model.Funcionario;
import br.com.brunocarol.transpco.repository.FuncionarioRepository;
import br.com.brunocarol.transpco.service.exceptions.ObjectNotFoundException;

/**
 * Representa a camada de comunicação entre o Controller das rotas da entidade Funcionario e o repositorio da entidade Funcionario
 *
 */
@Service
public class FuncionarioService {

	private static Logger log = LoggerFactory.getLogger(FuncionarioService.class);
	@Autowired
    private FuncionarioRepository repo;
    
	/**
     * Recupera todos os Funcionarios da base de dados
     *
     * @return Uma lista com todos os Funcionarios
     */
    public List<Funcionario> buscarTodos(){
    	return this.repo.findAll();
    }
    
	/**
     * Recupera um Funcionario da base de dados
     *
     * @param id Idenficador do Funcionario a ser recuperado
     * @return Container que encapsula o Funcionario
     */
    public Funcionario buscarPeloId(Long id) {
    	Optional<Funcionario> obj = this.repo.findById(id);
    	return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }
    
	/**
     * Cria ou atualiza um Funcionario
     *
     * @param obj Objeto com as informações a serem transformadas em Funcionario
     * @return Um novo ou atualizado Funcionario
     */
    public Funcionario salvar(Funcionario obj) {
    	return this.repo.save(obj);
    }
    
	/**
     * Exclue um Funcionario da base de dados
     *
     * @param id Idenficador do Funcionario a ser excluido
     */
    public void deletar(Long id) {
    	this.buscarPeloId(id);
    	this.repo.deleteById(id);
    }
    
    /**
	 * Faz a verificação de login com a camada de controle do Funcionario
	 * 
	 * @param email dado de login do Funcionario
	 * @param senha dado de login do Funcionario
	 * @return Funcioario realiza login no sistema
	 */
	public List<Funcionario> verificarUsuarioSenha(String email, String senha) {	
		List<Funcionario> retorno = repo.findByEmailAndSenhaIgnoreCase(email, senha);
		log.info("usuario verificado com sucesso");
		return retorno;
	}
}
