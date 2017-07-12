package br.com.admrica.estoque.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.admrica.estoque.model.Cargo;
import br.com.admrica.estoque.model.GrupoUsuario;
import br.com.admrica.estoque.model.Usuario;
import br.com.admrica.estoque.repository.GrupoUsuarioDAO;
import br.com.admrica.estoque.service.UsuarioService;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private GrupoUsuarioDAO grupoUsuarioDAO;

	private List<GrupoUsuario> listaGrupoUsario = new ArrayList<>();

	private List<GrupoUsuario> permissoesDada = new ArrayList<>();
	private List<GrupoUsuario> permissoes = new ArrayList<>();

	@PostConstruct
	public void init() {
		listaGrupoUsario = grupoUsuarioDAO.listarTodos();
		// this.usuario.adicionaItemVazio();
	}

	public CadastroUsuarioBean() {
		limpar();
	}

	public void limpar() {
		usuario = new Usuario();
	}

	public void salvar() {
		// for(int i=0; i< permissoesDada.size();i++){
		// permissoes.add(permissoesDada.get(i));
		// }

		this.usuario.setGruposUsuario(permissoesDada);
		this.usuario = this.usuarioService.salvar(usuario);
		limpar();
	}

	public void removeDalista(GrupoUsuario item) {
		this.listaGrupoUsario.remove(item);
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

	public List<GrupoUsuario> getListaGrupoUsario() {
		return listaGrupoUsario;
	}

	public void setListaGrupoUsario(List<GrupoUsuario> listaGrupoUsario) {
		this.listaGrupoUsario = listaGrupoUsario;
	}

	public List<GrupoUsuario> getPermissoesDada() {
		return permissoesDada;
	}

	public void setPermissoesDada(List<GrupoUsuario> permissoesDada) {
		this.permissoesDada = permissoesDada;
	}

}