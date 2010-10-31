package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntObjectIterator;



public class FloatSparseMatrixIterator implements Iterator<FloatMatrixEntry> {
	private final TIntObjectIterator<FloatSparseVector> rowIterator;
	private Iterator<FloatVectorEntry> columnIterator;
	
	FloatSparseMatrixIterator(FloatSparseMatrix matrix) {
		this.rowIterator = matrix.map.iterator();
		this.rowIterator.advance();
		this.columnIterator = this.rowIterator.value().iterator();
	}
	
	public boolean hasNext() {
		if (this.rowIterator.hasNext()) {
			return true;
		} else {
			return this.columnIterator.hasNext();
		}
	}

	public FloatMatrixEntry next() {
		if (!columnIterator.hasNext()) {
			// advance to the next row
			this.rowIterator.advance();
			this.columnIterator = this.rowIterator.value().iterator();
			
		}
		int row = rowIterator.key();
		FloatVectorEntry columnValuePair = columnIterator.next();
		int column = columnValuePair.getIndex();
		float value = columnValuePair.getValue();
		return new FloatMatrixEntry(row, column, value);
	}

	public void remove() {
		this.columnIterator.remove();
	}
}