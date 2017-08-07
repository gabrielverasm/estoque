package br.com.admrica.estoque.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admrica.estoque.model.Produto;
import br.com.admrica.estoque.service.ProdutoService;

@Named
@RequestScoped
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService produtoService;

	private List<Produto> listaPrdutosAlerta = new ArrayList<>();

	private List<Produto> listaProdutoQuantidadeCriticaTrintaPorCento = new ArrayList<>();

	public HomeBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		this.listaPrdutosAlerta = this.produtoService.pesquisarPorProdutoAlerta();
		this.listaProdutoQuantidadeCriticaTrintaPorCento = this.produtoService
				.pesquisarPorProdutoQuantidadeCriticaTrintaPorCento();
	}

	public List<Produto> getListaProdutoAcabando() {
		return listaPrdutosAlerta;
	}

	public void setListaProdutoAcabando(List<Produto> listaProdutoAcabando) {
		this.listaPrdutosAlerta = listaProdutoAcabando;
	}

	public List<Produto> getListaProdutoQuantidadeCriticaTrintaPorCento() {
		return listaProdutoQuantidadeCriticaTrintaPorCento;
	}

	public void setListaProdutoQuantidadeCriticaTrintaPorCento(
			List<Produto> listaProdutoQuantidadeCriticaTrintaPorCento) {
		this.listaProdutoQuantidadeCriticaTrintaPorCento = listaProdutoQuantidadeCriticaTrintaPorCento;
	}

}
