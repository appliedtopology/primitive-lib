package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntObjectIterator;



public class IntSparseMatrixIterator implements Iterator<IntMatrixEntry> {
	private final TIntObjectIterator<IntSparseVector> rowIterator;
	private Iterator<IntVectorEntry> columnIterator;
	
	IntSparseMatrixIterator(IntSparseMatrix matrix) {
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

	public IntMatrixEntry next() {
		if (!columnIterator.hasNext()) {
			// advance to the next row
			this.rowIterator.advance();
			this.columnIterator = this.rowIterator.value().iterator();
			
		}
		int row = rowIterator.key();
		IntVectorEntry columnValuePair = columnIterator.next();
		int column = columnValuePair.getIndex();
		int value = columnValuePair.getValue();
		return new IntMatrixEntry(row, column, value);
	}

	public void remove() {
		this.columnIterator.remove();
	}
}