package splett.animal.raca.mb;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import splett.animal.raca.Raca;
import splett.animal.raca.dao.RacaDao;

@ManagedBean(name = "racaLazyDataModel")
@ViewScoped
public class RacaLazyDataModel extends LazyDataModel<Raca>{

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value = "#{racaDao}")
	private RacaDao racaDao;

	@Override
	public Raca getRowData(String rowKey) {
		return racaDao.findById(Integer.parseInt(rowKey));
	}

	@Override
	public Object getRowKey(Raca raca) {
		return raca.getId();
	}

	@Override
	public List<Raca> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		List<Raca> source = null;

		if (filters.containsKey("nome")) {
			String nomePesquisa = filters.get("nome").toString();
			source = racaDao.pesquisarPorNome(nomePesquisa);
		} else {
			source = racaDao.list();
		}

		// sort
		if (sortField != null) {
			Collections
					.sort(source, new LazyRacaSorter(sortField, sortOrder));
		}

		// rowCount
		this.setRowCount(racaDao.getRowCount());

		return source;
	}

	public RacaDao getRacaDao() {
		return racaDao;
	}

	public void setRacaDao(RacaDao racaDao) {
		this.racaDao = racaDao;
	}
}






