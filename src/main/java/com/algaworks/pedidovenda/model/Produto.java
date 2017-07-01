package com.algaworks.pedidovenda.model;

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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;
	private Integer codigoDeBarras;
	private Double quantidade;
	private Double estoqueMinimo;
	private Double qtdDiaria;
	private Date dataDeValidade;
	private Date prazoDeEntrega;
	private Grupo grupo;
	private Unidade unidade;
	private Status status;
//	private List<ItemProduto> itensProduto = new ArrayList<>();
	private Usuario usuario;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Length(max = 100)
	@Column(length = 100, nullable = false, unique = true)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(unique = true)
	public Integer getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(Integer codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	@Column(precision = 7, scale = 2)
	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	@Column(nullable = false, precision = 8, scale = 2)
	public Double getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Double estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	@Column(nullable = false, precision = 5, scale = 2)
	public Double getQtdDiaria() {
		return qtdDiaria;
	}

	public void setQtdDiaria(Double qtdDiaria) {
		this.qtdDiaria = qtdDiaria;
	}

	@Temporal(TemporalType.DATE)
	public Date getDataDeValidade() {
		return dataDeValidade;
	}

	public void setDataDeValidade(Date dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}

	@Temporal(TemporalType.DATE)
	public Date getPrazoDeEntrega() {
		return prazoDeEntrega;
	}

	public void setPrazoDeEntrega(Date prazoDeEntrega) {
		this.prazoDeEntrega = prazoDeEntrega;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

//	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
//	public List<ItemProduto> getItensProduto() {
//		return itensProduto;
//	}
//
//	public void setItensProduto(List<ItemProduto> itensProduto) {
//		this.itensProduto = itensProduto;
//	}

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
