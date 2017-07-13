package br.com.admrica.estoque.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.tagcloud.DefaultTagCloudItem;
import org.primefaces.model.tagcloud.DefaultTagCloudModel;
import org.primefaces.model.tagcloud.TagCloudModel;

import br.com.admrica.estoque.model.Cargo;
import br.com.admrica.estoque.model.GrupoUsuario;
import br.com.admrica.estoque.model.Usuario;
import br.com.admrica.estoque.repository.GrupoUsuarioDAO;
import br.com.admrica.estoque.service.UsuarioService;
import br.com.admrica.estoque.util.jsf.FacesUtil;
import br.com.admrica.estoque.validation.UsuarioEdicao;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Produces
	@UsuarioEdicao
	private Usuario usuario;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private GrupoUsuarioDAO grupoUsuarioDAO;

	private List<GrupoUsuario> listaGrupoUsario = new ArrayList<>();

	private List<GrupoUsuario> permissoesDada = new ArrayList<>();

	/**
	 * criei este campo pois quando eu editava um usuario o selectManyCheck ficava habilita
	 * e com essa variavel eu consigo limpar
	 */
	private GrupoUsuario[] grupoUsuarioSelecionados;
	private TagCloudModel model;
	
	private String confirmaSenha;
//	@PostConstruct
//	public void init() {
//		listaGrupoUsario = grupoUsuarioDAO.listarTodos();
//		// this.usuario.adicionaItemVazio();
//	}
	
	public void salvar() {
		this.permissoesDada = Arrays.asList(grupoUsuarioSelecionados);
		
		this.usuario.setGruposUsuario(permissoesDada);
		this.usuario = this.usuarioService.salvar(usuario);
		limpar();
		Arrays.fill(this.grupoUsuarioSelecionados, null); // limpa os checkboxs
	}
	
	public void inicializar() { // carrega as categorias
		if (FacesUtil.isNotPostback()) {
			listaGrupoUsario = grupoUsuarioDAO.listarTodos();
			model = new DefaultTagCloudModel();
			
			int i = 1;
			for(GrupoUsuario g : this.usuario.getGruposUsuario()){
				model.addTag(new DefaultTagCloudItem(g.getNome(), i++));
			}
		}
	}

	public CadastroUsuarioBean() {
		limpar();
	}

	public void limpar() {
		usuario = new Usuario();
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

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	public GrupoUsuario[] getGrupoUsuarioSelecionados() {
		return grupoUsuarioSelecionados;
	}

	public void setGrupoUsuarioSelecionados(GrupoUsuario[] grupoUsuarioSelecionados) {
		this.grupoUsuarioSelecionados = grupoUsuarioSelecionados;
	}

	public TagCloudModel getModel() {
		return model;
	}

	public void setModel(TagCloudModel model) {
		this.model = model;
	}

}