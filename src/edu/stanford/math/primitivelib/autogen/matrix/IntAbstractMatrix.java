package edu.stanford.math.primitivelib.autogen.matrix;



public interface IntAbstractMatrix extends Iterable<IntMatrixEntry> {
	public abstract IntAbstractMatrix like(int rows, int columns);
	
	public abstract int getNumRows();
	public abstract int getNumColumns();
	
	public abstract int get(int row, int column);
	public abstract void set(int row, int column, int value);
	
		public abstract IntAbstractVector multiply(IntAbstractVector vector);
		
	public abstract String toString();
}