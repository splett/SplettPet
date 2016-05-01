package splett.animal.raca;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import splett.animal.raca.dao.RacaDao;

@ManagedBean(name = "racaConverter")
@ApplicationScoped
public class RacaConverter implements Converter {

	@ManagedProperty(value = "#{racaDao}")
	private RacaDao racaDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) throws ConverterException {
		if (value != null && !value.isEmpty()
				&& !value.equalsIgnoreCase("Selecione um"))
			return racaDao.pesquisarPorNome(value).get(0);
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object object) throws ConverterException {
		if (object instanceof Raca)
			return ((Raca) object).toString();
		return null;
	}

	public RacaDao getRacaDao() {
		return racaDao;
	}

	public void setRacaDao(RacaDao racaDao) {
		this.racaDao = racaDao;
	}
}