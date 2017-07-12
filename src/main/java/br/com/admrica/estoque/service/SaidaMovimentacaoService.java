package br.com.admrica.estoque.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.model.StatusMovimentacao;
import br.com.admrica.estoque.repository.MovimentacaoDAO;
import br.com.admrica.estoque.util.jpa.Transactional;

public class SaidaMovimentacaoService implements Serializable {

	private static final long serialVersionUID = 1L;


//	@Inject
//	private MovimentacaoService movimentacaoService;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Inject
	private MovimentacaoDAO movimentacaoDAO;

	@Transactional
	public Movimentacao retirarDoEstoque(Movimentacao movimentacao) {

		
		//movimentacao = this.pedidoService.salvar(pedido);

//		if (movimentacao.isNaoEmissivel()) {
//			throw new NegocioException(
//					"Pedido não pode ser emitido com status: "
//							+ pedido.getStatus().getDescricao());
//		}
		
		this.estoqueService.baixarItensEstoque(movimentacao);
		
		movimentacao.setStatusMovimentacao(StatusMovimentacao.BAIXADO);
		movimentacao = this.movimentacaoDAO.salvar(movimentacao);

		return movimentacao;
	}

}
