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

	public void verificaQuantidadeProdutoEstoque(Movimentacao movimentacao) {
		movimentacao = this.movimentacaoDAO.porId(movimentacao.getId());
		List<Produto> produtos = new ArrayList<>();

		for (ItemMovimentacao item : movimentacao.getItensMovimentacao()) {
			if (item.getProduto().getQuantidade() <= item.getProduto().getEstoqueMinimo()) {
				produtos.add(item.getProduto());
			}
		}

		if (!produtos.isEmpty())
			enviaEmailComProdutos(produtos);
	}

	public void enviaEmailComProdutos(List<Produto> produtos) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

		// MONTANDO A MGS
		String msg = " ";
		for (Produto item : produtos) {
			msg += " Produto: " + item.getNome() + " Qtd: " + item.getQuantidade() + "\n";
		}

		// PEGANDO OS DIRETORES
		List<Usuario> diretores;
		diretores = this.usuarioDAO.pesquisarPorCargo();

		MailMail mm = (MailMail) context.getBean("mailMail");

		for (Usuario diretor : diretores)
			mm.sendMail("alerta@gmail.com", diretor.getEmail(), "Produtos que estão acabando", msg);
	}

}
