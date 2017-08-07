package br.com.admrica.estoque.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admrica.estoque.model.Grupo;
import br.com.admrica.estoque.model.Produto;
import br.com.admrica.estoque.model.Unidade;
import br.com.admrica.estoque.repository.ProdutoDAO;
import br.com.admrica.estoque.repository.filter.ProdutoParaPesquisa;
import br.com.admrica.estoque.service.ProdutoService;

@Named
@ViewScoped
public class PesquisaProdutosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService produtoService;

	@Inject
	private ProdutoDAO produtoDAO;

	private ProdutoParaPesquisa filtro;

	private List<Produto> produtosFiltrados = new ArrayList<Produto>();

	private Produto produtoSelecionado;

	public PesquisaProdutosBean() {
		filtro = new ProdutoParaPesquisa();
	}

	public void pesquisar() {
		this.produtosFiltrados = produtoDAO.filtrados(filtro);
	}

	public void remover() {
		produtoService.remover(produtoSelecionado);
		produtosFiltrados.remove(produtoSelecionado);
	}

	// get and set

	public Grupo[] getGrupos() {
		return Grupo.values();
	}

	public Unidade[] getUnidades() {
		return Unidade.values();
	}

	public List<Produto> getProdutosFiltrados() {
		return produtosFiltrados;
	}

	public ProdutoParaPesquisa getFiltro() {
		return filtro;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}

}