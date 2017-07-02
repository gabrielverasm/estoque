package com.algaworks.pedidovenda.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

//import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.algaworks.pedidovenda.model.Movimentacao;
import com.algaworks.pedidovenda.repository.filter.MovimentacaoParaPesquisa;

public class MovimentacaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Movimentacao> filtrados(MovimentacaoParaPesquisa movimentacao) {
		Session session = this.manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Movimentacao.class)
				.createAlias("cliente", "c") // associação join com cliente e
												// nomeamos como "c"
				.createAlias("vendedor", "v"); // associação join com usuario e
												// nomeamos como "v"

		if (movimentacao.getNumeroDe() != null) {
			// id deve ser maior ou igual (ge = greater or equals) a
			// movimentacao.numeroDe
			criteria.add(Restrictions.ge("id", movimentacao.getNumeroDe()));
		}

		if (movimentacao.getNumeroAte() != null) {
			// id deve ser menor ou igual (le = lower or equals) a
			// movimentacao.numeroDe
			criteria.add(Restrictions.le("id", movimentacao.getNumeroAte()));
		}

		if (movimentacao.getDataCriacaoDe() != null) {
			// id deve ser maior ou igual (ge = greater or equals) a
			// movimentacao.numeroDe
			criteria.add(Restrictions.ge("dataCriacao",
					movimentacao.getDataCriacaoDe()));
		}

		if (movimentacao.getDataCriacaoAte() != null) {
			// id deve ser menor ou igual (le = lower or equals) a
			// movimentacao.numeroDe
			criteria.add(Restrictions.le("dataCriacao",
					movimentacao.getDataCriacaoAte()));
		}

		if (StringUtils.isNotBlank(movimentacao.getNomeCliente())) {
			// acessamos o nome do cliente associado ao movimentacao pelo alias "c"
			criteria.add(Restrictions.ilike("c.nome", movimentacao.getNomeCliente(),
					MatchMode.ANYWHERE));
		}

		if (StringUtils.isNotBlank(movimentacao.getNomeVendedor())) {
			// acessamos o nome do vendedor associado ao movimentacao pelo alias "v"
			criteria.add(Restrictions.ilike("v.nome", movimentacao.getNomeVendedor(),
					MatchMode.ANYWHERE));
		}

	//	if (movimentacao.getStatuses() != null && movimentacao.getStatuses().length > 0) {
			// adicionamos uma restrição "in", passando um array de constantes
			// da ENUM StatusMovimentacao
//			criteria.add(Restrictions.in("status", movimentacao.getStatuses()));
//		}

		return criteria.addOrder(Order.desc("id")).list();
	}

	public Movimentacao porId(Long id) {
		return this.manager.find(Movimentacao.class, id);
	}
	
	public Movimentacao porId(Movimentacao movimentacao) {
		return this.manager.find(Movimentacao.class, movimentacao.getId());
	}

	

	// crud
	public Movimentacao salvar(Movimentacao movimentacao) {
		return manager.merge(movimentacao);
	}


}
