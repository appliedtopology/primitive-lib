package edu.stanford.math.primitivelib.autogen.matrix;



public interface FloatAbstractMatrix extends Iterable<FloatMatrixEntry> {
	public abstract FloatAbstractMatrix like(int rows, int columns);
	
	public abstract int getNumRows();
	public abstract int getNumColumns();
	
	public abstract float get(int row, int column);
	public abstract void set(int row, int column, float value);
	
		public abstract FloatAbstractVector multiply(FloatAbstractVector vector);
		
	public abstract String toString();
}