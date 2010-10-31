package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class ObjectAbstractField<R> extends ObjectAbstractRing<R> {
	public abstract R divide(R a, R b);
	public abstract R invert(R a);
	
		
	public R divide(int a, int b) {
		return this.divide(this.valueOf(a), this.valueOf(b));
	}
	
	public R invert(int a) {
		return this.invert(this.valueOf(a));
	}
	
		
	@Override
	public R power(R a, int n)	{
		if (n < 0) {
			return this.power(this.invert(a), -n);
		}
	    R result = this.getOne();
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