package br.com.admrica.estoque.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.admrica.estoque.model.Produto;
import br.com.admrica.estoque.repository.ProdutoDAO;
import br.com.admrica.estoque.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Produto.class, value = "produtoConverter2")
public class ProdutoConverter2 implements Converter {

	// @Inject
	private ProdutoDAO produtoDAO;

	public ProdutoConverter2() {
		produtoDAO = CDIServiceLocator.getBean(ProdutoDAO.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Produto retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = produtoDAO.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Produto produto = (Produto) value;
			return produto.getId() == null ? null : produto.getId().toString();
		}

		return "";
	}

}
