package br.com.admrica.estoque.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.admrica.estoque.service.NegocioException;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Length(max = 100)
	@Column(length = 100, nullable = false, unique = true)
	private String nome;

	@Column(unique = true)
	private Integer codigoDeBarras;

	@Column(precision = 7, scale = 2)
	private Double quantidade;

	@Column(nullable = false, precision = 8, scale = 2)
	private Double estoqueMinimo;

	@Column(nullable = false, precision = 5, scale = 2)
	private Double qtdDiaria;

	@Temporal(TemporalType.DATE)
	private Date dataDeValidade;

	@Temporal(TemporalType.DATE)
	private Date prazoDeEntrega;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private Grupo grupo;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Unidade unidade;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Status status = Status.ATIVO;

	// private List<ItemProduto> itensProduto = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(Integer codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public Double getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Double estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public Double getQtdDiaria() {
		return qtdDiaria;
	}

	public void setQtdDiaria(Double qtdDiaria) {
		this.qtdDiaria = qtdDiaria;
	}

	public Date getDataDeValidade() {
		return dataDeValidade;
	}

	public void setDataDeValidade(Date dataDeValidade) {
		this.dataDeValidade = dataDeValidade;
	}

	public Date getPrazoDeEntrega() {
		return prazoDeEntrega;
	}

	public void setPrazoDeEntrega(Date prazoDeEntrega) {
		this.prazoDeEntrega = prazoDeEntrega;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	// @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval
	// = true)
	// public List<ItemProduto> getItensProduto() {
	// return itensProduto;
	// }
	//
	// public void setItensProduto(List<ItemProduto> itensProduto) {
	// this.itensProduto = itensProduto;
	// }

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

	@Override
	public String toString() {
		return nome;
	}

	public void baixarEstoque(Integer quantidade) {
		double novaQuantidade = this.getQuantidade() - quantidade;

		if (novaQuantidade < 0) {
			throw new NegocioException(
					"Não ha disponibilidade no estoque de " + quantidade + " itens produto " + this.getNome());
		}

		this.setQuantidade(novaQuantidade);
	}

	public void adicionarEstoque(Integer quantidade) {
		if (getQuantidade() == null) {
			setQuantidade(0.0);
		}
		this.setQuantidade(getQuantidade() + quantidade);
	}

}
