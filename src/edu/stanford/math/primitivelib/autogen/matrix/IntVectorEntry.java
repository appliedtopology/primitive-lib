package edu.stanford.math.primitivelib.autogen.matrix;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



public class IntVectorEntry {
	private final int index;
	private final int value;
	
	public IntVectorEntry(int index, int value) {
		this.index = index;
		this.value = value;
	}
	
	public int getIndex() {
		return this.index;
	}

	public int getValue() {
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
		final IntVectorEntry other = (IntVectorEntry) obj;
		return new EqualsBuilder().append(index, other.index).append(value, other.value).isEquals();

	}
}