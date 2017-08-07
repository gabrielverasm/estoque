package br.com.admrica.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.admrica.estoque.model.Produto;
import br.com.admrica.estoque.repository.ProdutoDAO;
import br.com.admrica.estoque.repository.filter.ProdutoParaPesquisa;
import br.com.admrica.estoque.util.jpa.Transactional;
import br.com.admrica.estoque.util.jsf.FacesUtil;

public class ProdutoService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProdutoDAO produtoDAO;

	@Transactional
	public Produto salvar(Produto produto) {
		return produtoDAO.guardar(produto);
	}

	@Transactional
	public void remover(Produto produto) {
		produtoDAO.remover(produto);
	}

	public Produto porId(Long id) {
		return this.produtoDAO.porId(id);
	}

	public List<Produto> filtrados(ProdutoParaPesquisa filtro) {

		List<Produto> lista = null;
		lista = produtoDAO.filtrados(filtro);

		if (lista.isEmpty()) {
			FacesUtil.AvisoMessage("Nenhum produto encontrado!");
			return null;
		}

		return lista;
	}

	public List<Produto> pesquisarPorProdutoAlerta() {
		return this.produtoDAO.pesquisarPorProdutoAlerta();
	}

	public List<Produto> pesquisarPorProdutoQuantidadeCriticaTrintaPorCento() {
		return this.produtoDAO.pesquisarPorProdutoQuantidadeCriticaTrintaPorCento();
	}

	public List<Produto> porNome(String nome) {
		return this.produtoDAO.porNome(nome.toUpperCase());
	}

	// public Produto porSku(String sku) {
	// return this.produtoDAO.porSku(sku);
	// }

	public List<Produto> listarTodos() {
		return this.produtoDAO.listarTodos();
	}

	public Produto porNumero(Long numeroItem) {
		return this.produtoDAO.porNumero(numeroItem);
	}
}
