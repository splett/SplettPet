package splett.usuario;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import splett.usuario.dao.UsuarioDao;

@ManagedBean(name = "usuarioConverter")
@ApplicationScoped
public class UsuarioConverter implements Converter {

	@ManagedProperty(value = "#{usuarioDao}")
	private UsuarioDao usuarioDao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if ( value != null && !value.isEmpty() && !value.equalsIgnoreCase("Selecione um") )
			return usuarioDao.listUsuariosByEmail(value).get(0);
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object object) throws ConverterException {
		if ( object instanceof Usuario )
			return ((Usuario)object).toString();
		return null;
	}

	public UsuarioDao getUsuarioDao()
	{
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao)
	{
		this.usuarioDao = usuarioDao;
	}
}