package edu.stanford.math.primitivelib.autogen.matrix;




public interface IntAbstractVector extends Iterable<IntVectorEntry> {
	public abstract IntAbstractVector like(int size);
	
	public abstract int get(int index);
	public abstract void set(int index, int value);
	public abstract int getLength();
	
		public abstract int innerProduct(IntAbstractVector other);
		
	public abstract String toString();
}