

import java.util.Iterator;

import gnu.trove.TIntObjectHashMap;
import gnu.trove.TIntObjectIterator;


public class BooleanSparseMatrix implements BooleanAbstractMatrix {
	/**
	 * We use a row-wise storage scheme. The variable map stores
	 * the rows of the matrix on an as-needed basis. Each row is a 
	 * BooleanSparseVector. This choice was made so that matrix-vector
	 * products can be computed very quickly.
	 */
	protected final TIntObjectHashMap<BooleanSparseVector> map = new TIntObjectHashMap<BooleanSparseVector>();

	protected final int rows;
	protected final int columns;

	public BooleanSparseMatrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}

	public boolean get(int row, int column) {
		if (!this.map.contains(row)) {
			return false;
		}
		return this.map.get(row).get(column);
	}

	public int getNumColumns() {
		return this.columns;
	}

	public int getNumRows() {
		return this.rows;
	}

	public Iterator<BooleanMatrixEntry> iterator() {
		return new BooleanSparseMatrixIterator(this);
	}

	public BooleanAbstractMatrix like(int rows, int columns) {
		return new BooleanSparseMatrix(rows, columns);
	}

	public int getNumNonzeroElements() {
		int num = 0;
		for (TIntObjectIterator<BooleanSparseVector> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			num += iterator.value().getNumNonzeroElements();
		}
		return num;
	}

	public BooleanAbstractVector multiply(BooleanAbstractVector vector) {
		BooleanAbstractVector result = new BooleanSparseVector(this.rows);
		boolean innerProductValue = false;
		for (TIntObjectIterator<BooleanSparseVector> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			innerProductValue = iterator.value().innerProduct(vector);
			if (innerProductValue) {
				result.set(iterator.key(), innerProductValue);
			}
		}

		return result;
	}

	public void set(int row, int column, boolean value) {
		if (value) {
			if (!this.map.contains(row)) {
				this.map.put(row, new BooleanSparseVector(this.columns));
			}
			this.map.get(row).set(column, value);
		} else {
			if (!this.map.contains(row)) {
				return;
			} else {
				this.map.remove(column);
			}
		}
	}
}