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
	private ProdutoDAO produtos;
	
	@Transactional
	public Produto salvar(Produto produto) {
		return produtos.guardar(produto);
	}
	
	@Transactional
	public void remover(Produto produto){
		produtos.remover(produto);
	}
	
	public Produto porId(Long id){
		return this.produtos.porId(id);
	}
	
	public List<Produto> filtrados(ProdutoParaPesquisa filtro) {
		List<Produto> lista = null;
		
//		if(StringUtils.isNotBlank(filtro.getSku()) && StringUtils.isNotBlank(filtro.getNome())){
//			FacesUtil.AvisoMessage("Preencha apenas um dos campos abaixo");
//			return null;
//		}
		
		lista = produtos.filtrados(filtro);
		
		if(lista.isEmpty()){
			FacesUtil.AvisoMessage("Nenhum produto encontrado!");
			return null;
		}
		
		return lista;
	}
	
	
	public List<Produto> porNome(String nome){
		return this.produtos.porNome(nome.toUpperCase());
	}
	
	public Produto porSku(String sku){
		return this.produtos.porSku(sku);
	}
	
	public List<Produto> listarTodos(){
		return this.produtos.listarTodos();
	}
}
