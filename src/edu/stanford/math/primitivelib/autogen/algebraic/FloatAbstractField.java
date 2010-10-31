package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class FloatAbstractField extends FloatAbstractRing {
	public abstract float divide(float a, float b);
	public abstract float invert(float a);
	
		
	public float divide(int a, int b) {
		return this.divide(this.valueOf(a), this.valueOf(b));
	}
	
	public float invert(int a) {
		return this.invert(this.valueOf(a));
	}
	
		
	@Override
	public float power(float a, int n)	{
		if (n < 0) {
			return this.power(this.invert(a), -n);
		}
	    float result = this.getOne();
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