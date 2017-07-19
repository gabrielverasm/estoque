package br.com.admrica.estoque.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.admrica.estoque.controller.MailMail;
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

	private Movimentacao movimentacao;

	public void verificaQuantidadeProdutoEstoque(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
		movimentacao = this.movimentacaoDAO.porId(movimentacao.getId());
		List<Produto> produtosAlerta = new ArrayList<>();

		for (ItemMovimentacao item : movimentacao.getItensMovimentacao()) {
			if (item.getProduto().getQuantidade() <= item.getProduto().getEstoqueMinimo()) {
				produtosAlerta.add(item.getProduto());
			}
		}

		if (!produtosAlerta.isEmpty())
			enviaEmailComProdutos(produtosAlerta);
	}

	public void enviaEmailComProdutos(List<Produto> produtos) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

		// MONTANDO A MGS
		String msg = "A movimentação de número: " + "<strong>" + movimentacao.getId() + "</strong>"
				+ ", e descrição: <strong>" + movimentacao.getDescricao()
				+ "</strong>, deu saída em produtos com estoque crítico." + "\n" + "\n"
				+ "<strong>Os produtos abaixo listados estão com estoque crítico e necessitam de aquisição.</strong>"
				+ "\n" + "\n" + "\n";
		for (Produto item : produtos) {
			msg += "<strong>Produto:</strong> " + item.getNome() + "       <strong>Estoque:</strong> "
					+ item.getQuantidade() + "\n";
		}

		// PEGANDO USUARIO QUE RECEBE ALERTA
		List<Usuario> usuariosAlerta;
		usuariosAlerta = this.usuarioDAO.pesquisaUsuarioRecebeAlerta();

		// Pegando destinatários
		String[] destinatarios = new String[usuariosAlerta.size()];

		for (int i = 0; i < usuariosAlerta.size(); i++) {
			destinatarios[i] = usuariosAlerta.get(i).getEmail();
		}

		// Enviando e-mail
		MailMail mm = (MailMail) context.getBean("mailMail");
		mm.sendMail(destinatarios, "Alerta - Estoque insuficiente (Movimentação: " + movimentacao.getId() + ").", msg);
	}

}
