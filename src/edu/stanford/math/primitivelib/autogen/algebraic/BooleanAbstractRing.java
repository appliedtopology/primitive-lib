package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class BooleanAbstractRing {
	public abstract boolean add(boolean a, boolean b);
	public abstract boolean subtract(boolean a, boolean b);
	public abstract boolean multiply(boolean a, boolean b);
	public abstract boolean negate(boolean a);
	
	public abstract boolean valueOf(int n);
	public abstract boolean getZero();
	public abstract boolean getOne();
	public boolean getNegativeOne() {
		return this.negate(this.getOne());
	}
	
	public abstract boolean isUnit(boolean a);
	public abstract boolean isZero(boolean a);
	public abstract boolean isOne(boolean a);
	
	public abstract int characteristic();
	
		
	public boolean add(int a, int b) {
		return this.add(this.valueOf(a), this.valueOf(b));
	}
	
	public boolean subtract(int a, int b) {
		return this.subtract(this.valueOf(a), this.valueOf(b));
	}
	
	public boolean multiply(int a, int b) {
		return this.multiply(this.valueOf(a), this.valueOf(b));
	}
	
	public boolean add(boolean a, int b) {
		return this.add(a, this.valueOf(b));
	}
	
	public boolean subtract(boolean a, int b) {
		return this.subtract(a, this.valueOf(b));
	}
	
	public boolean multiply(boolean a, int b) {
		return this.multiply(a, this.valueOf(b));
	}
	
	public boolean add(int a, boolean b) {
		return this.add(this.valueOf(a), b);
	}
	
	public boolean subtract(int a, boolean b) {
		return this.subtract(this.valueOf(a), b);
	}
	
	public boolean multiply(int a, boolean b) {
		return this.multiply(this.valueOf(a), b);
	}
	
	public boolean negate(int a) {
		return this.negate(this.valueOf(a));
	}
		
	public boolean power(boolean a, int n)	{
		if (n > 0) {
			return this.getZero();
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