package com.algaworks.pedidovenda.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.algaworks.pedidovenda.model.ItemMovimentacao;
import com.algaworks.pedidovenda.model.Movimentacao;
import com.algaworks.pedidovenda.model.Produto;
import com.algaworks.pedidovenda.model.Usuario;
import com.algaworks.pedidovenda.service.MovimentacaoService;
import com.algaworks.pedidovenda.service.ProdutoService;
import com.algaworks.pedidovenda.service.UsuarioService;
import com.algaworks.pedidovenda.util.jsf.FacesUtil;
import com.algaworks.pedidovenda.validation.MovimentacaoAlteradoEvent;
import com.algaworks.pedidovenda.validation.MovimentacaoEdicao;
import com.algaworks.pedidovenda.validation.SKU;

@Named
@ViewScoped
public class CadastroMovimentacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@MovimentacaoEdicao
	private Movimentacao movimentacao;

	private List<Usuario> vendedores;

	@Inject
	private MovimentacaoService movimentacaoService;

	@Inject
	private UsuarioService usuarioService;


	@Inject
	private ProdutoService produtoService;

	private Produto produtoLinhaEditavel;

	@SKU
	private String sku;


	public CadastroMovimentacaoBean() {
		Limpar();
	}

	public void salvar() {
		try {

			this.movimentacao = this.movimentacaoService.salvar(movimentacao);
		} finally {
			this.movimentacao.adicionaItemVazio();
		}
	}

	public void Limpar() {
		movimentacao = new Movimentacao();

	}

	/**
	 * Metodo responsavel por carregar os vendedores antes da pagina ser
	 * renderizada
	 */

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {
//			this.vendedores = usuarioService.listaVendedores();

			this.movimentacao.adicionaItemVazio();

		//	this.recalcularMovimentacao();

		}
	}


	public boolean isEditando() {
		return this.movimentacao.getId() != null;
	}

	public void recalcularMovimentacao() {

		if (this.movimentacao != null) {
			this.movimentacao.recalcularValorTotal();
		}

	}

	public List<Produto> completarProduto(String nome) {
		return this.produtoService.porNome(nome.toUpperCase());
	}

	public void carregarProdutoLinhaEditavel() {
		ItemMovimentacao item = this.movimentacao.getItensMovimentacao().get(0);
		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.AvisoMessage("Ja existe um item no movimentacao com o produto informado");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				//item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());

				this.movimentacao.adicionaItemVazio();
				this.produtoLinhaEditavel = null;
				this.sku = null;
				this.movimentacao.recalcularValorTotal();
			}
		}
	}

	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;

		for (ItemMovimentacao item : this.getMovimentacao().getItensMovimentacao()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}

		return existeItem;
	}

	public void carregarProdutoPorSKU() {
		if (StringUtils.isNotEmpty(this.sku)) {
			this.produtoLinhaEditavel = this.produtoService.porSku(this.sku);
			this.carregarProdutoLinhaEditavel();
		}

	}

	public void atualizarQuantidade(ItemMovimentacao item, int linha) {

		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getMovimentacao().getItensMovimentacao().remove(linha);
			}
		}

		this.movimentacao.recalcularValorTotal();

	}

	public void removeItem(ItemMovimentacao item,int linha) {

		this.getMovimentacao().getItensMovimentacao().remove(linha);
		//this.getMovimentacao().adicionaItemVazio();
		this.movimentacao.recalcularValorTotal();

	}

	public void movimentacaoAlterado(@Observes MovimentacaoAlteradoEvent event) {
		this.movimentacao = event.getMovimentacao();
	}


	public Date dataHoje() {
		return new Date();
	}

	// get and set
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

}