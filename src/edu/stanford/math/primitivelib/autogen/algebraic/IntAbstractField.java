package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class IntAbstractField extends IntAbstractRing {
	public abstract int divide(int a, int b);
	public abstract int invert(int a);
	
		
	@Override
	public int power(int a, int n)	{
		if (n < 0) {
			return this.power(this.invert(a), -n);
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