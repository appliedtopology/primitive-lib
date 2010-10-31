package edu.stanford.math.primitivelib.autogen.matrix;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import gnu.trove.TIntLongHashMap;
import gnu.trove.TIntLongIterator;



public class LongSparseVector implements LongAbstractVector {
	protected final TIntLongHashMap map = new TIntLongHashMap();
	protected final int size;
	
	public LongSparseVector(int size) {
		this.size = size;
	}
	
	public LongSparseVector(long[] array) {
		this.size = array.length;
		for (int i = 0; i < size; i++) {
			if (array[i] == 0L) {
				continue;
			}
			this.map.put(i, array[i]);
		}
	}
	
	public LongAbstractVector like(int size) {
		return new LongSparseVector(size);
	}
	
	public int getNumNonzeroElements() {
		return this.map.size();
	}
	
	public double getDensity() {
		return ((double) this.getNumNonzeroElements()) / ((double) (size));
	}
	
	public void set(int index, long value) {
		if (value == 0L) {
			this.map.remove(index);
		} else {
			this.map.put(index, value);
		}
	}
	
	public long get(int index) {
		return this.map.get(index);
	}
	
	public int getLength() {
		return this.size;
	}
	
		public long innerProduct(LongSparseVector other) {
		long sum = 0;
		LongSparseVector smaller = (this.map.size() < other.map.size() ? this : other);
		LongSparseVector larger = (this.map.size() < other.map.size() ? other : this);
		
		for (TIntLongIterator iterator = smaller.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * larger.get(iterator.key());
		}
		
		return sum;
	}
	
	public long innerProduct(long[] other) {
		long sum = 0;
		for (TIntLongIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * other[iterator.key()];
		}
		return sum;
	}
	
	public long innerProduct(LongAbstractVector other) {
		if (other instanceof LongSparseVector) {
			return this.innerProduct((LongSparseVector) other);
		}
		long sum = 0;
		for (TIntLongIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * other.get(iterator.key());
		}
		return sum;
	}
		
	public Iterator<LongVectorEntry> iterator() {
		return new LongSparseVectorIterator(this);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		builder.append("[");
		for (LongVectorEntry pair: this) {
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
		for (LongVectorEntry pair: this) {
			if (index > 0) {
				writer.append(", ");
			}
			writer.write(pair.getIndex());
			writer.write(": ");
						writer.write(Long .toString(pair.getValue()));
						index++;
		}
		writer.write("]");
	}
	
		
	public long[] toArray() {
		long[] array = new long[this.size];
		
		for (LongVectorEntry pair: this) {
			array[pair.getIndex()] = pair.getValue();
		}
		
		return array;
	}
	
	}