package edu.stanford.math.primitivelib.autogen.matrix;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



public class LongVectorEntry {
	private final int index;
	private final long value;
	
	public LongVectorEntry(int index, long value) {
		this.index = index;
		this.value = value;
	}
	
	public int getIndex() {
		return this.index;
	}

	public long getValue() {
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
		final LongVectorEntry other = (LongVectorEntry) obj;
		return new EqualsBuilder().append(index, other.index).append(value, other.value).isEquals();

	}
}