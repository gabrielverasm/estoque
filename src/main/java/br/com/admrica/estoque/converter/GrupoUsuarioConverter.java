package br.com.admrica.estoque.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.admrica.estoque.model.GrupoUsuario;
import br.com.admrica.estoque.model.Usuario;
import br.com.admrica.estoque.repository.GrupoUsuarioDAO;
import br.com.admrica.estoque.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class,value="grupoUsuarioConverter")
public class GrupoUsuarioConverter implements Converter {

    // @Inject
    private GrupoUsuarioDAO grupoUsuarioDAO;

    public GrupoUsuarioConverter() {
        grupoUsuarioDAO = CDIServiceLocator.getBean(GrupoUsuarioDAO.class);
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        GrupoUsuario retorno = null;

        if (value != null) {
            Long id = new Long(value);
            retorno = grupoUsuarioDAO.porId(id);
        }

        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value != null) {
            GrupoUsuario grupoUsuario = (GrupoUsuario) value;
            return grupoUsuario.getId() == null ? null : grupoUsuario.getId().toString();
        }

        return "";
    }

}