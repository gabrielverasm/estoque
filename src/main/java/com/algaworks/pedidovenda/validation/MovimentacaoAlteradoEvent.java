package com.algaworks.pedidovenda.validation;

import com.algaworks.pedidovenda.model.Movimentacao;

public class MovimentacaoAlteradoEvent {
	
	private Movimentacao movimentacao;
	
	public MovimentacaoAlteradoEvent(Movimentacao movimentacao){
	  this.movimentacao = movimentacao;	
	}
	
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

}
