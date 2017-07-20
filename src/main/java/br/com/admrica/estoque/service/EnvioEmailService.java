package br.com.admrica.estoque.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.admrica.estoque.controller.EnvioMovimentacaoBean;
import br.com.admrica.estoque.model.ItemMovimentacao;
import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.model.Produto;
import br.com.admrica.estoque.model.Usuario;
import br.com.admrica.estoque.repository.MovimentacaoDAO;
import br.com.admrica.estoque.repository.UsuarioDAO;

public class EnvioEmailService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Inject
	private MovimentacaoDAO movimentacaoDAO;

	@Inject
	private EnvioMovimentacaoBean envioMovimentacaoBean;

	public void verificaProdutosComAlerta(Movimentacao movimentacao) {
		movimentacao = this.movimentacaoDAO.porId(movimentacao.getId());
		List<Produto> produtosAlerta = new ArrayList<>();

		for (ItemMovimentacao item : movimentacao.getItensMovimentacao()) {
			if (item.getProduto().getQuantidade() <= item.getProduto().getEstoqueMinimo()) {
				produtosAlerta.add(item.getProduto());
			}
		}

		if (!produtosAlerta.isEmpty()) {
			// PEGANDO USUARIO QUE RECEBE ALERTA
			List<Usuario> usuariosAlerta;
			usuariosAlerta = this.usuarioDAO.pesquisaUsuarioRecebeAlerta();

			// Pegando destinatários
			String[] destinatarios = new String[usuariosAlerta.size()];
			for (int i = 0; i < usuariosAlerta.size(); i++) {
				destinatarios[i] = usuariosAlerta.get(i).getEmail();
			}

			this.envioMovimentacaoBean.enviaAlertaMovimentacao(movimentacao, produtosAlerta, destinatarios);
		}

	}
}
