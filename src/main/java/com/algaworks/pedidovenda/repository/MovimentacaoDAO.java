package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.algaworks.pedidovenda.model.Movimentacao;
import com.algaworks.pedidovenda.service.NegocioException;
import com.algaworks.pedidovenda.util.jpa.Transactional;

public class MovimentacaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Movimentacao guardar(Movimentacao movimentacao) {
		return manager.merge(movimentacao);
	}

	@Transactional
	public void remover(Movimentacao movimentacao) {
		try {
			movimentacao = porId(movimentacao.getId());
			manager.remove(movimentacao);
			manager.flush();

		} catch (PersistenceException e) {
			// TODO: handle exception
			throw new NegocioException("Movimentacao nao pode ser excluido");
		}
	}
	
	

	//implementar pesquisa por data
	public Movimentacao porSku(String sku) {
		try {
			return manager
					.createQuery("from Movimentacao where upper(sku) = :sku",
							Movimentacao.class)
					.setParameter("sku", sku.toUpperCase()).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	
	public Movimentacao porId(Long id) {
		return manager.find(Movimentacao.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Movimentacao> porNome(String nome) {
		String jpql = "from Movimentacao p where p.nome like :nome ORDER BY nome ASC";
		Query query = manager.createQuery(jpql, Movimentacao.class);
		query.setParameter("nome", "%" + nome.toUpperCase() + "%");

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Movimentacao> listarTodos(){
		String jpql = "from Movimentacao ORDER BY nome ASC";
		Query query = manager.createQuery(jpql,Movimentacao.class);
		return query.getResultList();
	}

}
