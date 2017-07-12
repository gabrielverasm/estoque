package br.com.admrica.estoque.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.admrica.estoque.model.ItemMovimentacao;
import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.model.Produto;
import br.com.admrica.estoque.model.StatusMovimentacao;
import br.com.admrica.estoque.model.Usuario;
import br.com.admrica.estoque.service.MovimentacaoService;
import br.com.admrica.estoque.service.ProdutoService;
import br.com.admrica.estoque.util.jpa.Transactional;
import br.com.admrica.estoque.util.jsf.FacesUtil;
import br.com.admrica.estoque.validation.MovimentacaoAlteradoEvent;
import br.com.admrica.estoque.validation.MovimentacaoEdicao;

@Named
@ViewScoped
public class CadastroMovimentacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Produces
	@MovimentacaoEdicao
	private Movimentacao movimentacao;

	private List<Usuario> vendedores;

	@Inject
	private MovimentacaoService movimentacaoService;

	@Inject
	private ProdutoService produtoService;

	private Produto produtoLinhaEditavel;

	private boolean flag;

	@Inject
	private SaidaMovimentacaoBean saidaMovimentacaoBean;

	@Inject
	private EntradaMovimentacaoBean entradaMovimentacaoBean;

	private Date currentDate = new Date();
	
	private Long numeroItem; 
	
	public Date getCurrentDate() {
		return currentDate;
	}

	public CadastroMovimentacaoBean() {
		Limpar();
	}

	public void salvar() {
		try {

			this.movimentacao = this.movimentacaoService.salvar(movimentacao);
		} finally {
			this.movimentacao.adicionaItemVazio();
		}
	}

	public void Limpar() {
		movimentacao = new Movimentacao();

	}

	public void carregarProdutoPorNumero() {
		if (StringUtils.isNotBlank(String.valueOf(this.numeroItem))) {
			this.produtoLinhaEditavel = this.produtoService.porNumero(this.numeroItem);
			this.carregarProdutoLinhaEditavel();
		}

	}

	/**
	 * Metodo responsavel por carregar os vendedores antes da pagina ser
	 * renderizada
	 */

	public void inicializar() {
		if (FacesUtil.isNotPostback()) {

			this.movimentacao.adicionaItemVazio();

		}
		if (isBaixado() || this.movimentacao.isCancelado()) {
			produtoLinhaEditavel = null;
			movimentacao.removerItemVazio();
		}
	}

	public boolean isEditando() {
		return this.movimentacao.getId() != null;
	}

	public boolean isBaixado() {
		return this.movimentacao.getStatusMovimentacao() == StatusMovimentacao.BAIXADO;
	}

	public void recalcularMovimentacao() {

		if (this.movimentacao != null) {
			this.movimentacao.recalcularValorTotal();
		}

	}

	public List<Produto> completarProduto(String nome) {
		return this.produtoService.porNome(nome.toUpperCase());
	}

	public void carregarProdutoLinhaEditavel() {
		ItemMovimentacao item = this.movimentacao.getItensMovimentacao().get(0);
		if (this.produtoLinhaEditavel != null) {
			if (this.existeItemComProduto(this.produtoLinhaEditavel)) {
				FacesUtil.AvisoMessage("Ja existe um item no movimentacao com o produto informado");
			} else {
				item.setProduto(this.produtoLinhaEditavel);
				// item.setValorUnitario(this.produtoLinhaEditavel.getValorUnitario());

				this.movimentacao.adicionaItemVazio();
				this.produtoLinhaEditavel = null;
				this.movimentacao.recalcularValorTotal();
			}
		}
	}

	private boolean existeItemComProduto(Produto produto) {
		boolean existeItem = false;

		for (ItemMovimentacao item : this.getMovimentacao().getItensMovimentacao()) {
			if (produto.equals(item.getProduto())) {
				existeItem = true;
				break;
			}
		}

		return existeItem;
	}

	public void atualizarQuantidade(ItemMovimentacao item, int linha) {

		if (item.getQuantidade() < 1) {
			if (linha == 0) {
				item.setQuantidade(1);
			} else {
				this.getMovimentacao().getItensMovimentacao().remove(linha);
			}
		}

		this.movimentacao.recalcularValorTotal();

	}

	public void atualizarDataValidade(ItemMovimentacao item, int linha) {

		if (item.getDataDeValidade() == null) {
			item.setDataDeValidade(new Date());
		}

		// this.movimentacao.recalcularValorTotal();

	}

	@Transactional
	public void removeItem(ItemMovimentacao item, int linha) {

		this.getMovimentacao().getItensMovimentacao().remove(linha);
		// this.getMovimentacao().adicionaItemVazio();
		this.movimentacao.recalcularValorTotal();

	}

	public void movimentacaoAlterado(@Observes MovimentacaoAlteradoEvent event) {
		this.movimentacao = event.getMovimentacao();
	}

	public Date dataHoje() {
		return new Date();
	}

	public boolean mostraCampo() {
		boolean retorno = false;
		flag = false;
		if (movimentacao.getOperacao().equals("entrada")) {
			retorno = true;
			flag = true;
		}

		// System.out.println(movimentacao.getOperacao() + " - flag - " + flag);
		return retorno;
	}

	public void entradaSaida() {
		if (movimentacao.getOperacao().equals("entrada")) {
			entradaMovimentacaoBean.adicionarNoEstoque();
		} else {
			saidaMovimentacaoBean.retirarDoEstoque();
		}
	}

	// get and set
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public List<Usuario> getVendedores() {
		return vendedores;
	}

	public Produto getProdutoLinhaEditavel() {
		return produtoLinhaEditavel;
	}

	public void setProdutoLinhaEditavel(Produto produtoLinhaEditavel) {
		this.produtoLinhaEditavel = produtoLinhaEditavel;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Long getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(Long numeroItem) {
		this.numeroItem = numeroItem;
	}

}