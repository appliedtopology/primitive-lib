package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class LongAbstractField extends LongAbstractRing {
	public abstract long divide(long a, long b);
	public abstract long invert(long a);
	
		
	public long divide(int a, int b) {
		return this.divide(this.valueOf(a), this.valueOf(b));
	}
	
	public long invert(int a) {
		return this.invert(this.valueOf(a));
	}
	
		
	@Override
	public long power(long a, int n)	{
		if (n < 0) {
			return this.power(this.invert(a), -n);
		}
	    long result = this.getOne();
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