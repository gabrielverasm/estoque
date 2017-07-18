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

	
	private List<Produto> listaProdutoAcabando = new ArrayList<>();
	
	public HomeBean() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init(){
		this.listaProdutoAcabando = this.produtoService.pesquisarPorProdutoAcabando();
		
	}

	public List<Produto> getListaProdutoAcabando() {
		return listaProdutoAcabando;
	}

	public void setListaProdutoAcabando(List<Produto> listaProdutoAcabando) {
		this.listaProdutoAcabando = listaProdutoAcabando;
	}
	
}
