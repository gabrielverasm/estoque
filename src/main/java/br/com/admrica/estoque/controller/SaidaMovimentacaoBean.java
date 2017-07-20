package br.com.admrica.estoque.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.service.EnvioEmailService;
import br.com.admrica.estoque.service.SaidaMovimentacaoService;
import br.com.admrica.estoque.validation.MovimentacaoAlteradoEvent;
import br.com.admrica.estoque.validation.MovimentacaoEdicao;

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

	@Inject
	private EnvioEmailService envioEmailService;

	public void retirarDoEstoque() {

		this.movimentacao.removerItemVazio();
		this.movimentacao = this.saidaMovimentacaoService.retirarDoEstoque(this.movimentacao);
		this.envioEmailService.verificaProdutosComAlerta(this.movimentacao);
		this.movimentacaoAlteradoEvent.fire(new MovimentacaoAlteradoEvent(this.movimentacao));

	}
}
