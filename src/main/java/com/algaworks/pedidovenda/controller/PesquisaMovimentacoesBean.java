package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Movimentacao;
import com.algaworks.pedidovenda.model.StatusMovimentacao;
import com.algaworks.pedidovenda.repository.MovimentacaoDAO;
import com.algaworks.pedidovenda.repository.filter.MovimentacaoParaPesquisa;

@Named
@ViewScoped
public class PesquisaMovimentacoesBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentacaoDAO movimentacaoDAO;

	private MovimentacaoParaPesquisa filtro;
	private List<Movimentacao> movimentacoesFiltrados;

	public PesquisaMovimentacoesBean() {
		filtro = new MovimentacaoParaPesquisa();
		movimentacoesFiltrados = new ArrayList<>();
	}

	public void pesquisar() {
		movimentacoesFiltrados = movimentacaoDAO.filtrados(filtro);
	}

	public StatusMovimentacao[] getStatuses() {
		return StatusMovimentacao.values();
	}

	public List<Movimentacao> getMovimentacoesFiltrados() {
		return movimentacoesFiltrados;
	}

	public MovimentacaoParaPesquisa getFiltro() {
		return filtro;
	}
}
