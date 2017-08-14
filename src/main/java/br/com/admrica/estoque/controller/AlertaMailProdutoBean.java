package br.com.admrica.estoque.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.model.Produto;
import br.com.admrica.estoque.util.jsf.FacesUtil;
import br.com.admrica.estoque.util.mail.Mailer;

@Named
@RequestScoped
public class AlertaMailProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;

	// @Inject
	// @MovimentacaoEdicao
	// private Movimentacao movimentacao;

	public void enviaAlertaProduto(Movimentacao movimentacao, List<Produto> produtos, String[] destinatarios) {
		try {

			for (Produto item : produtos) {
				MailMessage message = mailer.novaMensagem();

				message.to(destinatarios).subject("Alerta estoque insuficiente! (" + item.getNome() + ").")
						.bodyHtml("<br><br><center>O produto: <strong>" + item.getNome()
								+ "</strong> está com quantidade baixa (<strong>Estoque atual: " + item.getQuantidade()
								+ "</strong>).<br><br><br>Movimentação (<strong>" + movimentacao.getId() + "</strong>): <strong>"
								+ movimentacao.getDescricao() + "</strong>.").send();
			}
			FacesUtil.InfoMessage("Alerta enviado por e-mail.");
		} catch (Exception e) {
			FacesUtil.ErrorMessage("Erro ao enviar alerta por e-mail!");
		}
	}

}
