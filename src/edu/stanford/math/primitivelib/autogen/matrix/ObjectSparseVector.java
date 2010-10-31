package edu.stanford.math.primitivelib.autogen.matrix;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import gnu.trove.TIntObjectHashMap;



public class ObjectSparseVector<R> implements ObjectAbstractVector<R> {
	protected final TIntObjectHashMap<R> map = new TIntObjectHashMap<R>();
	protected final int size;
	
	public ObjectSparseVector(int size) {
		this.size = size;
	}
	
	public ObjectSparseVector(R[] array) {
		this.size = array.length;
		for (int i = 0; i < size; i++) {
			if (array[i] == null) {
				continue;
			}
			this.map.put(i, array[i]);
		}
	}
	
	public ObjectAbstractVector<R> like(int size) {
		return new ObjectSparseVector<R>(size);
	}
	
	public int getNumNonzeroElements() {
		return this.map.size();
	}
	
	public double getDensity() {
		return ((double) this.getNumNonzeroElements()) / ((double) (size));
	}
	
	public void set(int index, R value) {
		if (value == null) {
			this.map.remove(index);
		} else {
			this.map.put(index, value);
		}
	}
	
	public R get(int index) {
		return this.map.get(index);
	}
	
	public int getLength() {
		return this.size;
	}
	
		
	public Iterator<ObjectVectorEntry<R>> iterator() {
		return new ObjectSparseVectorIterator<R>(this);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		builder.append("[");
		for (ObjectVectorEntry<R> pair: this) {
			if (index > 0) {
				builder.append(", ");
			}
			builder.append(pair.getIndex());
			builder.append(": ");
			builder.append(pair.getValue());
			index++;
		}
		builder.append("]");
		
		return builder.toString();
	}
	
	public void write(Writer writer) throws IOException {
		int index = 0;
		writer.write("[");
		for (ObjectVectorEntry<R> pair: this) {
			if (index > 0) {
				writer.append(", ");
			}
			writer.write(pair.getIndex());
			writer.write(": ");
						writer.write(pair.getValue().toString());
						index++;
		}
		writer.write("]");
	}
	
	}