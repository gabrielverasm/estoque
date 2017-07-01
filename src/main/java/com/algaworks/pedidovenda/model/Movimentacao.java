package com.algaworks.pedidovenda.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Movimentacao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private StatusMovimentacao statusMovimentacao = StatusMovimentacao.PENDENTE;
	
	@Column(length = 150, nullable = false)
	private String nome;

	@Column(columnDefinition = "text")
	private String observacao;
	
	@Temporal(TemporalType.DATE)
	private Date prazoDeEntrega;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	
//	@ManyToOne
//	@JoinColumn(nullable = false)
//	private Usuario usuario;
	
	
//	@Column(nullable = true)
	private String operacao;
	
	@OneToMany(mappedBy = "movimentacao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemMovimentacao> itensMovimentacao = new ArrayList<>();

	@Transient
	public boolean isNovo() {
		return getId() == null;
	}

	@Transient
	public boolean isExistente() {
		return !isNovo();
	}
	
	
	@Transient
	public boolean isAlteravel() {
		return this.statusMovimentacao == StatusMovimentacao.PENDENTE;
	}
	
	@Transient
	public boolean isNaoAlteravel() {
		return !isAlteravel();
	}
	
	public void adicionaItemVazio() {
		
		if(this.statusMovimentacao == StatusMovimentacao.PENDENTE)
		{
//			Produto produto = new Produto();
//			
//			ItemMovimentacao item = new ItemMovimentacao();
//			item.setProduto(produto);
//			item.setQuantidade(1.0);
//			item.setMovimentacao(this);
//			item.setUsuario(null);
//			item.setItemProduto(null);
//			
//			this.getItensMovimentacao().add(0,item);
			
		}
	}
	
	//get and set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public StatusMovimentacao getStatusMovimentacao() {
		return statusMovimentacao;
	}

	public void setStatusMovimentacao(StatusMovimentacao statusMovimentacao) {
		this.statusMovimentacao = statusMovimentacao;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	
	public Date getPrazoDeEntrega() {
		return prazoDeEntrega;
	}

	public void setPrazoDeEntrega(Date prazoDeEntrega) {
		this.prazoDeEntrega = prazoDeEntrega;
	}

	
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

	

	
	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	
	public List<ItemMovimentacao> getItensMovimentacao() {
		return itensMovimentacao;
	}

	public void setItensMovimentacao(List<ItemMovimentacao> itensMovimentacao) {
		this.itensMovimentacao = itensMovimentacao;
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



}
