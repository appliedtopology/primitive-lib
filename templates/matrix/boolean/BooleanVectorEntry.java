
/**
 * This class implements a pair (a, b, c), where a and b are ints and
 * is a boolean. Note that any instance of this class is immutable, and
 * implements value semantics.
 * 
 * @author Andrew Tausz
 *
 */
public class BooleanVectorEntry {
	private final int index;
	
	/**
	 * Constructor which initializes the pair.
	 * 
	 * @param first the value of the first component
	 * @param second the value of the second component
	 */
	public BooleanVectorEntry(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return index;
	}
	
	public boolean getValue() {
		return true;
	}
	
	@Override
	public String toString() {
		return ("(" + index + ", " + 1 + ")");
	}
	
	@Override
	public int hashCode() {
		return index;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BooleanVectorEntry other = (BooleanVectorEntry) obj;
		return (index == other.index);

	}
}