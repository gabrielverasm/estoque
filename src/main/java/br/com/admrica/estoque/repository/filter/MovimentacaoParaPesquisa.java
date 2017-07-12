package br.com.admrica.estoque.repository.filter;

import java.io.Serializable;
import java.util.Date;

import br.com.admrica.estoque.model.StatusMovimentacao;

public class MovimentacaoParaPesquisa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long numeroDe;
	private Long numeroAte;
	private Date dataCriacaoDe;
	private Date dataCriacaoAte;
	private String descricao;
	private StatusMovimentacao[] statuses;

	// get and set
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

	public Date getDataCriacaoDe() {
		return dataCriacaoDe;
	}

	public void setDataCriacaoDe(Date dataCriacaoDe) {
		this.dataCriacaoDe = dataCriacaoDe;
	}

	public Date getDataCriacaoAte() {
		return dataCriacaoAte;
	}

	public void setDataCriacaoAte(Date dataCriacaoAte) {
		this.dataCriacaoAte = dataCriacaoAte;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String nome) {
		this.descricao = nome;
	}

	public StatusMovimentacao[] getStatuses() {
		return statuses;
	}

	public void setStatuses(StatusMovimentacao[] statuses) {
		this.statuses = statuses;
	}
}
