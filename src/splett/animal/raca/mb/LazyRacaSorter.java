package splett.animal.raca.mb;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import splett.animal.raca.Raca;

public class LazyRacaSorter implements Comparator<Raca>{

	private String sortField;

	private SortOrder sortOrder;

	public LazyRacaSorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(Raca u1, Raca u2) {
		try {
			Object value1 = Raca.class.getField(this.sortField).get(u1);
			Object value2 = Raca.class.getField(this.sortField).get(u2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
