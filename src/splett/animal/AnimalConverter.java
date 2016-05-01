package splett.animal;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import splett.animal.dao.AnimalDao;

@ManagedBean(name = "animalConverter")
@ApplicationScoped
public class AnimalConverter implements Converter {

	@ManagedProperty(value = "#{animalDao}")
	private AnimalDao animalDao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if ( value != null && !value.isEmpty() && !value.equalsIgnoreCase("Selecione um") )
			return animalDao.pesquisarPorNome(value).get(0);
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
		if ( object instanceof Animal )
			return ((Animal)object).toString();
		return null;
	}

	public AnimalDao getAnimalDao()
	{
		return animalDao;
	}

	public void setAnimalDao(AnimalDao animalDao)
	{
		this.animalDao = animalDao;
	}
}