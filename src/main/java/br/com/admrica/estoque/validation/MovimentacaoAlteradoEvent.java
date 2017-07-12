package br.com.admrica.estoque.validation;

import br.com.admrica.estoque.model.Movimentacao;

public class MovimentacaoAlteradoEvent {
	
	private Movimentacao movimentacao;
	
	public MovimentacaoAlteradoEvent(Movimentacao movimentacao){
	  this.movimentacao = movimentacao;	
	}
	
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

}
