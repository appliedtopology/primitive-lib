package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntDoubleIterator;



public class DoubleSparseVectorIterator implements Iterator<DoubleVectorEntry> {
	private final TIntDoubleIterator iterator;
	
	public DoubleSparseVectorIterator(DoubleSparseVector vector) {
		this.iterator = vector.map.iterator();
	}
	
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public DoubleVectorEntry next() {
		return new DoubleVectorEntry(this.iterator.key(), this.iterator.value());
	}

	public void remove() {
		this.iterator.remove();
	}
}