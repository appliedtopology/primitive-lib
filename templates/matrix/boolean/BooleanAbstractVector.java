



public interface BooleanAbstractVector extends Iterable<IntBooleanUnorderedPair> {
	public abstract BooleanAbstractVector like(int size);
	
	public abstract boolean get(int index);
	public abstract void set(int index, boolean value);
	public abstract int getLength();
	
	public abstract boolean innerProduct(BooleanAbstractVector other);
		
	public abstract String toString();
}