package br.com.admrica.estoque.controller;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admrica.estoque.model.Grupo;
import br.com.admrica.estoque.model.Produto;
import br.com.admrica.estoque.model.Status;
import br.com.admrica.estoque.model.Unidade;
import br.com.admrica.estoque.service.ProdutoService;
import br.com.admrica.estoque.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoService cadastroProdutoService;

	private Produto produto;

	public CadastroProdutoBean() {
		limpar();
	}

	// esse metodo é chamado antes de renderizar a pagina
	public void inicializar() { // carrega as categorias
		if (FacesUtil.isNotPostback()) {
		}
	}

	private void limpar() {
		produto = new Produto();
	}

	public void salvar() {
		this.produto = cadastroProdutoService.salvar(this.produto);
		limpar();

		FacesUtil.InfoMessage("Produto salvo com sucesso!");
	}

	public boolean verificaEdicao() {
		return this.produto.getId() != null;
	}

	public Unidade[] getUnidades() {
		return Unidade.values();
	}

	public Grupo[] getGrupos() {
		return Grupo.values();
	}

	public Status[] getstatusProduto() {
		return Status.values();
	}

	// get and set
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}