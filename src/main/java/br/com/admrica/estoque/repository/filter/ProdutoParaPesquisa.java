package br.com.admrica.estoque.repository.filter;

import java.io.Serializable;

import br.com.admrica.estoque.model.Grupo;
import br.com.admrica.estoque.model.Unidade;

public class ProdutoParaPesquisa implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Long numeroDe;
	private Long numeroAte;
	private Grupo[] grupos;
	private Unidade[] unidades;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getNumeroDe() {
		return numeroDe;
	}

	public void setNumeroDe(Long numeroDe) {
		this.numeroDe = numeroDe;
	}

	public Long getNumeroAte() {
		return numeroAte;
	}

	public void setNumeroAte(Long numeroAte) {
		this.numeroAte = numeroAte;
	}

	public Grupo[] getGrupos() {
		return grupos;
	}

	public void setGrupos(Grupo[] grupos) {
		this.grupos = grupos;
	}

	public Unidade[] getUnidades() {
		return unidades;
	}

	public void setUnidades(Unidade[] unidades) {
		this.unidades = unidades;
	}

}