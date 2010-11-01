package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntObjectHashMap;
import gnu.trove.TIntObjectIterator;




public class FloatSparseMatrix implements FloatAbstractMatrix {
	/**
	 * We use a row-wise storage scheme. The variable map stores
	 * the rows of the matrix on an as-needed basis. Each row is a 
	 * FloatSparseVector. This choice was made so that matrix-vector
	 * products can be computed very quickly.
	 */
	protected final TIntObjectHashMap<FloatSparseVector> map = new TIntObjectHashMap<FloatSparseVector>();

	protected final int rows;
	protected final int columns;
	
	public FloatSparseMatrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	public float get(int row, int column) {
		if (!this.map.contains(row)) {
			return 0.0f;
		}
		return this.map.get(row).get(column);
	}

	public int getNumColumns() {
		return this.columns;
	}

	public int getNumRows() {
		return this.rows;
	}

	public Iterator<FloatMatrixEntry> iterator() {
		return new FloatSparseMatrixIterator(this);
	}

	public FloatAbstractMatrix like(int rows, int columns) {
		return new FloatSparseMatrix(rows, columns);
	}
	
	public int getNumNonzeroElements() {
		int num = 0;
		for (TIntObjectIterator<FloatSparseVector> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			num += iterator.value().getNumNonzeroElements();
		}
		return num;
	}

		public FloatAbstractVector multiply(FloatAbstractVector vector) {
		FloatAbstractVector result = new FloatSparseVector(this.rows);
		float innerProductValue = 0;
		for (TIntObjectIterator<FloatSparseVector> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			innerProductValue = iterator.value().innerProduct(vector);
			if (innerProductValue != 0) {
				result.set(iterator.key(), innerProductValue);
			}
		}
		
		return result;
	}
	
	public void set(int row, int column, float value) {
		if (value == 0.0f) {
			if (!this.map.contains(row)) {
				return;
			} else {
				this.map.remove(column);
			}
		} else {
			if (!this.map.contains(row)) {
				this.map.put(row, new FloatSparseVector(this.columns));
			}
			this.map.get(row).set(column, value);
		}
	}
	
	public FloatSparseMatrix transpose() {
		FloatSparseMatrix result = new FloatSparseMatrix(columns, rows);
		
		for (FloatMatrixEntry entry: this) {
			result.set(entry.getCol(), entry.getRow(), entry.getValue());
		}
		
		return result;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int count = 0;
		for (FloatMatrixEntry entry: this) {
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