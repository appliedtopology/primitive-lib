package edu.stanford.math.primitivelib.autogen.matrix;




public interface FloatAbstractVector extends Iterable<FloatVectorEntry> {
	public abstract FloatAbstractVector like(int size);
	
	public abstract float get(int index);
	public abstract void set(int index, float value);
	public abstract int getLength();
	
		public abstract float innerProduct(FloatAbstractVector other);
		
	public abstract String toString();
}