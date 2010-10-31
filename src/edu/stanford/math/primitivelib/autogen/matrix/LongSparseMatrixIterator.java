package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntObjectIterator;



public class LongSparseMatrixIterator implements Iterator<LongMatrixEntry> {
	private final TIntObjectIterator<LongSparseVector> rowIterator;
	private Iterator<LongVectorEntry> columnIterator;
	
	LongSparseMatrixIterator(LongSparseMatrix matrix) {
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

	public LongMatrixEntry next() {
		if (!columnIterator.hasNext()) {
			// advance to the next row
			this.rowIterator.advance();
			this.columnIterator = this.rowIterator.value().iterator();
			
		}
		int row = rowIterator.key();
		LongVectorEntry columnValuePair = columnIterator.next();
		int column = columnValuePair.getIndex();
		long value = columnValuePair.getValue();
		return new LongMatrixEntry(row, column, value);
	}

	public void remove() {
		this.columnIterator.remove();
	}
}