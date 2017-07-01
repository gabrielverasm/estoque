package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.ItemMovimentacao;
import com.algaworks.pedidovenda.model.Movimentacao;
import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.service.MovimentacaoService;
import com.algaworks.pedidovenda.service.ProdutoService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroMovimentacaoBean implements Serializable {

	
	private static final long serialVersionUID = 1L;


	private Movimentacao movimentacao;
	
	@Inject
	private MovimentacaoService movimentacaoService;
	
	@Inject
	private ProdutoService produtoService;
	
	private Produto produtoLinha;
	
	public CadastroMovimentacaoBean() {
		limpar();
	}
	
	public void inicializar() {
	if (FacesUtil.isNotPostback()) {

		this.movimentacao.adicionaItemVazio();

	}
}

	public void limpar() {
		movimentacao = new Movimentacao();
	}
	
	public void salvar(){
		this.movimentacao = this.movimentacaoService.salvar(movimentacao);
	}
	
	public void carregarProdutoLinhaEditavel(){
		ItemMovimentacao item = this.movimentacao.getItensMovimentacao().get(0);
		
		if(this.produtoLinha != null){
			item.setProduto(this.produtoLinha);
			item.setQuantidade(1.0);
			item.setDataDeValidade(new Date());
			item.setDataDeRecebimento(new Date());
//			item.setItemProduto(null);
			this.movimentacao.adicionaItemVazio();
			this.produtoLinha = null;
		}
	}
	
	
	public List<Produto> completarProduto(String nome){
		return this.produtoService.porNome(nome.toUpperCase());
	}
	
	
	public Produto getProdutoLinha() {
		return produtoLinha;
	}

	public void setProdutoLinha(Produto produtoLinha) {
		this.produtoLinha = produtoLinha;
	}

	public boolean verificaEdicao(){
		return this.movimentacao.getId() != null;
	}
	
	public Date dataHoje(){
		return new Date();
	}
	
	public void atualizarQuantidade(ItemMovimentacao item, int linha) {

		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1.0);
			} else {
				this.getMovimentacao().getItensMovimentacao().remove(linha);
			}
		}

	}
	
	//get and set
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	
}
