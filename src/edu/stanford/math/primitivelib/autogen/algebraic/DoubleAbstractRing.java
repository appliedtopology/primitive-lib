package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class DoubleAbstractRing {
	public abstract double add(double a, double b);
	public abstract double subtract(double a, double b);
	public abstract double multiply(double a, double b);
	public abstract double negate(double a);
	
	public abstract double valueOf(int n);
	public abstract double getZero();
	public abstract double getOne();
	public double getNegativeOne() {
		return this.negate(this.getOne());
	}
	
	public abstract boolean isUnit(double a);
	public abstract boolean isZero(double a);
	public abstract boolean isOne(double a);
	
	public abstract int characteristic();
	
		
	public double add(int a, int b) {
		return this.add(this.valueOf(a), this.valueOf(b));
	}
	
	public double subtract(int a, int b) {
		return this.subtract(this.valueOf(a), this.valueOf(b));
	}
	
	public double multiply(int a, int b) {
		return this.multiply(this.valueOf(a), this.valueOf(b));
	}
	
	public double add(double a, int b) {
		return this.add(a, this.valueOf(b));
	}
	
	public double subtract(double a, int b) {
		return this.subtract(a, this.valueOf(b));
	}
	
	public double multiply(double a, int b) {
		return this.multiply(a, this.valueOf(b));
	}
	
	public double add(int a, double b) {
		return this.add(this.valueOf(a), b);
	}
	
	public double subtract(int a, double b) {
		return this.subtract(this.valueOf(a), b);
	}
	
	public double multiply(int a, double b) {
		return this.multiply(this.valueOf(a), b);
	}
	
	public double negate(int a) {
		return this.negate(this.valueOf(a));
	}
		
	public double power(double a, int n)	{
		if (n > 0) {
			return this.getZero();
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