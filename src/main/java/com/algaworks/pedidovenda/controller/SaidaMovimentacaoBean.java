package com.algaworks.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.pedidovenda.model.Movimentacao;
import com.algaworks.pedidovenda.service.SaidaMovimentacaoService;
import com.algaworks.pedidovenda.validation.MovimentacaoAlteradoEvent;
import com.algaworks.pedidovenda.validation.MovimentacaoEdicao;

@Named
@RequestScoped
public class SaidaMovimentacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@MovimentacaoEdicao
	private Movimentacao movimentacao;

	@Inject
	private SaidaMovimentacaoService saidaMovimentacaoService;

	@Inject
	private Event<MovimentacaoAlteradoEvent> movimentacaoAlteradoEvent;


	public void retirarDoEstoque(){
		this.movimentacao.removerItemVazio();
		this.movimentacao = this.saidaMovimentacaoService.retirarDoEstoque(this.movimentacao);
		this.movimentacaoAlteradoEvent.fire(new MovimentacaoAlteradoEvent(this.movimentacao));
	}
}
