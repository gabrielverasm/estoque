package br.com.admrica.estoque.repository;

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

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.repository.filter.MovimentacaoParaPesquisa;

public class MovimentacaoDAO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Movimentacao> filtrados(MovimentacaoParaPesquisa filtro) {
		Session session = this.manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Movimentacao.class);

		if (filtro.getNumeroDe() != null) {
			// id deve ser maior ou igual (ge = greater or equals) a
			// movimentacao.numeroDe
			criteria.add(Restrictions.ge("id", filtro.getNumeroDe()));
		}

		if (filtro.getNumeroAte() != null) {
			// id deve ser menor ou igual (le = lower or equals) a
			// movimentacao.numeroDe
			criteria.add(Restrictions.le("id", filtro.getNumeroAte()));
		}

		if (filtro.getDataCriacaoDe() != null) {
			// id deve ser maior ou igual (ge = greater or equals) a
			// movimentacao.numeroDe
			criteria.add(Restrictions.ge("dataCriacao", filtro.getDataCriacaoDe()));
		}

		if (filtro.getDataCriacaoAte() != null) {
			// id deve ser menor ou igual (le = lower or equals) a
			// movimentacao.numeroDe
			criteria.add(Restrictions.le("dataCriacao", filtro.getDataCriacaoAte()));
		}

		if (StringUtils.isNotBlank(filtro.getDescricao())) {
			criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
		}

		if (filtro.getStatuses() != null && filtro.getStatuses().length > 0) {
			// adicionamos uma restrição "in", passando um array de constantes
			// da ENUM StatusMovimentacao
			criteria.add(Restrictions.in("statusMovimentacao", filtro.getStatuses()));
		}

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
