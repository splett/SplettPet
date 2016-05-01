package splett.usuario.endereco;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import splett.usuario.endereco.dao.EnderecoDao;

@ManagedBean(name = "enderecoEstadoConverter")
@ApplicationScoped
public class EnderecoEstadoConverter implements Converter {

	@ManagedProperty(value = "#{enderecoDao}")
	private EnderecoDao enderecoDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		if (value != null && !value.isEmpty()
				&& !value.equalsIgnoreCase("Selecione um"))
			return enderecoDao.pesquisarPorEstado(value).get(0);
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) throws ConverterException {
		if (object instanceof Endereco)
			return ((Endereco) object).getUf();
		return null;
	}

	public EnderecoDao getEnderecoDao() {
		return enderecoDao;
	}

	public void setEnderecoDao(EnderecoDao enderecoDao) {
		this.enderecoDao = enderecoDao;
	}
}