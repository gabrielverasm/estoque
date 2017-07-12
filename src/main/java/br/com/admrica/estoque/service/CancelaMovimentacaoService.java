package br.com.admrica.estoque.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.model.StatusMovimentacao;
import br.com.admrica.estoque.repository.MovimentacaoDAO;
import br.com.admrica.estoque.util.jpa.Transactional;
import br.com.admrica.estoque.util.jsf.FacesUtil;

public class CancelaMovimentacaoService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private MovimentacaoDAO movimentacaoDAO;
	
	@Inject
	private EstoqueService estoqueService;
	
	@Transactional
	public Movimentacao cancelar(Movimentacao movimentacao) {
		movimentacao = this.movimentacaoDAO.porId(movimentacao.getId());

//		if (pedido.isNaoCancelavel()) {
//			throw new NegocioException("Pedido nao pode ser cancelado  no  Status" + pedido.getStatus().getDescricao());
//		}
//
//		if (parcelaDAO.verificaPedidopago(pedido)) {
//			throw new NegocioException("Pedido nao pode ser cancelado  ja existe parcela paga");
//		}
//
//		if (pedido.isEmitido()) {
//			this.estoqueService.retornarItensEstoque(pedido);
//		}
		
		if(movimentacao.isPendente()){
			throw new NegocioException("A movimentação não pode ser cancelada, quando Status estiver PEDENTE");
		}
		
		if(movimentacao.isOperacaoEntrada()){
			//retirar do estoque
			this.estoqueService.baixarItensEstoque(movimentacao);
		}else{
			this.estoqueService.adicionarItemNoEstoque(movimentacao);
		}

		movimentacao.setStatusMovimentacao(StatusMovimentacao.CANCELADO);
		movimentacao = movimentacaoDAO.salvar(movimentacao);

		FacesUtil.AvisoMessage("Movimentação cancelada");
		return movimentacao;
	}


}
