package br.com.admrica.estoque.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.model.StatusMovimentacao;
import br.com.admrica.estoque.repository.MovimentacaoDAO;
import br.com.admrica.estoque.util.jpa.Transactional;
import br.com.admrica.estoque.util.jsf.FacesUtil;

public class EntradaMovimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private MovimentacaoDAO movimentacaoDAO;

	@Inject
	private EstoqueService estoqueService;

	@Transactional
	public Movimentacao realizarEntrada(Movimentacao movimentacao) {
		try {

			movimentacao = this.movimentacaoDAO.porId(movimentacao);

			if (movimentacao.getOperacao().equals("entrada")) {
				movimentacao.setStatusMovimentacao(StatusMovimentacao.BAIXADO);
				this.estoqueService.adicionarItemNoEstoque(movimentacao);
				// movimentacao.setDataCriacao(new Date());
				movimentacao = this.movimentacaoDAO.salvar(movimentacao);
				FacesUtil.InfoMessage("Produto(s) adicionado(s) no estoque");
			}
		} catch (Exception e) {
			FacesUtil.ErrorMessage("Erro ao tentar adicionar produto no estoque");
		}

		return movimentacao;
	}
}
