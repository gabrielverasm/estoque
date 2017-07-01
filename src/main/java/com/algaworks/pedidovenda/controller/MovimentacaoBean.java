package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Movimentacao;
import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.service.MovimentacaoService;
import com.algaworks.pedidovenda.service.ProdutoService;

@Named
@ViewScoped
public class MovimentacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	private Movimentacao movimentacao;

	private MovimentacaoService movimentacaoService;

	private ProdutoService produtoService;

	private Produto produtoLinhaEditavel;

	public MovimentacaoBean() {
		limpar();
	}

	public void salvar() {
		System.out.println("movimentacao salva");
	}

	public void inicializar() {

	}

	public void limpar() {
		movimentacao = new Movimentacao();
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public MovimentacaoService getMovimentacaoService() {
		return movimentacaoService;
	}

	public void setMovimentacaoService(MovimentacaoService movimentacaoService) {
		this.movimentacaoService = movimentacaoService;
	}

	public ProdutoService getProdutoService() {
		return produtoService;
	}

	public void setProdutoService(ProdutoService produtoService) {
		this.produtoService = produtoService;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

}
