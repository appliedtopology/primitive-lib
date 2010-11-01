package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntObjectHashMap;
import gnu.trove.TIntObjectIterator;




public class ObjectSparseMatrix<R> implements ObjectAbstractMatrix<R> {
	/**
	 * We use a row-wise storage scheme. The variable map stores
	 * the rows of the matrix on an as-needed basis. Each row is a 
	 * ObjectSparseVector<R>. This choice was made so that matrix-vector
	 * products can be computed very quickly.
	 */
	protected final TIntObjectHashMap<ObjectSparseVector<R>> map = new TIntObjectHashMap<ObjectSparseVector<R>>();

	protected final int rows;
	protected final int columns;
	
	public ObjectSparseMatrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	public R get(int row, int column) {
		if (!this.map.contains(row)) {
			return null;
		}
		return this.map.get(row).get(column);
	}

	public int getNumColumns() {
		return this.columns;
	}

	public int getNumRows() {
		return this.rows;
	}

	public Iterator<ObjectMatrixEntry<R>> iterator() {
		return new ObjectSparseMatrixIterator<R>(this);
	}

	public ObjectAbstractMatrix<R> like(int rows, int columns) {
		return new ObjectSparseMatrix<R>(rows, columns);
	}
	
	public int getNumNonzeroElements() {
		int num = 0;
		for (TIntObjectIterator<ObjectSparseVector<R>> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			num += iterator.value().getNumNonzeroElements();
		}
		return num;
	}

	
	public void set(int row, int column, R value) {
		if (value == null) {
			if (!this.map.contains(row)) {
				return;
			} else {
				this.map.remove(column);
			}
		} else {
			if (!this.map.contains(row)) {
				this.map.put(row, new ObjectSparseVector<R>(this.columns));
			}
			this.map.get(row).set(column, value);
		}
	}
	
	public ObjectSparseMatrix<R> transpose() {
		ObjectSparseMatrix<R> result = new ObjectSparseMatrix<R>(columns, rows);
		
		for (ObjectMatrixEntry<R> entry: this) {
			result.set(entry.getCol(), entry.getRow(), entry.getValue());
		}
		
		return result;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int count = 0;
		for (ObjectMatrixEntry<R> entry: this) {
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