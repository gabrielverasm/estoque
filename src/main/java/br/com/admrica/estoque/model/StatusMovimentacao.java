package br.com.admrica.estoque.model;

public enum StatusMovimentacao {
	PENDENTE("Pendente"), BAIXADO("Baixado"), CANCELADO("Cancelado");

	private String descricao;

	StatusMovimentacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
