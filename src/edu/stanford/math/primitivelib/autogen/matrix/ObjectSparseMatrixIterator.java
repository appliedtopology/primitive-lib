package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntObjectIterator;



public class ObjectSparseMatrixIterator<R> implements Iterator<ObjectMatrixEntry<R>> {
	private final TIntObjectIterator<ObjectSparseVector<R>> rowIterator;
	private Iterator<ObjectVectorEntry<R>> columnIterator;
	
	ObjectSparseMatrixIterator(ObjectSparseMatrix<R> matrix) {
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

	public ObjectMatrixEntry<R> next() {
		if (!columnIterator.hasNext()) {
			// advance to the next row
			this.rowIterator.advance();
			this.columnIterator = this.rowIterator.value().iterator();
			
		}
		int row = rowIterator.key();
		ObjectVectorEntry<R> columnValuePair = columnIterator.next();
		int column = columnValuePair.getIndex();
		R value = columnValuePair.getValue();
		return new ObjectMatrixEntry<R>(row, column, value);
	}

	public void remove() {
		this.columnIterator.remove();
	}
}