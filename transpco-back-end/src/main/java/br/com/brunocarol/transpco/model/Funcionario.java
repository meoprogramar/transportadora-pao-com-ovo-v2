package br.com.brunocarol.transpco.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Define as informações enviadas pela view para o controller da entidade Funcionario
 *
 */
@Entity
public class Funcionario extends EntidadeAbstrata {

	private String nome;
	private String email;
	private String senha;
	private boolean adm;
	@ManyToOne
	private Franquia franquia;	

	public Funcionario() {
		super();
	}

	public Funcionario(String nome, String email, String senha, boolean adm, Franquia franquia) {
		super();
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.adm = adm;
		this.franquia = franquia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAdm() {
		return adm;
	}

	public void setAdm(boolean adm) {
		this.adm = adm;
	}

	public Franquia getFranquia() {
		return franquia;
	}

	public void setFranquia(Franquia franquia) {
		this.franquia = franquia;
	}
}
