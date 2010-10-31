package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntIterator;


public class BooleanSparseVectorIterator implements Iterator<BooleanVectorEntry> {
	private final TIntIterator iterator;
	
	public BooleanSparseVectorIterator(BooleanSparseVector vector) {
		this.iterator = vector.map.iterator();
	}
	
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public BooleanVectorEntry next() {
		return new BooleanVectorEntry(this.iterator.next());
	}

	public void remove() {
		this.iterator.remove();
	}
};
