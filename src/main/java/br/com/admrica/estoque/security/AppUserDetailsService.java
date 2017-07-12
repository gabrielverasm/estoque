package br.com.admrica.estoque.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.admrica.estoque.model.GrupoUsuario;
import br.com.admrica.estoque.model.Usuario;
import br.com.admrica.estoque.repository.UsuarioDAO;
import br.com.admrica.estoque.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UsuarioDAO usuarioDao = CDIServiceLocator.getBean(UsuarioDAO.class);
		Usuario usuario = usuarioDao.porEmail(email);

		UsuarioSistema user = null;

		if (usuario != null) {
			user = new UsuarioSistema(usuario, getGruposUsuario(usuario));
		}

		return user;
	}

	private Collection<? extends GrantedAuthority> getGruposUsuario(Usuario usuario) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (GrupoUsuario grupoUsuario : usuario.getGruposUsuario()) {
			authorities.add(new SimpleGrantedAuthority(grupoUsuario.getNome()));
		}
		return authorities;
	}

}