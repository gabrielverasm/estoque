package com.algaworks.pedidovenda.model;

public enum Grupo {
	ALIMENTACAO("Alimentação"), LIMPEZA("Limpeza"), NUTRICAO("Nutrição");

	private String descricao;

	Grupo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
