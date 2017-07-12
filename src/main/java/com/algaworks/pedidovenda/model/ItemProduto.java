package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ItemProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date dataDeValidade;
	
	@Column(precision = 7, scale = 2)
	private Double quantidade;
	
	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName="id")
	private Produto produto;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_item_movimentacao")
	private ItemMovimentacao itemMovimentacao;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Date getDataDeValidade() {
		return dataDeValidade;
	}

	public void setDataDeValidade(Date dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}

	
	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

//	@ManyToOne
//	@JoinColumn(name = "id_movimentacao" )
//	public Movimentacao getMovimentacao() {
//		return movimentacao;
//	}
//
//	public void setMovimentacao(Movimentacao movimentacao) {
//		this.movimentacao = movimentacao;
//	}

	
	public ItemMovimentacao getItemMovimentacao() {
		return itemMovimentacao;
	}

	public void setItemMovimentacao(ItemMovimentacao itemMovimentacao) {
		this.itemMovimentacao = itemMovimentacao;
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
		ItemProduto other = (ItemProduto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ItemProduto [dataDeValidade=" + dataDeValidade + ", quantidade=" + quantidade + "]";
	}
}
