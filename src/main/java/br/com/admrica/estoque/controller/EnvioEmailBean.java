package br.com.admrica.estoque.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;

import br.com.admrica.estoque.util.jsf.FacesUtil;
import br.com.admrica.estoque.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;

	public void enviarEmail() {
		MailMessage message = mailer.novaMensagem();
		
		//message.to(this.alerta.getUsuario()).getEmail().subject("O produto...");
		message.to("gabrielverasm@gmail.com").subject("Alerta falta de produtos").bodyHtml("Email teste").send();
		
		FacesUtil.InfoMessage("E-mail envido com sucesso!");
	}

}
