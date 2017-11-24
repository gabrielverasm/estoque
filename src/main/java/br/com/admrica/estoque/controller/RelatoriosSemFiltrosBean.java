package br.com.admrica.estoque.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import br.com.admrica.estoque.util.jsf.FacesUtil;
import br.com.admrica.estoque.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatoriosSemFiltrosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status = "ATIVO";

	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	public void emitirRelatorioDeProdutos() {

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("status", this.status);
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/produtos.jasper",
				this.response, parametros, "Relatorio de Produtos.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);

		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.ErrorMessage("A execução do relatório não retornou dados.");
		}
	}

}
