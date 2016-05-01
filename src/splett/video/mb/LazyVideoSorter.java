package splett.video.mb;

import java.util.Comparator;

import org.primefaces.model.SortOrder;

import splett.video.Video;

public class LazyVideoSorter implements Comparator<Video> {
	private String sortField;

	private SortOrder sortOrder;

	public LazyVideoSorter(String sortField, SortOrder sortOrder) {
		this.sortField = sortField;
		this.sortOrder = sortOrder;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(Video u1, Video u2) {
		try {
			Object value1 = Video.class.getField(this.sortField).get(u1);
			Object value2 = Video.class.getField(this.sortField).get(u2);

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
