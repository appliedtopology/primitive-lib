package edu.stanford.math.primitivelib.autogen.matrix;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import gnu.trove.TIntObjectHashMap;



/**
 * This class provides a sparse vector implementation of the interface ObjectAbstractVector<R>.
 * It only stores non-zero entries thus is suited for applications where the dimension of the
 * vector is large, but most of the elements are zero.
 * 
 * @author autogen
 *
 * @param <R>
 */
public class ObjectSparseVector<R> implements ObjectAbstractVector<R> {
	/**
	 * This hash map stores the index -> value mappings. It is designed for
	 * fast lookups.
	 */
	protected final TIntObjectHashMap<R> map = new TIntObjectHashMap<R>();
	
	/**
	 * This is the size (or dimension) of the vector. Note that this is not the
	 * actual number of entries, but is merely one past the maximum allowable index.
	 */
	protected final int size;
	
	/**
	 * This constructor initializes the vector have the specified size.
	 * 
	 * @param size the size to initialize to
	 */
	public ObjectSparseVector(int size) {
		this.size = size;
	}
	
	/**
	 * This constructor initializes the vector with the contents of the
	 * given array.
	 * 
	 * @param array the array to initialize with
	 */
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
	
	/**
	 * This function gets the number of non-zero elements in the vector.
	 * 
	 * @return the number of non-zero entries
	 */
	public int getNumNonzeroElements() {
		return this.map.size();
	}
	
	/**
	 * This function returns the density (number of non-zero entries / size) of the vector.
	 * 
	 * @return the density of the vector
	 */
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
	
	/**
	 * This function writes the contents of the vector to a given Writer object.
	 * 
	 * @param writer the Writer object to write to
	 * @throws IOException
	 */
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