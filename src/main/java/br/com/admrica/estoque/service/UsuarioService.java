package br.com.admrica.estoque.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.admrica.estoque.model.Usuario;
import br.com.admrica.estoque.repository.UsuarioDAO;
import br.com.admrica.estoque.repository.filter.UsuarioParaPesquisa;
import br.com.admrica.estoque.util.jpa.Transactional;
import br.com.admrica.estoque.util.jsf.FacesUtil;

public class UsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Transactional
	public Usuario salvar(Usuario usuario) {

		try {
			usuario = usuarioDAO.salvar(usuario);
			FacesUtil.InfoMessage("Usuario salvo com sucesso!");
		} catch (Exception e) {
			FacesUtil.ErrorMessage("Erro ao tentar salvar o usuario");
		}

		return usuario;
	}

	public List<Usuario> pesquisar(UsuarioParaPesquisa usuarioParaPesquisa) {
		return usuarioDAO.pesquisar(usuarioParaPesquisa.getNome());
	}
}
