package br.com.admrica.estoque.util.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.jdbc.Work;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ExecutorRelatorioSemfiltro implements Work {

	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> parametros = new HashMap<>();
	private String nomeArquivoSaida;

	private boolean relatorioGerado;

	public ExecutorRelatorioSemfiltro(String caminhoRelatorio, HttpServletResponse response, String nomeArquivoSaida) {
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.nomeArquivoSaida = nomeArquivoSaida;
	}

	@Override
	public void execute(Connection connection) throws SQLException {
		try {
			InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);

			JasperPrint print = JasperFillManager.fillReport(relatorioStream, parametros, connection);
			this.relatorioGerado = print.getPages().size() > 0;

			if (this.relatorioGerado) {
				JRExporter exportador = new JRPdfExporter();
				exportador.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
				exportador.setParameter(JRExporterParameter.JASPER_PRINT, print);

				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "attachment;filename=\"" + this.nomeArquivoSaida + "\"");
				exportador.exportReport();

			}
		} catch (Exception e) {
			throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
		}
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}
}
