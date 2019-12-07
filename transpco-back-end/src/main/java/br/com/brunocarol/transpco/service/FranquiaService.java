package br.com.brunocarol.transpco.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brunocarol.transpco.model.Encomenda;
import br.com.brunocarol.transpco.model.Franquia;
import br.com.brunocarol.transpco.repository.EncomendaRepository;
import br.com.brunocarol.transpco.repository.FranquiaRepository;
import br.com.brunocarol.transpco.repository.FuncionarioRepository;
import br.com.brunocarol.transpco.service.exceptions.ObjectNotFoundException;
import br.com.brunocarol.transpco.model.Funcionario;

/**
 * Representa a camada de comunicação entre o Controller das rotas da entidade Franquia e o repositorio da entidade Franquia
 *
 */
@Service
public class FranquiaService {

	private static Logger log = LoggerFactory.getLogger(FranquiaService.class);
	@Autowired
	private FranquiaRepository repoFranquia;
	@Autowired
	private EncomendaRepository repoEncomenda;
	@Autowired
	private FuncionarioRepository repoFuncionario;

	/**
     * Recupera todas as Franquias da base de dados
     *
     * @return Uma lista com todas as Franquias
     */
	public List<Franquia> buscarTodos() {
		return this.repoFranquia.findAll();
	}

	/**
     * Recupera uma Franquia da base de dados
     *
     * @param id Idenficador da franquia a ser recuperada
     * @return Container que encapsula a franquia
     */
	public Franquia buscarPeloId(Long id) {
		Optional<Franquia> obj = this.repoFranquia.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
	}

	/**
     * Cria ou atualiza uma Franquia
     *
     * @param param Franquia que sera inserida
     * @return Uma nova ou atualizada Franquia
     */
	public Franquia salvar(Franquia param) {
		return repoFranquia.save(param);
	}

	/**
     * Exclue uma Franquia da base de dados
     *
     * @param id Idenficador da Franquia a ser excluida
     */
	public void deletar(Long id) {
		this.buscarPeloId(id);
		repoFranquia.deleteById(id);
	}
	
	/**
	 * Lista todas as encomendas que estão relacionadas com a Franquia
	 * 
	 * @param franquia Entidade que possui encomendas
	 * @return listar encomendas que a Franquia possui
	 */
	public List<Encomenda> listarEncomendas(Franquia franquia) {
		return repoEncomenda.findByFranquiaRemetenteOrFranquiaDestino(franquia, franquia);
	}
	
	/**
	 * Lista das rotas das encomendas relacionadas com a Franquia
	 * 
	 * @param franquia Entidade que possui encomendas
	 * @return lista de rotas para determinada encomenda da Franquia
	 */
	public List<Encomenda> listarRotas(Franquia franquia) {
		log.info("rotas listadas com sucesso");
		return repoEncomenda.findByRota(franquia);
	}
	
	/**
	 * Lista todas as Franquias dependendes (Proximas) da franquia atual
	 * 
	 * @param franquia Entidade atual que possui Franquias Proximas
	 * @return listagem das Franquias dependentes (Franquias Proximas)
	 */
	public List<Franquia> listarFranquiasDependentes(Franquia franquia) {
		List<Franquia> franquiasDependentes = new ArrayList<>(); 
		List<Franquia> franquias = repoFranquia.findAll();
		for(Franquia faux: franquias) 
			for(Franquia fpaux: franquiasProximas(faux)) 
				if(fpaux.equals(franquia))
					franquiasDependentes.add(fpaux);			
		log.info("listeed franquis dependentes sucess" , franquiasDependentes);
		return franquiasDependentes;
	}
	
	/**
	 * Lista todos os funcionarios de uma franquia.
	 * 
	 * @param franquia Entidade atual que possui Franquias Proximas
	 * @return listagem das Franquias dependentes (Franquias Proximas)
	 */
	public List<Funcionario> listarFuncionarios(Franquia franquia) {
		return repoFuncionario.findByFranquia(franquia);
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
			franquiasProximas.add(this.buscarPeloId(fpaux));
		return franquiasProximas;
	}
}
