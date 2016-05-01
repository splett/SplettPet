package splett.animal.tipo.mb;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import splett.animal.tipo.TipoAnimal;

public class LazyTipoAnimalSorter implements Comparator<TipoAnimal>{

	private String sortField;

	private SortOrder sortOrder;

	public LazyTipoAnimalSorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(TipoAnimal a1, TipoAnimal a2) {
		try {
			Object value1 = TipoAnimal.class.getField(this.sortField).get(a1);
			Object value2 = TipoAnimal.class.getField(this.sortField).get(a2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
