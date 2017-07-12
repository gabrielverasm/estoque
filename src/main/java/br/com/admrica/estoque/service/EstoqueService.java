package br.com.admrica.estoque.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.admrica.estoque.model.ItemMovimentacao;
import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.repository.MovimentacaoDAO;

public class EstoqueService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	//private PedidoDAO pedidoDAO;
	private MovimentacaoDAO movimentacaoDAO;

//	@Transactional
//	public void baixarItensEstoque(Pedido pedido) {
//		pedido = this.pedidoDAO.porId(pedido.getId());
//		
//		for(ItemPedido item : pedido.getItens()){
//			item.getProduto().baixarEstoque(item.getQuantidade());
//		}
//	}

	public void baixarItensEstoque(Movimentacao movimentacao) {
		movimentacao = this.movimentacaoDAO.porId(movimentacao.getId());
		
		for(ItemMovimentacao item : movimentacao.getItensMovimentacao()){
			item.getProduto().baixarEstoque(item.getQuantidade());
		}
	}
	
	public void adicionarItemNoEstoque(Movimentacao movimentacao){
		movimentacao = this.movimentacaoDAO.porId(movimentacao.getId());
		
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
