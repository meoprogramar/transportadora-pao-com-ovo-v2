package br.com.brunocarol.transpco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brunocarol.transpco.model.Franquia;

/**
 * Interface de comunicação com a base de dados Franquia
 *
 */
@Repository
public interface FranquiaRepository extends JpaRepository<Franquia, Long>{

}
