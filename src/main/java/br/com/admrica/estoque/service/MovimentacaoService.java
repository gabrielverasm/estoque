package br.com.admrica.estoque.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.repository.MovimentacaoDAO;
import br.com.admrica.estoque.repository.filter.MovimentacaoParaPesquisa;
import br.com.admrica.estoque.util.jpa.Transactional;
import br.com.admrica.estoque.util.jsf.FacesUtil;

@SuppressWarnings("serial")
public class MovimentacaoService implements Serializable {

	@Inject
	private MovimentacaoDAO movimentacaoDAO;
	
	public Movimentacao porId(Movimentacao movimentacao){
		return movimentacaoDAO.porId(movimentacao);
	}

	public List<Movimentacao> filtrados(MovimentacaoParaPesquisa movimentacao) {
		if (movimentacao.getNumeroDe() == null && movimentacao.getNumeroAte() == null) {
			movimentacao.setNumeroDe(null);
			movimentacao.setNumeroAte(null);
		}
		return movimentacaoDAO.filtrados(movimentacao);
	}

	@Transactional
	public Movimentacao salvar(Movimentacao movimentacao) {
		movimentacao.removerItemVazio();

		try {
			if (movimentacao.isNovo()) {
				movimentacao.setDataCriacao(new Date());
			}

			//movimentacao.recalcularValorTotal();

//			if (movimentacao.isNaoAlteravel()) {
//				throw new NegocioException(
//						"Movimentacao nao pode ser alterado no status "
//								+ movimentacao.getStatus().getDescricao());
//			}

//			if (movimentacao.getItens().isEmpty()) {
//				// FacesUtil.AvisoMessage("O Movimentacao deve ter pelo menos um item");
//				throw new NegocioException(
//						"O Movimentacao deve ter pelo menos um item");
//			}

//			if (movimentacao.isValorTotalNegativo()) {
//				// FacesUtil.ErrorMessage("O valor total nao pode ser negativo");
//				throw new NegocioException(
//						"O valor total nao pode ser negativo");
//			}


			movimentacao = this.movimentacaoDAO.salvar(movimentacao);

			FacesUtil.InfoMessage("Movimentacao salvo com sucesso!");

		} catch (Exception e) {
			FacesUtil.ErrorMessage("Erro ao tentar salva o movimentacao");
		}

		return movimentacao;

	}

}
