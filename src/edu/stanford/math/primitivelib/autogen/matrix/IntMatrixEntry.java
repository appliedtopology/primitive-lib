package edu.stanford.math.primitivelib.autogen.matrix;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;



/**
 * This class implements a pair (a, b, c), where a and b are ints and
 * is of type int. Note that any instance of this class is immutable, and
 * implements value semantics.
 * 
 * @author autogen
 *
 */
public class IntMatrixEntry {
	private final int row;
	private final int col;
	private final int value;
	
	/**
	 * Constructor which initializes the entry.
	 * 
	 * @param row the row of the entry
	 * @param col the column of the entry
	 * @param value the value at the entry
	 */
	public IntMatrixEntry(int row, int col, int value) {
		this.row = row;
		this.col = col;
		this.value = value;
	}
	
	/**
	 * Gets the row of the entry.
	 * 
	 * @return the row
	 */
	public int getRow() {
		return this.row;
	}
	
	/**
	 * Gets the column of the entry.
	 * 
	 * @return the column
	 */
	public int getCol() {
		return this.col;
	}
	
	/**
	 * Gets the value in the matrix
	 * 
	 * @return the value of the entry
	 */
	public int getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return ("(" + row + ", " + col + ", " + value + ")");
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(row).append(col).append(value).toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IntMatrixEntry other = (IntMatrixEntry) obj;
		return new EqualsBuilder().append(row, other.row).append(col, other.col).append(value, other.value).isEquals();

	}
}