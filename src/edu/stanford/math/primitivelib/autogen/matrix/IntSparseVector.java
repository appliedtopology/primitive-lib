package edu.stanford.math.primitivelib.autogen.matrix;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import gnu.trove.TIntIntHashMap;
import gnu.trove.TIntIntIterator;



public class IntSparseVector implements IntAbstractVector {
	protected final TIntIntHashMap map = new TIntIntHashMap();
	protected final int size;
	
	public IntSparseVector(int size) {
		this.size = size;
	}
	
	public IntSparseVector(int[] array) {
		this.size = array.length;
		for (int i = 0; i < size; i++) {
			if (array[i] == 0) {
				continue;
			}
			this.map.put(i, array[i]);
		}
	}
	
	public IntAbstractVector like(int size) {
		return new IntSparseVector(size);
	}
	
	public int getNumNonzeroElements() {
		return this.map.size();
	}
	
	public double getDensity() {
		return ((double) this.getNumNonzeroElements()) / ((double) (size));
	}
	
	public void set(int index, int value) {
		if (value == 0) {
			this.map.remove(index);
		} else {
			this.map.put(index, value);
		}
	}
	
	public int get(int index) {
		return this.map.get(index);
	}
	
	public int getLength() {
		return this.size;
	}
	
		public int innerProduct(IntSparseVector other) {
		int sum = 0;
		IntSparseVector smaller = (this.map.size() < other.map.size() ? this : other);
		IntSparseVector larger = (this.map.size() < other.map.size() ? other : this);
		
		for (TIntIntIterator iterator = smaller.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * larger.get(iterator.key());
		}
		
		return sum;
	}
	
	public int innerProduct(int[] other) {
		int sum = 0;
		for (TIntIntIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * other[iterator.key()];
		}
		return sum;
	}
	
	public int innerProduct(IntAbstractVector other) {
		if (other instanceof IntSparseVector) {
			return this.innerProduct((IntSparseVector) other);
		}
		int sum = 0;
		for (TIntIntIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * other.get(iterator.key());
		}
		return sum;
	}
		
	public Iterator<IntVectorEntry> iterator() {
		return new IntSparseVectorIterator(this);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		builder.append("[");
		for (IntVectorEntry pair: this) {
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
		for (IntVectorEntry pair: this) {
			if (index > 0) {
				writer.append(", ");
			}
			writer.write(pair.getIndex());
			writer.write(": ");
						writer.write(Integer .toString(pair.getValue()));
						index++;
		}
		writer.write("]");
	}
	
		
	public int[] toArray() {
		int[] array = new int[this.size];
		
		for (IntVectorEntry pair: this) {
			array[pair.getIndex()] = pair.getValue();
		}
		
		return array;
	}
	
	}