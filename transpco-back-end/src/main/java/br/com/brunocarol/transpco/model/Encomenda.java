package br.com.brunocarol.transpco.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Transient;

import br.com.brunocarol.transpco.model.Franquia;

/**
 * Define as informações enviadas pela view para o controller da entidade
 * Encomenda
 */
@Entity
public class Encomenda extends EntidadeAbstrata {	
	
	private String clienteRemetente;
	private String clienteDestino;
	private String enderecoEntrega;
	private int tipo_encomenda; 
	@Transient
	private UUID idAlfaNumerico = UUID.randomUUID();
	@ManyToOne()
	private Franquia franquiaRemetente;	
	@ManyToOne()	
	private Franquia franquiaDestino;	
	@ManyToMany()
	private List<Franquia> rota = new ArrayList<>();

	public Encomenda() {
	}

	public Encomenda(String clienteRemetente, String clienteDestino, String enderecoEntrega, int tipo_encomenda,
			Franquia franquiaRemetente, Franquia franquiaDestino) {
		super();
		this.clienteRemetente = clienteRemetente;
		this.clienteDestino = clienteDestino;
		this.enderecoEntrega = enderecoEntrega;
		this.tipo_encomenda = tipo_encomenda;
		this.franquiaRemetente = franquiaRemetente;
		this.franquiaDestino = franquiaDestino;
	}

	public String getClienteRemetente() {
		return clienteRemetente;
	}

	public void setClienteRemetente(String clienteRemetente) {
		this.clienteRemetente = clienteRemetente;
	}

	public String getClienteDestino() {
		return clienteDestino;
	}

	public void setClienteDestino(String clienteDestino) {
		this.clienteDestino = clienteDestino;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setEnderecoEntrega(String enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

	public int getTipo_encomenda() {
		return tipo_encomenda;
	}

	public void setTipo_encomenda(int tipo_encomenda) {
		this.tipo_encomenda = tipo_encomenda;
	}

	public UUID getIdAlfaNumerico() {
		return idAlfaNumerico;
	}

	public void setIdAlfaNumerico(UUID idAlfaNumerico) {
		this.idAlfaNumerico = idAlfaNumerico;
	}

	public Franquia getFranquiaRemetente() {
		return franquiaRemetente;
	}

	public void setFranquiaRemetente(Franquia franquiaRemetente) {
		this.franquiaRemetente = franquiaRemetente;
	}

	public Franquia getFranquiaDestino() {
		return franquiaDestino;
	}

	public void setFranquiaDestino(Franquia franquiaDestino) {
		this.franquiaDestino = franquiaDestino;
	}

	public List<Franquia> getRota() {
		return rota;
	}

	public void setRota(List<Franquia> rota) {
		this.rota = rota;
	}	
}
