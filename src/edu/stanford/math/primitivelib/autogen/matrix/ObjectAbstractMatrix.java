package edu.stanford.math.primitivelib.autogen.matrix;



public interface ObjectAbstractMatrix<R> extends Iterable<ObjectMatrixEntry<R>> {
	public abstract ObjectAbstractMatrix<R> like(int rows, int columns);
	
	public abstract int getNumRows();
	public abstract int getNumColumns();
	
	public abstract R get(int row, int column);
	public abstract void set(int row, int column, R value);
	
		
	public abstract String toString();
}