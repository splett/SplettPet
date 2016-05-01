package splett.animal.mb;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import splett.animal.Animal;

public class LazyAnimalSorter implements Comparator<Animal> {

	private String sortField;

	private SortOrder sortOrder;

	public LazyAnimalSorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(Animal a1, Animal a2) {
		try {
			Object value1 = Animal.class.getField(this.sortField).get(a1);
			Object value2 = Animal.class.getField(this.sortField).get(a2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}