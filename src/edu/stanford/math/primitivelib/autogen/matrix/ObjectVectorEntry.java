package edu.stanford.math.primitivelib.autogen.matrix;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



public class ObjectVectorEntry<R> {
	private final int index;
	private final R value;
	
	public ObjectVectorEntry(int index, R value) {
		this.index = index;
		this.value = value;
	}
	
	public int getIndex() {
		return this.index;
	}

	public R getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return ("(" + index + ", " + value + ")");
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(index).append(value).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ObjectVectorEntry<?> other = (ObjectVectorEntry<?>) obj;
		return new EqualsBuilder().append(index, other.index).append(value, other.value).isEquals();

	}
}