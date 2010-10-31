package edu.stanford.math.primitivelib.autogen.matrix;






public interface BooleanAbstractMatrix extends Iterable<BooleanMatrixEntry> {
	public abstract BooleanAbstractMatrix like(int rows, int columns);
	
	public abstract int getNumRows();
	public abstract int getNumColumns();
	
	public abstract boolean get(int row, int column);
	public abstract void set(int row, int column, boolean value);
	
		public abstract BooleanAbstractVector multiply(BooleanAbstractVector vector);
		
	public abstract String toString();
};
