package br.com.admrica.estoque.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.service.CancelaMovimentacaoService;
import br.com.admrica.estoque.validation.MovimentacaoAlteradoEvent;
import br.com.admrica.estoque.validation.MovimentacaoEdicao;

@Named
@RequestScoped
public class CancelaMovimentacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CancelaMovimentacaoService cancelaMovimentacaoService;
	
	@Inject
	private Event<MovimentacaoAlteradoEvent> movimentacaoAlteradoEvent;
	
	@Inject
	@MovimentacaoEdicao
	private Movimentacao movimentacao;
	
	public void cancelarMovimentacao(){
		this.movimentacao = this.cancelaMovimentacaoService.cancelar(movimentacao);
		this.movimentacaoAlteradoEvent.fire(new MovimentacaoAlteradoEvent(movimentacao));
	}

}
