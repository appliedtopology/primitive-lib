package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntFloatIterator;



public class FloatSparseVectorIterator implements Iterator<FloatVectorEntry> {
	private final TIntFloatIterator iterator;
	
	public FloatSparseVectorIterator(FloatSparseVector vector) {
		this.iterator = vector.map.iterator();
	}
	
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public FloatVectorEntry next() {
		return new FloatVectorEntry(this.iterator.key(), this.iterator.value());
	}

	public void remove() {
		this.iterator.remove();
	}
}