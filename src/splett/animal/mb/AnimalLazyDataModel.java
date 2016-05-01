package splett.animal.mb;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import splett.animal.Animal;
import splett.animal.dao.AnimalDao;

@ManagedBean(name = "animalLazyDataModel")
@ViewScoped
public class AnimalLazyDataModel extends LazyDataModel<Animal> {

    private static final long serialVersionUID = 1L;

    @ManagedProperty(value = "#{animalDao}")
    private AnimalDao animalDao;

    @Override
    public Animal getRowData(String rowKey) {
	return animalDao.findById(Integer.parseInt(rowKey));
    }

    @Override
    public Object getRowKey(Animal animal) {
	return animal.getId();
    }

    @Override
    public List<Animal> load(int first, int pageSize, String sortField, SortOrder sortOrder,
	    Map<String, Object> filters) {
	List<Animal> source = null;

	if (filters.containsKey("nome")) {
	    String nomePesquisa = filters.get("nome").toString();
	    source = animalDao.pesquisarPorNome(nomePesquisa);
	} else {
	    source = animalDao.list(first, pageSize);
	}

	// sort
	if (sortField != null) {
	    Collections.sort(source, new LazyAnimalSorter(sortField, sortOrder));
	}

	// rowCount
	this.setRowCount(animalDao.getRowCount());

	return source;
    }

    public AnimalDao getAnimalDao() {
	return animalDao;
    }

    public void setAnimalDao(AnimalDao animalDao) {
	this.animalDao = animalDao;
    }

}
