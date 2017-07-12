package br.com.admrica.estoque.controller;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admrica.estoque.model.Cargo;
import br.com.admrica.estoque.model.Usuario;
import br.com.admrica.estoque.service.UsuarioService;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	@Inject
	private UsuarioService usuarioService;

	public CadastroUsuarioBean() {
		limpar();
	}

	public void limpar() {
		usuario = new Usuario();
	}

	public void salvar() {
		this.usuario = this.usuarioService.salvar(usuario);
		limpar();
	}

	public boolean verificaEdicao() {
		return this.usuario.getId() != null;
	}

	public Date dataHoje() {
		return new Date();
	}

	// get and set
	public Usuario getUsuario() {
		return usuario;
	}

	public Cargo[] getCargoUsuario() {
		return Cargo.values();
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
