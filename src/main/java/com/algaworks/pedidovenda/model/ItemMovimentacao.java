package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "item_movimentacao")
public class ItemMovimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_movimentacao", nullable = false)
	private Movimentacao movimentacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataDeRecebimento;
	
	@Temporal(TemporalType.DATE)
	private Date dataDeValidade;
	
	//
	
	@Column(nullable = false, length = 3)
	private Integer quantidade = 1;
	
	@Column(name = "valor_unitario", precision = 10, scale = 2)
	private BigDecimal valorUnitario = BigDecimal.ZERO;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
//	@ManyToOne
//	@JoinColumn(name = "id_pedido")
//	private Pedido pedido;

	//get and set
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}



	
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	
	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
//	public Pedido getPedido() {
//		return pedido;
//	}
//
//	public void setPedido(Pedido pedido) {
//		this.pedido = pedido;
//	}

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
		ItemMovimentacao other = (ItemMovimentacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Transient
	public BigDecimal getValorTotal() {
		//total = valor da unidade * quantidade
		return this.getValorUnitario().multiply(new BigDecimal(this.getQuantidade()));
	}
	
	@Transient
	public boolean isProdutoAssociado(){
		return this.getProduto() != null && this.getProduto().getId() != null;
	}

	public Date getDataDeRecebimento() {
		return dataDeRecebimento;
	}

	public void setDataDeRecebimento(Date dataDeRecebimento) {
		this.dataDeRecebimento = dataDeRecebimento;
	}

	public Date getDataDeValidade() {
		return dataDeValidade;
	}

	public void setDataDeValidade(Date dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}

//	@Transient
//	public boolean isEstoqueSuficiente(){
//		return this.getPedido().isEmitido() ||  this.getProduto().getId() == null
//				|| this.getProduto().getQuantidadeEstoque() >= this.getQuantidade();
//	}
	
//	@Transient
//	public boolean isEstoqueInSuficiente(){
//		return !isEstoqueSuficiente();
//	}
}
