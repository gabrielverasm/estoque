package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.ItemMovimentacao;
import com.algaworks.pedidovenda.model.Movimentacao;
import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.service.MovimentacaoService;
import com.algaworks.pedidovenda.service.ProdutoService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.validation.MovimentacaoAlteradoEvent;
import com.algaworks.pedidovenda.validation.MovimentacaoEdicao;

@Named
@ViewScoped
public class CadastroMovimentacaoBean implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Produces
	@MovimentacaoEdicao
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
	
	public List<Produto> completarProduto(String nome){
		return this.produtoService.porNome(nome);
	}
	
	public void carregarProdutoLinhaEditavel(){
		ItemMovimentacao item = this.movimentacao.getItensMovimentacao().get(0);
		
		if(this.produtoLinha != null){
			//dataDeRecebimento, dataDeValidade, id_movimentacao, id_produto, quantidade, id_usuario
//			item.setDataDeRecebimento(new Date());
//			item.setDataDeValidade(new Date());
//			item.setProduto(this.produtoLinha);
//			item.setQuantidade(1.0);
//			item.setMovimentacao(this.movimentacao);
//			item.setItemProduto(null);
//			this.movimentacao.adicionaItemVazio();
//			this.produtoLinha = null;
		}
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

	
	public void MovimentacaoAlterado(@Observes MovimentacaoAlteradoEvent event) {
		this.movimentacao = event.getMovimentacao();
	}
	
	//get and set
	
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
	

	
	//get and set
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	
}
