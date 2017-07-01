package com.algaworks.pedidovenda.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class ItemMovimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 50)
	private double quantidade;
	@ManyToOne
	@JoinColumn(name = "id_produto", referencedColumnName="id")
	private Produto produto;

	//	@ManyToOne
//	@JoinColumn(name= "id_item_produto")
//	private ItemProduto itemProduto;
	@ManyToOne
	@JoinColumn(name = "id_movimentacao")
	private Movimentacao movimentacao;
	
	@Temporal(TemporalType.DATE)
	private Date dataDeRecebimento;
	
	@Temporal(TemporalType.DATE)
	private Date dataDeValidade;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	
//	public ItemProduto getItemProduto() {
//		return itemProduto;
//	}
//
//	public void setItemProduto(ItemProduto itemProduto) {
//		this.itemProduto = itemProduto;
//	}

	
	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
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

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Transient
	public boolean isProdutoAssociado(){
		return this.getProduto() != null && this.getProduto().getId() != null;
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
		ItemMovimentacao other = (ItemMovimentacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

}
