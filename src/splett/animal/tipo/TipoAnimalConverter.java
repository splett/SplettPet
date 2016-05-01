package splett.animal.tipo;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import splett.animal.tipo.dao.TipoAnimalDao;

@ManagedBean(name = "tipoAnimalConverter")
@ApplicationScoped
public class TipoAnimalConverter implements Converter {

	@ManagedProperty(value = "#{tipoAnimalDao}")
	private TipoAnimalDao tipoAnimalDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		if (value != null && !value.isEmpty()
				&& !value.equalsIgnoreCase("Selecione um"))
			return tipoAnimalDao.pesquisarPorNome(value).get(0);
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) throws ConverterException {
		if (object instanceof TipoAnimal)
			return ((TipoAnimal) object).toString();
		return null;
	}

	public TipoAnimalDao getTipoAnimalDao() {
		return tipoAnimalDao;
	}

	public void setTipoAnimalDao(TipoAnimalDao tipoAnimalDao) {
		this.tipoAnimalDao = tipoAnimalDao;
	}
}