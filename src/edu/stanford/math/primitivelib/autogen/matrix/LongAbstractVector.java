package edu.stanford.math.primitivelib.autogen.matrix;




public interface LongAbstractVector extends Iterable<LongVectorEntry> {
	public abstract LongAbstractVector like(int size);
	
	public abstract long get(int index);
	public abstract void set(int index, long value);
	public abstract int getLength();
	
		public abstract long innerProduct(LongAbstractVector other);
		
	public abstract String toString();
}