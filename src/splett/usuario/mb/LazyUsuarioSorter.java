package splett.usuario.mb;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import splett.usuario.Usuario;

public class LazyUsuarioSorter implements Comparator<Usuario> {

	private String sortField;

	private SortOrder sortOrder;

	public LazyUsuarioSorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(Usuario u1, Usuario u2) {
		try {
			Object value1 = Usuario.class.getField(this.sortField).get(u1);
			Object value2 = Usuario.class.getField(this.sortField).get(u2);

			int value = ((Comparable) value1).compareTo(value2);

			return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}