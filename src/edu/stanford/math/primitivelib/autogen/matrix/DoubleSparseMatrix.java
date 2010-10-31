package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntObjectHashMap;
import gnu.trove.TIntObjectIterator;




public class DoubleSparseMatrix implements DoubleAbstractMatrix {
	/**
	 * We use a row-wise storage scheme. The variable map stores
	 * the rows of the matrix on an as-needed basis. Each row is a 
	 * DoubleSparseVector. This choice was made so that matrix-vector
	 * products can be computed very quickly.
	 */
	protected final TIntObjectHashMap<DoubleSparseVector> map = new TIntObjectHashMap<DoubleSparseVector>();

	protected final int rows;
	protected final int columns;
	
	public DoubleSparseMatrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	public double get(int row, int column) {
		if (!this.map.contains(row)) {
			return 0.0d;
		}
		return this.map.get(row).get(column);
	}

	public int getNumColumns() {
		return this.columns;
	}

	public int getNumRows() {
		return this.rows;
	}

	public Iterator<DoubleMatrixEntry> iterator() {
		return new DoubleSparseMatrixIterator(this);
	}

	public DoubleAbstractMatrix like(int rows, int columns) {
		return new DoubleSparseMatrix(rows, columns);
	}
	
	public int getNumNonzeroElements() {
		int num = 0;
		for (TIntObjectIterator<DoubleSparseVector> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			num += iterator.value().getNumNonzeroElements();
		}
		return num;
	}

		public DoubleAbstractVector multiply(DoubleAbstractVector vector) {
		DoubleAbstractVector result = new DoubleSparseVector(this.rows);
		double innerProductValue = 0;
		for (TIntObjectIterator<DoubleSparseVector> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			innerProductValue = iterator.value().innerProduct(vector);
			if (innerProductValue != 0) {
				result.set(iterator.key(), innerProductValue);
			}
		}
		
		return result;
	}
	
	public void set(int row, int column, double value) {
		if (value == 0.0d) {
			if (!this.map.contains(row)) {
				return;
			} else {
				this.map.remove(column);
			}
		} else {
			if (!this.map.contains(row)) {
				this.map.put(row, new DoubleSparseVector(this.columns));
			}
			this.map.get(row).set(column, value);
		}
	}
}