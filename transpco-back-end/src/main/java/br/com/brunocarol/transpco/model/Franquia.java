package br.com.brunocarol.transpco.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Define as informações enviadas pela view para o controller da entidade Franquia
 *
 */
@Entity
public class Franquia extends EntidadeAbstrata {
	
	private String nome;
	@Column(nullable = true)
	private boolean aeroporto;
	@Column(nullable = true)
	private boolean matriz;
	private String rua;
	private String numero;
	private String cidade;
	private String estado;
	private String regiao;

	@ElementCollection
	@CollectionTable(name = "franquiasProximas")
	@MapKeyColumn(name = "franquiaProximaId")
	private Map<Long, Double> franquiasProximas = new HashMap<Long, Double>();

	@JsonBackReference
	@OneToMany(mappedBy = "franquiaRemetente")
	private List<Encomenda> encomendas = new ArrayList<>();

	public Franquia() {

	}

	public Franquia(String nome, boolean aeroporto, boolean matriz, String regiao, String estado,
			String cidade, String rua, String numero) {
		super();
		this.nome = nome;
		this.aeroporto = aeroporto;
		this.matriz = matriz;
		this.rua = rua;
		this.numero = numero;
		this.cidade = cidade;
		this.estado = estado;
		this.regiao = regiao;

	}

	public List<Encomenda> getEncomendas() {
		return encomendas;
	}

	public void setEncomendas(List<Encomenda> encomendas) {
		this.encomendas = encomendas;
	}

	public boolean isAeroporto() {
		return aeroporto;
	}

	public void setAeroporto(boolean aeroporto) {
		this.aeroporto = aeroporto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isMatriz() {
		return matriz;
	}

	public void setMatriz(boolean matriz) {
		this.matriz = matriz;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public Map<Long, Double> getFranquiasProximas() {
		return franquiasProximas;
	}

	public void setFranquiasProximas(Map<Long, Double> franquiasProximas) {
		this.franquiasProximas = franquiasProximas;
	}
}
