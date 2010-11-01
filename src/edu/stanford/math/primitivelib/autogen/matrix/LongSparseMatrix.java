package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntObjectHashMap;
import gnu.trove.TIntObjectIterator;




public class LongSparseMatrix implements LongAbstractMatrix {
	/**
	 * We use a row-wise storage scheme. The variable map stores
	 * the rows of the matrix on an as-needed basis. Each row is a 
	 * LongSparseVector. This choice was made so that matrix-vector
	 * products can be computed very quickly.
	 */
	protected final TIntObjectHashMap<LongSparseVector> map = new TIntObjectHashMap<LongSparseVector>();

	protected final int rows;
	protected final int columns;
	
	public LongSparseMatrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	public long get(int row, int column) {
		if (!this.map.contains(row)) {
			return 0L;
		}
		return this.map.get(row).get(column);
	}

	public int getNumColumns() {
		return this.columns;
	}

	public int getNumRows() {
		return this.rows;
	}

	public Iterator<LongMatrixEntry> iterator() {
		return new LongSparseMatrixIterator(this);
	}

	public LongAbstractMatrix like(int rows, int columns) {
		return new LongSparseMatrix(rows, columns);
	}
	
	public int getNumNonzeroElements() {
		int num = 0;
		for (TIntObjectIterator<LongSparseVector> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			num += iterator.value().getNumNonzeroElements();
		}
		return num;
	}

		public LongAbstractVector multiply(LongAbstractVector vector) {
		LongAbstractVector result = new LongSparseVector(this.rows);
		long innerProductValue = 0;
		for (TIntObjectIterator<LongSparseVector> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			innerProductValue = iterator.value().innerProduct(vector);
			if (innerProductValue != 0) {
				result.set(iterator.key(), innerProductValue);
			}
		}
		
		return result;
	}
	
	public void set(int row, int column, long value) {
		if (value == 0L) {
			if (!this.map.contains(row)) {
				return;
			} else {
				this.map.remove(column);
			}
		} else {
			if (!this.map.contains(row)) {
				this.map.put(row, new LongSparseVector(this.columns));
			}
			this.map.get(row).set(column, value);
		}
	}
	
	public LongSparseMatrix transpose() {
		LongSparseMatrix result = new LongSparseMatrix(columns, rows);
		
		for (LongMatrixEntry entry: this) {
			result.set(entry.getCol(), entry.getRow(), entry.getValue());
		}
		
		return result;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int count = 0;
		for (LongMatrixEntry entry: this) {
			if (count > 0) {
				builder.append(", ");
			}
			builder.append("(" + entry.getRow() + ", " + entry.getCol() + "): " + entry.getValue());
			count++;
		}
		builder.append("]");
		return builder.toString();
	}
}