package br.com.admrica.estoque.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.service.EntradaMovimentacao;
import br.com.admrica.estoque.validation.MovimentacaoAlteradoEvent;
import br.com.admrica.estoque.validation.MovimentacaoEdicao;

@Named
@RequestScoped
public class EntradaMovimentacaoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	@MovimentacaoEdicao
	private Movimentacao movimentacao;

	@Inject
	private EntradaMovimentacao entradaMovimentacao;

	@Inject
	private Event<MovimentacaoAlteradoEvent> movimentacaoAlteradoEvent;


	public void adicionarNoEstoque(){
		this.movimentacao = this.entradaMovimentacao.realizarEntrada(this.movimentacao);
		this.movimentacaoAlteradoEvent.fire(new MovimentacaoAlteradoEvent(this.movimentacao));
	}
}
