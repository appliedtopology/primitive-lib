package edu.stanford.math.primitivelib.autogen.matrix;




public interface ObjectAbstractVector<R> extends Iterable<ObjectVectorEntry<R>> {
	public abstract ObjectAbstractVector<R> like(int size);
	
	public abstract R get(int index);
	public abstract void set(int index, R value);
	public abstract int getLength();
	
		
	public abstract String toString();
}