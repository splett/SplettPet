package splett.usuario.mb;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import splett.usuario.Usuario;
import splett.usuario.dao.UsuarioDao;

@ManagedBean(name = "usuarioLazyDataModel")
@ViewScoped
public class UsuarioLazyDataModel extends LazyDataModel<Usuario> {

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{usuarioDao}")
	private UsuarioDao usuarioDao;

	@Override
	public Usuario getRowData(String rowKey) {
		return usuarioDao.findById(Integer.parseInt(rowKey));
	}

	@Override
	public Object getRowKey(Usuario usuario) {
		return usuario.getId();
	}

	@Override
	public List<Usuario> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		List<Usuario> source = null;

		if (filters.containsKey("nome")) {
			String nomePesquisa = filters.get("nome").toString();
			source = usuarioDao.pesquisarPorNome(nomePesquisa);
		} else {
			source = usuarioDao.list(first, pageSize);
		}

		// sort
		if (sortField != null) {
			Collections.sort(source,
					new LazyUsuarioSorter(sortField, sortOrder));
		}

		// rowCount
		this.setRowCount(usuarioDao.getRowCount());

		return source;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

}
