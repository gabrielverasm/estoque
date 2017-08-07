package br.com.admrica.estoque.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.model.StatusMovimentacao;
import br.com.admrica.estoque.repository.MovimentacaoDAO;
import br.com.admrica.estoque.repository.filter.MovimentacaoParaPesquisa;

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

	// get and set
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
