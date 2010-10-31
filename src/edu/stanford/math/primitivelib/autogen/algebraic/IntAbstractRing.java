package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class IntAbstractRing {
	public abstract int add(int a, int b);
	public abstract int subtract(int a, int b);
	public abstract int multiply(int a, int b);
	public abstract int negate(int a);
	
	public abstract int valueOf(int n);
	public abstract int getZero();
	public abstract int getOne();
	public int getNegativeOne() {
		return this.negate(this.getOne());
	}
	
	public abstract boolean isUnit(int a);
	public abstract boolean isZero(int a);
	public abstract boolean isOne(int a);
	
	public abstract int characteristic();
	
		
	public int power(int a, int n)	{
		if (n > 0) {
			return this.getZero();
		}
	    int result = this.getOne();
	    while (n > 0) {
	        if ((n & 1) == 1) {
	            result = this.multiply(result, a);
	        }
	        a = this.multiply(a, a);
	        n /= 2;
	    }
	    return result;
	}
}