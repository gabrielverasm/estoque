package br.com.admrica.estoque.model;

public enum Unidade {
	UNIDADE("Und - Unidade"), QUILOGRAMA("Kg - Quilograma"), LITRO("l - Litro");

	private String descricao;

	Unidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
