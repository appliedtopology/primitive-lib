package edu.stanford.math.primitivelib.autogen.matrix;



public interface DoubleAbstractMatrix extends Iterable<DoubleMatrixEntry> {
	public abstract DoubleAbstractMatrix like(int rows, int columns);
	
	public abstract int getNumRows();
	public abstract int getNumColumns();
	
	public abstract double get(int row, int column);
	public abstract void set(int row, int column, double value);
	
		public abstract DoubleAbstractVector multiply(DoubleAbstractVector vector);
		
	public abstract String toString();
}