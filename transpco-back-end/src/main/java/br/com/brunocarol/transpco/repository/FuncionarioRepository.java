package br.com.brunocarol.transpco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brunocarol.transpco.model.Funcionario;
import br.com.brunocarol.transpco.model.Franquia;

/**
 * Interface de comunicação com a base de dados Funcionario
 *
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	/** 
	 * Executa o método findByEmailAndSenhaIgnoreCase(String email, String senha) para realizar uma conexao com a tabela funcionarios
	 * @param email email do funcionario para realizar o login
	 * @param senha senha do funcionario para realizar o login
     * @return Lista de funcionarios com os dados
	 */
	List<Funcionario> findByEmailAndSenhaIgnoreCase(String email, String senha);
	List<Funcionario> findByFranquia(Franquia franquia);	
}
