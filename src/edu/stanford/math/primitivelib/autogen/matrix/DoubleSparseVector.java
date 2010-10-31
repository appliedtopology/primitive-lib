package edu.stanford.math.primitivelib.autogen.matrix;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import gnu.trove.TIntDoubleHashMap;
import gnu.trove.TIntDoubleIterator;



public class DoubleSparseVector implements DoubleAbstractVector {
	protected final TIntDoubleHashMap map = new TIntDoubleHashMap();
	protected final int size;
	
	public DoubleSparseVector(int size) {
		this.size = size;
	}
	
	public DoubleSparseVector(double[] array) {
		this.size = array.length;
		for (int i = 0; i < size; i++) {
			if (array[i] == 0.0d) {
				continue;
			}
			this.map.put(i, array[i]);
		}
	}
	
	public DoubleAbstractVector like(int size) {
		return new DoubleSparseVector(size);
	}
	
	public int getNumNonzeroElements() {
		return this.map.size();
	}
	
	public double getDensity() {
		return ((double) this.getNumNonzeroElements()) / ((double) (size));
	}
	
	public void set(int index, double value) {
		if (value == 0.0d) {
			this.map.remove(index);
		} else {
			this.map.put(index, value);
		}
	}
	
	public double get(int index) {
		return this.map.get(index);
	}
	
	public int getLength() {
		return this.size;
	}
	
		public double innerProduct(DoubleSparseVector other) {
		double sum = 0;
		DoubleSparseVector smaller = (this.map.size() < other.map.size() ? this : other);
		DoubleSparseVector larger = (this.map.size() < other.map.size() ? other : this);
		
		for (TIntDoubleIterator iterator = smaller.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * larger.get(iterator.key());
		}
		
		return sum;
	}
	
	public double innerProduct(double[] other) {
		double sum = 0;
		for (TIntDoubleIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * other[iterator.key()];
		}
		return sum;
	}
	
	public double innerProduct(DoubleAbstractVector other) {
		if (other instanceof DoubleSparseVector) {
			return this.innerProduct((DoubleSparseVector) other);
		}
		double sum = 0;
		for (TIntDoubleIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			sum += iterator.value() * other.get(iterator.key());
		}
		return sum;
	}
		
	public Iterator<DoubleVectorEntry> iterator() {
		return new DoubleSparseVectorIterator(this);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		builder.append("[");
		for (DoubleVectorEntry pair: this) {
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
		for (DoubleVectorEntry pair: this) {
			if (index > 0) {
				writer.append(", ");
			}
			writer.write(pair.getIndex());
			writer.write(": ");
						writer.write(Double .toString(pair.getValue()));
						index++;
		}
		writer.write("]");
	}
	
		
	public double[] toArray() {
		double[] array = new double[this.size];
		
		for (DoubleVectorEntry pair: this) {
			array[pair.getIndex()] = pair.getValue();
		}
		
		return array;
	}
	
	}