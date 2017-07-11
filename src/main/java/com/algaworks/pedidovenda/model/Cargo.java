package com.algaworks.pedidovenda.model;

public enum Cargo {
	INFORMATICA("Informática"), DIRECAO("Direção"), ESTOQUE("Estoque");

	private String descricao;

	Cargo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
