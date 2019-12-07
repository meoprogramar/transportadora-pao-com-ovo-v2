package br.com.brunocarol.transpco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brunocarol.transpco.model.Encomenda;
import br.com.brunocarol.transpco.model.Franquia;

/**
 * Interface de comunicação com a base de dados Encomenda
 *
 */
public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {

	/**
     * Executa uma busca por Encomenda baseado em sua Franquias de saida e destino
     * @param franquia1 Franquia remetente da encomenda
	* @param franquia2 Franquia de destino da encomenda
     * @return Lista de Encomenda pelas franquias proximas
     */
	List<Encomenda> findByFranquiaRemetenteOrFranquiaDestino(Franquia franquia1, Franquia franquia2);
	
	/**
     * Executa uma busca por Encomenda baseado em sua rota
     * @param franquia Franquia que a encomenda se encontra
     * @return Lista de Encomenda pela rota
     */
	List<Encomenda> findByRota(Franquia franquia);
}
