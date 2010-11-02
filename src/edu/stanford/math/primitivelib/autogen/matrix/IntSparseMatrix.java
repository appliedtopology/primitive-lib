package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntObjectHashMap;
import gnu.trove.TIntObjectIterator;




/**
 * This class provides a sparse-matrix implementation of the IntAbstractMatrix
 * interface. It only stores non-zero elements, and is therefore suited for applications
 * where most of the entries in the matrix are zero.
 * 
 * @author autogen
 *
 */
public class IntSparseMatrix implements IntAbstractMatrix {
	/**
	 * We use a row-wise storage scheme. The variable map stores
	 * the rows of the matrix on an as-needed basis. Each row is a 
	 * IntSparseVector. This choice was made so that matrix-vector
	 * products can be computed very quickly.
	 */
	protected final TIntObjectHashMap<IntSparseVector> map = new TIntObjectHashMap<IntSparseVector>();

	/**
	 * The number of rows in the matrix.
	 */
	protected final int rows;

	/**
	 * The number of columns in the matrix.
	 */
	protected final int columns;

	/**
	 * This constructor initializes the class with the specified number
	 * of rows and columns.
	 * 
	 * @param rows the number of rows in the matrix
	 * @param columns the number of columns in the matrix
	 */
	public IntSparseMatrix(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
	}
	
	public int get(int row, int column) {
		if (!this.map.contains(row)) {
			return 0;
		}
		return this.map.get(row).get(column);
	}

	public int getNumColumns() {
		return this.columns;
	}

	public int getNumRows() {
		return this.rows;
	}

	public Iterator<IntMatrixEntry> iterator() {
		return new IntSparseMatrixIterator(this);
	}

	public IntAbstractMatrix like(int rows, int columns) {
		return new IntSparseMatrix(rows, columns);
	}
	
	/**
	 * This function returns the number of non-zero elements in the matrix.
	 * 
	 * @return the number of non-zero elements
	 */
	public int getNumNonzeroElements() {
		int num = 0;
		for (TIntObjectIterator<IntSparseVector> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			num += iterator.value().getNumNonzeroElements();
		}
		return num;
	}

		public IntAbstractVector multiply(IntAbstractVector vector) {
		IntAbstractVector result = new IntSparseVector(this.rows);
		int innerProductValue = 0;
		for (TIntObjectIterator<IntSparseVector> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			innerProductValue = iterator.value().innerProduct(vector);
			if (innerProductValue != 0) {
				result.set(iterator.key(), innerProductValue);
			}
		}
		
		return result;
	}
	
	public void set(int row, int column, int value) {
		if (value == 0) {
			if (!this.map.contains(row)) {
				return;
			} else {
				this.map.remove(column);
			}
		} else {
			if (!this.map.contains(row)) {
				this.map.put(row, new IntSparseVector(this.columns));
			}
			this.map.get(row).set(column, value);
		}
	}
	
	/**
	 * This function computes the transpose of the matrix.
	 * 
	 * @return the transpose of the matrix
	 */
	public IntSparseMatrix transpose() {
		IntSparseMatrix result = new IntSparseMatrix(columns, rows);
		
		for (IntMatrixEntry entry: this) {
			result.set(entry.getCol(), entry.getRow(), entry.getValue());
		}
		
		return result;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int count = 0;
		for (IntMatrixEntry entry: this) {
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