package br.com.admrica.estoque.model;

public enum Unidade {
	UNIDADE("Und."), QUILOGRAMA("Kg"), LITRO("l");

	private String descricao;

	Unidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
