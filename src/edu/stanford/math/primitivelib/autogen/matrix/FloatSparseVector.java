package edu.stanford.math.primitivelib.autogen.matrix;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import gnu.trove.TIntFloatHashMap;
import gnu.trove.TIntFloatIterator;



public class FloatSparseVector implements FloatAbstractVector {
	protected final TIntFloatHashMap map = new TIntFloatHashMap();
	protected final int size;
	
	public FloatSparseVector(int size) {
		this.size = size;
	}
	
	public FloatSparseVector(float[] array) {
		this.size = array.length;
		for (int i = 0; i < size; i++) {
			if (array[i] == 0.0f) {
				continue;
			}
			this.map.put(i, array[i]);
		}
	}
	
	public FloatAbstractVector like(int size) {
		return new FloatSparseVector(size);
	}
	
	public int getNumNonzeroElements() {
		return this.map.size();
	}
	
	public double getDensity() {
		return ((double) this.getNumNonzeroElements()) / ((double) (size));
	}
	
	public void set(int index, float value) {
		if (value == 0.0f) {
			this.map.remove(index);
		} else {
			this.map.put(index, value);
		}
	}
	
	public float get(int index) {
		return this.map.get(index);
	}
	
	public int getLength() {
		return this.size;
	}
	
		public float innerProduct(FloatSparseVector other) {
		float sum = 0;
		FloatSparseVector smaller = (this.map.size() < other.map.size() ? this : other);
		FloatSparseVector larger = (this.map.size() < other.map.size() ? other : this);
		
		for (TIntFloatIterator iterator = smaller.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * larger.get(iterator.key());
		}
		
		return sum;
	}
	
	public float innerProduct(float[] other) {
		float sum = 0;
		for (TIntFloatIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * other[iterator.key()];
		}
		return sum;
	}
	
	public float innerProduct(FloatAbstractVector other) {
		if (other instanceof FloatSparseVector) {
			return this.innerProduct((FloatSparseVector) other);
		}
		float sum = 0;
		for (TIntFloatIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * other.get(iterator.key());
		}
		return sum;
	}
		
	public Iterator<FloatVectorEntry> iterator() {
		return new FloatSparseVectorIterator(this);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		builder.append("[");
		for (FloatVectorEntry pair: this) {
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
		for (FloatVectorEntry pair: this) {
			if (index > 0) {
				writer.append(", ");
			}
			writer.write(pair.getIndex());
			writer.write(": ");
						writer.write(Float .toString(pair.getValue()));
						index++;
		}
		writer.write("]");
	}
	
		
	public float[] toArray() {
		float[] array = new float[this.size];
		
		for (FloatVectorEntry pair: this) {
			array[pair.getIndex()] = pair.getValue();
		}
		
		return array;
	}
	
	}