package edu.stanford.math.primitivelib.autogen.algebraic;



/**
 * This interface defines a (left) R-module structure on
 * a generic data type.
 * 
 * @author Andrew Tausz
 *
 * @param <long> ring of scalars
 * @param <M> the underlying data type
 */
public abstract class LongAbstractModule<M> {
	public abstract M add(M a, M b);
	public abstract M subtract(M a, M b);
	public abstract M multiply(long r, M a);
	public abstract M negate(M a);
	public abstract M getAdditiveIdentity();
	
		public abstract M multiply(int r, M a);
		
	/**
	 * This function performs the operation a = a + b.
	 * 
	 * @param a the value to accumulate in
	 * @param b the module element to add
	 */
	public abstract void accumulate(M a, M b);
	
	/**
	 * This function performs the operation a = a + r * b.
	 * 
	 * @param a the value to accumulate in
	 * @param b the module element to add
	 * @param c the scalar multiplier
	 */
	public abstract void accumulate(M a, M b, long r);
}