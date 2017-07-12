package br.com.admrica.estoque.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admrica.estoque.model.Usuario;
import br.com.admrica.estoque.repository.filter.UsuarioParaPesquisa;
import br.com.admrica.estoque.service.UsuarioService;

@Named
@ViewScoped
public class PesquisaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioService usuarioService;

	private Usuario usuarioSelecionado;

	private List<Usuario> listaUsuario = new ArrayList<Usuario>();

	private UsuarioParaPesquisa usuarioParaPesquisa;

	public PesquisaUsuarioBean() {
		usuarioParaPesquisa = new UsuarioParaPesquisa();
	}

	public void pesquisar() {
		listaUsuario = usuarioService.pesquisar(usuarioParaPesquisa);
	}
	
	
	// GET AND SET
	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public UsuarioParaPesquisa getUsuarioParaPesquisa() {
		return usuarioParaPesquisa;
	}

	public void setUsuarioParaPesquisa(UsuarioParaPesquisa usuarioParaPesquisa) {
		this.usuarioParaPesquisa = usuarioParaPesquisa;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}
}
