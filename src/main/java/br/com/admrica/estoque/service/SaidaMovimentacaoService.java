package br.com.admrica.estoque.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.model.StatusMovimentacao;
import br.com.admrica.estoque.repository.MovimentacaoDAO;
import br.com.admrica.estoque.util.jpa.Transactional;
import br.com.admrica.estoque.util.jsf.FacesUtil;

public class SaidaMovimentacaoService implements Serializable {

	private static final long serialVersionUID = 1L;

	// @Inject
	// private MovimentacaoService movimentacaoService;

	@Inject
	private EstoqueService estoqueService;

	@Inject
	private MovimentacaoDAO movimentacaoDAO;

	@Transactional
	public Movimentacao retirarDoEstoque(Movimentacao movimentacao) {

		try {
			this.estoqueService.baixarItensEstoque(movimentacao);

			movimentacao.setStatusMovimentacao(StatusMovimentacao.BAIXADO);
			movimentacao = this.movimentacaoDAO.salvar(movimentacao);
			FacesUtil.InfoMessage("Movimentação baixada com sucesso.");
		} catch (Exception e) {
			FacesUtil.ErrorMessage("Erro ao tentar baixar o movimentacao");
		}

		return movimentacao;
	}

}
