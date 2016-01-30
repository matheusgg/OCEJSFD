package datatable.datamodel;

import java.util.Arrays;
import java.util.Comparator;

import javax.faces.model.DataModel;

public class SortDataModel<E> extends DataModel<E> {

	private DataModel<E> wrapped;
	private Integer[] rows;

	public SortDataModel(DataModel<E> wrapped) {
		if (wrapped == null) {
			throw new IllegalArgumentException("Wrapped cannot be null.");
		}
		this.wrapped = wrapped;
		this.initRows();
	}

	private void initRows() {
		int rowCount = this.wrapped.getRowCount();
		this.rows = new Integer[rowCount];
		for (int i = 0; i < rowCount; i++) {
			this.rows[i] = i;
		}
	}

	private E getRowDataByIndex(int index) {
		int oldIndex = this.wrapped.getRowIndex();
		this.wrapped.setRowIndex(index);
		E data = this.wrapped.getRowData();
		this.wrapped.setRowIndex(oldIndex);
		return data;
	}

	public void sortBy(Comparator<E> comp) {
		Comparator<Integer> indexComparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return comp.compare(getRowDataByIndex(o1), getRowDataByIndex(o2));
			}
		};
		Arrays.sort(this.rows, indexComparator);
	}

	@Override
	public boolean isRowAvailable() {
		return this.wrapped.isRowAvailable();
	}

	@Override
	public int getRowCount() {
		return this.rows != null ? this.rows.length : -1;
	}

	@Override
	public E getRowData() {
		return this.wrapped.getRowData();
	}

	@Override
	public int getRowIndex() {
		return this.wrapped.getRowIndex();
	}

	@Override
	public void setRowIndex(int rowIndex) {
		if (rowIndex >= 0 && rowIndex < this.rows.length) {
			this.wrapped.setRowIndex(this.rows[rowIndex]);
		} else {
			this.wrapped.setRowIndex(rowIndex);
		}
	}

	@Override
	public Object getWrappedData() {
		return this.wrapped.getWrappedData();
	}

	@Override
	public void setWrappedData(Object data) {
		if (data == null) {
			throw new IllegalArgumentException("Data cannot be null.");
		}
		this.wrapped.setWrappedData(data);
	}

}
