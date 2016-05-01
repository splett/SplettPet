package splett.animal.tipo.mb;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import splett.animal.tipo.TipoAnimal;
import splett.animal.tipo.dao.TipoAnimalDao;

@ManagedBean(name = "tipoAnimalLazyDataModel")
@ViewScoped
public class TipoAnimalLazyDataModel extends LazyDataModel<TipoAnimal>{

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{tipoAnimalDao}")
	private TipoAnimalDao tipoAnimalDao;

	@Override
	public TipoAnimal getRowData(String rowKey) {
		return tipoAnimalDao.findById(Integer.parseInt(rowKey));
	}

	@Override
	public Object getRowKey(TipoAnimal tipoAnimal) {
		return tipoAnimal.getId();
	}

	@Override
	public List<TipoAnimal> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		List<TipoAnimal> source = null;

		if (filters.containsKey("nome")) {
			String nomePesquisa = filters.get("nome").toString();
			source = tipoAnimalDao.pesquisarPorNome(nomePesquisa);
		} else {
			source = tipoAnimalDao.list();
		}

		// sort
		if (sortField != null) {
			Collections
					.sort(source, new LazyTipoAnimalSorter(sortField, sortOrder));
		}

		// rowCount
		this.setRowCount(tipoAnimalDao.getRowCount());

		return source;
	}

	public TipoAnimalDao getTipoAnimalDao() {
		return tipoAnimalDao;
	}

	public void setTipoAnimalDao(TipoAnimalDao tipoAnimalDao) {
		this.tipoAnimalDao = tipoAnimalDao;
	}

}





