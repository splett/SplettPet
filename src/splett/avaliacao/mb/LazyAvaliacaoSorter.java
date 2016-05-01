package splett.avaliacao.mb;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import splett.avaliacao.Avaliacao;

public class LazyAvaliacaoSorter implements Comparator<Avaliacao> {

    private String sortField;

    private SortOrder sortOrder;

    public LazyAvaliacaoSorter(String sortField, SortOrder sortOrder) {
	this.sortField = sortField;
	this.sortOrder = sortOrder;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public int compare(Avaliacao u1, Avaliacao u2) {
	try {
	    Object value1 = Avaliacao.class.getField(this.sortField).get(u1);
	    Object value2 = Avaliacao.class.getField(this.sortField).get(u2);

	    int value = ((Comparable) value1).compareTo(value2);

	    return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
	} catch (Exception e) {
	    throw new RuntimeException();
	}
    }
}
