package edu.stanford.math.primitivelib.autogen.matrix;



public interface LongAbstractMatrix extends Iterable<LongMatrixEntry> {
	public abstract LongAbstractMatrix like(int rows, int columns);
	
	public abstract int getNumRows();
	public abstract int getNumColumns();
	
	public abstract long get(int row, int column);
	public abstract void set(int row, int column, long value);
	
		public abstract LongAbstractVector multiply(LongAbstractVector vector);
		
	public abstract String toString();
}