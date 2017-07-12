package br.com.admrica.estoque.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.admrica.estoque.model.Usuario;
import br.com.admrica.estoque.repository.UsuarioDAO;
import br.com.admrica.estoque.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class,value="usuarioConverter")
public class UsuarioConverter implements Converter {

	// @Inject
	private UsuarioDAO usuarioDAO;

	public UsuarioConverter() {
		usuarioDAO = CDIServiceLocator.getBean(UsuarioDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Usuario retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = usuarioDAO.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}

		return "";
	}

}