package edu.stanford.math.primitivelib.autogen.matrix;

import java.util.Iterator;

import gnu.trove.TIntObjectIterator;



public class ObjectSparseVectorIterator<R> implements Iterator<ObjectVectorEntry<R>> {
	private final TIntObjectIterator<R> iterator;
	
	public ObjectSparseVectorIterator(ObjectSparseVector<R> vector) {
		this.iterator = vector.map.iterator();
	}
	
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public ObjectVectorEntry<R> next() {
		this.iterator.advance();
		return new ObjectVectorEntry<R>(this.iterator.key(), this.iterator.value());
	}

	public void remove() {
		this.iterator.remove();
	}
}