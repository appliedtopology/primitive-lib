package edu.stanford.math.primitivelib.autogen.matrix;




public interface DoubleAbstractVector extends Iterable<DoubleVectorEntry> {
	public abstract DoubleAbstractVector like(int size);
	
	public abstract double get(int index);
	public abstract void set(int index, double value);
	public abstract int getLength();
	
		public abstract double innerProduct(DoubleAbstractVector other);
		
	public abstract String toString();
}