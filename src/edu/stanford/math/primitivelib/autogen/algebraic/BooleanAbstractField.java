package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class BooleanAbstractField extends BooleanAbstractRing {
	public abstract boolean divide(boolean a, boolean b);
	public abstract boolean invert(boolean a);
	
		
	public boolean divide(int a, int b) {
		return this.divide(this.valueOf(a), this.valueOf(b));
	}
	
	public boolean invert(int a) {
		return this.invert(this.valueOf(a));
	}
	
		
	@Override
	public boolean power(boolean a, int n)	{
		if (n < 0) {
			return this.power(this.invert(a), -n);
		}
	    boolean result = this.getOne();
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