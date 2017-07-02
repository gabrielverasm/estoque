package com.algaworks.pedidovenda.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.algaworks.pedidovenda.model.ItemMovimentacao;
import com.algaworks.pedidovenda.model.Movimentacao;
import com.algaworks.pedidovenda.repository.MovimentacaoDAO;

public class EstoqueService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	//private PedidoDAO pedidoDAO;
	private MovimentacaoDAO MovimentacaoDAO;

//	@Transactional
//	public void baixarItensEstoque(Pedido pedido) {
//		pedido = this.pedidoDAO.porId(pedido.getId());
//		
//		for(ItemPedido item : pedido.getItens()){
//			item.getProduto().baixarEstoque(item.getQuantidade());
//		}
//	}

	public void adicionarItemNoEstoque(Movimentacao movimentacao){
		movimentacao = this.MovimentacaoDAO.porId(movimentacao.getId());
		
		for(ItemMovimentacao item : movimentacao.getItensMovimentacao()){
			item.getProduto().adicionarEstoque(item.getQuantidade());
		}
		
		
	}

//	public void retornarItensEstoque(Pedido pedido) {
//		pedido = this.pedidoDAO.porId(pedido.getId());
//		
//		for(ItemPedido item : pedido.getItens()){
//			item.getProduto().adicionarEstoque(item.getQuantidade());
//		}
//	}

}
