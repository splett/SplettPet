package splett.foto.mb;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import splett.foto.Foto;
import splett.usuario.Usuario;

public class LazyFotoSorter implements Comparator<Foto> {
    private String sortField;

    private SortOrder sortOrder;

    public LazyFotoSorter(String sortField, SortOrder sortOrder) {
	this.sortField = sortField;
	this.sortOrder = sortOrder;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int compare(Foto u1, Foto u2) {
	try {
	    Object value1 = Usuario.class.getField(this.sortField).get(u1);
	    Object value2 = Usuario.class.getField(this.sortField).get(u2);

	    int value = ((Comparable) value1).compareTo(value2);

	    return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
	} catch (Exception e) {
	    throw new RuntimeException();
	}
    }

    public String getSortField() {
	return sortField;
    }

    public void setSortField(String sortField) {
	this.sortField = sortField;
    }

    public SortOrder getSortOrder() {
	return sortOrder;
    }

    public void setSortOrder(SortOrder sortOrder) {
	this.sortOrder = sortOrder;
    }

}
