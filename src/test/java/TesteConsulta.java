import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

import com.algaworks.pedidovenda.model.ItemMovimentacao;
import com.algaworks.pedidovenda.model.ItemProduto;
import com.algaworks.pedidovenda.model.Produto;

public class TesteConsulta {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("estoquePU");
		EntityManager em = emf.createEntityManager();
		Session sessao = em.unwrap(Session.class);

//		Usuario usuario = new Usuario();
//		usuario.setId(3L);
		
		Produto produto = new Produto();
		produto = em.find(Produto.class,1L);
		
		
		
		Query query  = em.createQuery("from ItemMovimentacao i where i.movimentacao.id = 1");
		
		List<ItemMovimentacao> itens = query.getResultList(); 
		
		

		for(ItemMovimentacao item : itens){
			System.out.println(item.getMovimentacao().getId()+ " "+item.getProduto().getNome());
			ItemProduto itemProduto = new ItemProduto();
			
			itemProduto.setQuantidade(Double.parseDouble(item.getQuantidade().toString()));
			System.out.println(itemProduto.toString());
		}
		
		
//		for (Date data : valores.keySet()) {
//			System.out.println(data + " : " + valores.get(data));
//		}
		em.close();
	}

}
//
// @SuppressWarnings({ "unchecked"})
// public static Map<Date, BigDecimal> valoresTotaisPorData(
// Integer numeroDeDias, Usuario criadoPor, Session sessao) {
//
// numeroDeDias -= 1;
// Calendar dataInicial = Calendar.getInstance(); // data atual do SO
//
// dataInicial = DateUtils.truncate(dataInicial, Calendar.DAY_OF_MONTH);
// dataInicial.add(Calendar.DAY_OF_MONTH, numeroDeDias * -1);
//
// Map<Date, BigDecimal> resultado = criarMapaVazio(numeroDeDias,
// dataInicial);
//
// Criteria criteria = sessao.createCriteria(Pedido.class);
//
// // ex.: select date(data_criacao) as data, sum(valorTotal) as valor from
// // pedido where
// // data_criacao >= :dataInicial and vendedor_id = : criadoPor
// // group by date(data_criacao)
//
// criteria.setProjection(
// Projections.projectionList()
// // projeção da data
// .add(Projections.sqlGroupProjection(
// "date(data_criacao) as data",
// "date(data_criacao)", new String[] { "data" },
// new Type[] { StandardBasicTypes.DATE }))
// // projeção valor total - o valor total citado abaixo é
// // do objeto
// .add(Projections.sum("valorTotal").as("valor")))
// .add(Restrictions.ge("dataCriacao", dataInicial.getTime()));
//
// if (criadoPor != null) {
// criteria.add(Restrictions.eq("vendedor", criadoPor));
// }
//
// List<DataValor> valoresPorData = criteria
// .setResultTransformer(Transformers.aliasToBean(DataValor.class)).list();
//
// for(DataValor dataValor : valoresPorData){
// resultado.put(dataValor.getData(), dataValor.getValor());
// }
//
// return resultado;
// }
//
// private static Map<Date, BigDecimal> criarMapaVazio(Integer numeroDeDias,
// Calendar dataInicial) {
//
// dataInicial = (Calendar) dataInicial.clone();
// Map<Date, BigDecimal> mapaInicial = new TreeMap<>();
//
// for (int i = 0; i <= numeroDeDias; i++) {
// mapaInicial.put(dataInicial.getTime(), BigDecimal.ZERO);
// dataInicial.add(Calendar.DAY_OF_MONTH, 1);
// }
// return mapaInicial;
// }
//
// }
