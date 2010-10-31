package edu.stanford.math.primitivelib.autogen.pair;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;


/**
 * This class implements a pair (a, b), where a is a T and b 
 * is a float. Note that any instance of this class is immutable, and
 * implements value semantics.
 * 
 * @author Andrew Tausz
 *
 */
public class ObjectFloatPair<T> {
	/*
	 * Make the fields first and second final to maintain immutability.
	 */
	protected final T first;
	protected final float second;
	
	/**
	 * Constructor which initializes the pair.
	 * 
	 * @param first the value of the first component
	 * @param second the value of the second component
	 */
	public ObjectFloatPair(T first, float second) {
		this.first = first;
		this.second = second;
	}
	
	/**
	 * Constructor which initializes from another ObjectFloatPair<T>.
	 * 
	 * @param pair the ObjectFloatPair<T> to initialize from
	 */
	public ObjectFloatPair(ObjectFloatPair<T> pair) {
		this.first = pair.first;
		this.second = pair.second;
	}
	
	/**
	 * Get the first component.
	 * 
	 * @return the first component
	 */
	public T getFirst() {
		return this.first;
	}
	
	/**
	 * Get the second component.
	 * 
	 * @return the second component
	 */
	public float getSecond() {
		return this.second;
	}
	
	@Override
	public String toString() {
		return ("(" + first + ", " + second + ")");
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(first).append(second).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ObjectFloatPair<?> other = (ObjectFloatPair<?>) obj;
		return new EqualsBuilder().append(first, other.first).append(second, other.second).isEquals();
	}
}