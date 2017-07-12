package br.com.admrica.estoque.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "movimentacao")
public class Movimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private StatusMovimentacao statusMovimentacao = StatusMovimentacao.PENDENTE;
	
	@Column(length = 150)
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	private Date prazoDeEntrega;

	private String operacao;
	
	@OneToMany(mappedBy = "movimentacao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemMovimentacao> itensMovimentacao = new ArrayList<>();
	//

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date dataCriacao;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_emissao")
	private Date dataEmissao;

	@Column(columnDefinition = "text")
	private String observacao;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_entrega")
	private Date dataEntrega;

	@Column(name = "valor_frete", precision = 10, scale = 2)
	private BigDecimal valorFrete = BigDecimal.ZERO;

	@Column(name = "valor_desconto", precision = 10, scale = 2)
	private BigDecimal valorDesconto = BigDecimal.ZERO;

	@Column(name = "valor_total",  precision = 10, scale = 2)
	private BigDecimal valorTotal = BigDecimal.ZERO;

	@Column(name="numero_parcela")
	private int numeroParcela = 0;

	@ManyToOne
	@JoinColumn(name = "id_usuario_vendedor")
	private Usuario vendedor;


	@Transient
	public boolean isNovo() {
		return getId() == null;
	}

	@Transient
	public boolean isExistente() {
		return !isNovo();
	}


	public void recalcularValorTotal() {
//		BigDecimal total = BigDecimal.ZERO;
//
//		// total = frete - desconto
//		total = total.add(this.getValorFrete()).subtract(
//				this.getValorDesconto());
//
//		for (ItemPedido item : this.getItens()) {
//			if (item.getProduto() != null && item.getProduto().getId() != null) {
//				total = total.add(item.getValorTotal());
//			}
//		}
//
//		this.setValorTotal(total);

	}

//	@Transient
//	public BigDecimal getValorSubTotal() {
//		// sub total = valor total - frete + desconto
//		return this.getValorTotal().subtract(this.getValorFrete())
//				.add(this.getValorDesconto());
//	}

	public void adicionaItemVazio() {

	//	if (this.isOrcamento()) {
			Produto produto = new Produto();
			//produto.setQuantidadeEstoque(1);

			//ItemPedido item = new ItemPedido();
			ItemMovimentacao item = new ItemMovimentacao();
			item.setProduto(produto);
			item.setQuantidade(1);
			//item.setPedido(this);
			item.setMovimentacao(this);

			//this.getItens().add(0, item);
			this.getItensMovimentacao().add(0, item);
		//}
	}

//	@Transient
//	public boolean isOrcamento() {
//		return StatusPedido.ORCAMENTO.equals(this.getStatus());
//	}

	public void removerItemVazio() {
		//ItemPedido primeiroItem = this.getItens().get(0);
		ItemMovimentacao primeiroItem = this.getItensMovimentacao().get(0);
		
		if(primeiroItem != null && primeiroItem.getProduto().getId() == null){
			//this.getItens().remove(0);
			this.getItensMovimentacao().remove(0);
		}
		
	}
	
	public void removerItem(ItemMovimentacao item){
		this.getItensMovimentacao().remove(item);
		adicionaItemVazio();
	}

	@Transient
	public boolean isValorTotalNegativo() {
		// TODO Auto-generated method stub
		return this.valorTotal.compareTo(BigDecimal.ZERO) < 0;
	}

//	@Transient
//	public boolean isEmitido() {
//		return StatusPedido.EMITIDO.equals(this.getStatus());
//	}
//
//	@Transient
//	public boolean isEmissivel() {
//		return this.isExistente() && this.isOrcamento();
//	}
	
	
//	@Transient
//	public boolean isNaoEmissivel() {
//		return !isEmissivel();
//	}

//	@Transient
//	public boolean isCancelavel() {
//		return this.isExistente() && !this.isCancelado();
//	}
//	
//
	@Transient
	public boolean isNaoCancelado() {
		return !isCancelado();
	}

	@Transient
	public boolean isCancelado() {
		//return StatusPedido.CANCELADO.equals(this.getStatus());
		return StatusMovimentacao.CANCELADO.equals(this.statusMovimentacao);
	}
//
	@Transient
	public boolean isAlteravel() {
		return this.isPendente();
	}
	
	@Transient
	public boolean isNaoAlteravel() {
		return !isAlteravel() || isBaixado();
	}
//	
//	@Transient
//	public boolean isNaoEnviavelPorEmail() {
//		return isNovo() || isCancelado();
//	}
	
	@Transient
	public boolean isPendente() {
		return StatusMovimentacao.PENDENTE.equals(this.getStatusMovimentacao());
	}
	
	@Transient
	public boolean isBaixado() {
		return StatusMovimentacao.BAIXADO.equals(this.getStatusMovimentacao());
	}
	//para travar o botao de baixa na tela cadastro de movimentação
	public boolean verifica(){
		if(this.isNovo() || isNaoAlteravel())
			return true;
		
		return false;
	}
	
	@Transient
	public boolean isOperacaoEntrada(){
		boolean retorno = false;
		
		if(getOperacao().equals("entrada"))
			retorno = true;
		
		return retorno;
	}
	
	
	// get and set
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getDataCriacao() {
			return dataCriacao;
		}

		public void setDataCriacao(Date dataCriacao) {
			this.dataCriacao = dataCriacao;
		}

		public String getObservacao() {
			return observacao;
		}

		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}

		public Date getDataEntrega() {
			return dataEntrega;
		}

		public void setDataEntrega(Date dataEntrega) {
			this.dataEntrega = dataEntrega;
		}

		public Date getDataEmissao() {
			return dataEmissao;
		}

		public void setDataEmissao(Date dataEmissao) {
			this.dataEmissao = dataEmissao;
		}

		public BigDecimal getValorFrete() {
			return valorFrete;
		}

		public void setValorFrete(BigDecimal valorFrete) {
			this.valorFrete = valorFrete;
		}

		public BigDecimal getValorDesconto() {
			return valorDesconto;
		}

		public void setValorDesconto(BigDecimal valorDesconto) {
			this.valorDesconto = valorDesconto;
		}

		public BigDecimal getValorTotal() {
			return valorTotal;
		}

		public void setValorTotal(BigDecimal valorTotal) {
			this.valorTotal = valorTotal;
		}

//		public StatusPedido getStatus() {
//			return status;
//		}
//
//		public void setStatus(StatusPedido status) {
//			this.status = status;
//		}
//
//		public FormaPagamento getFormaPagamento() {
//			return formaPagamento;
//		}
//
//		public void setFormaPagamento(FormaPagamento formaPagamento) {
//			this.formaPagamento = formaPagamento;
//		}

		public Usuario getVendedor() {
			return vendedor;
		}

		public void setVendedor(Usuario vendedor) {
			this.vendedor = vendedor;
		}

//		public Cliente getCliente() {
//			return cliente;
//		}
//
//		public void setCliente(Cliente cliente) {
//			this.cliente = cliente;
//		}
//
//		public EnderecoEntrega getEnderecoEntrega() {
//			return enderecoEntrega;
//		}
//
//		public void setEnderecoEntrega(EnderecoEntrega enderecoEntrega) {
//			this.enderecoEntrega = enderecoEntrega;
//		}
//
//		public List<ItemPedido> getItens() {
//			return itens;
//		}
//
//		public void setItens(List<ItemPedido> itens) {
//			this.itens = itens;
//		}
		
		public int getNumeroParcela() {
			return numeroParcela;
		}

		public void setNumeroParcela(int numeroParcela) {
			this.numeroParcela = numeroParcela;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ItemMovimentacao> getItensMovimentacao() {
		return itensMovimentacao;
	}

	public void setItensMovimentacao(List<ItemMovimentacao> itensMovimentacao) {
		this.itensMovimentacao = itensMovimentacao;
	}

	public StatusMovimentacao getStatusMovimentacao() {
		return statusMovimentacao;
	}

	public void setStatusMovimentacao(StatusMovimentacao statusMovimentacao) {
		this.statusMovimentacao = statusMovimentacao;
	}

	public Date getPrazoDeEntrega() {
		return prazoDeEntrega;
	}

	public void setPrazoDeEntrega(Date prazoDeEntrega) {
		this.prazoDeEntrega = prazoDeEntrega;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
}