package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class DoubleAbstractField extends DoubleAbstractRing {
	public abstract double divide(double a, double b);
	public abstract double invert(double a);
	
		
	public double divide(int a, int b) {
		return this.divide(this.valueOf(a), this.valueOf(b));
	}
	
	public double invert(int a) {
		return this.invert(this.valueOf(a));
	}
	
		
	@Override
	public double power(double a, int n)	{
		if (n < 0) {
			return this.power(this.invert(a), -n);
		}
	    double result = this.getOne();
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