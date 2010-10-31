package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntLongIterator;



public class LongSparseVectorIterator implements Iterator<LongVectorEntry> {
	private final TIntLongIterator iterator;
	
	public LongSparseVectorIterator(LongSparseVector vector) {
		this.iterator = vector.map.iterator();
	}
	
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public LongVectorEntry next() {
		return new LongVectorEntry(this.iterator.key(), this.iterator.value());
	}

	public void remove() {
		this.iterator.remove();
	}
}