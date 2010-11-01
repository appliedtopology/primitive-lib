package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntIntIterator;



public class IntSparseVectorIterator implements Iterator<IntVectorEntry> {
	private final TIntIntIterator iterator;
	
	public IntSparseVectorIterator(IntSparseVector vector) {
		this.iterator = vector.map.iterator();
	}
	
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public IntVectorEntry next() {
		this.iterator.advance();
		return new IntVectorEntry(this.iterator.key(), this.iterator.value());
	}

	public void remove() {
		this.iterator.remove();
	}
}