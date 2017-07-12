package br.com.admrica.estoque.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.admrica.estoque.model.Movimentacao;
import br.com.admrica.estoque.repository.MovimentacaoDAO;
import br.com.admrica.estoque.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Movimentacao.class)
public class MovimentacaoConverter implements Converter {

	//@Inject
	private MovimentacaoDAO movimentacaoDAO;
	
	public MovimentacaoConverter() {
		movimentacaoDAO = CDIServiceLocator.getBean(MovimentacaoDAO.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Movimentacao retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = movimentacaoDAO.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Movimentacao movimentacao = (Movimentacao) value;
			return  movimentacao.getId() == null ?  null :  movimentacao.getId().toString();
		}
		
		return "";
	}

}
