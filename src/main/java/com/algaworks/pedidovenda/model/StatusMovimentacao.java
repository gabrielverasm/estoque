package com.algaworks.pedidovenda.model;

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
