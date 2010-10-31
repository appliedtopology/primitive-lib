package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class LongAbstractRing {
	public abstract long add(long a, long b);
	public abstract long subtract(long a, long b);
	public abstract long multiply(long a, long b);
	public abstract long negate(long a);
	
	public abstract long valueOf(int n);
	public abstract long getZero();
	public abstract long getOne();
	public long getNegativeOne() {
		return this.negate(this.getOne());
	}
	
	public abstract boolean isUnit(long a);
	public abstract boolean isZero(long a);
	public abstract boolean isOne(long a);
	
	public abstract int characteristic();
	
		
	public long add(int a, int b) {
		return this.add(this.valueOf(a), this.valueOf(b));
	}
	
	public long subtract(int a, int b) {
		return this.subtract(this.valueOf(a), this.valueOf(b));
	}
	
	public long multiply(int a, int b) {
		return this.multiply(this.valueOf(a), this.valueOf(b));
	}
	
	public long add(long a, int b) {
		return this.add(a, this.valueOf(b));
	}
	
	public long subtract(long a, int b) {
		return this.subtract(a, this.valueOf(b));
	}
	
	public long multiply(long a, int b) {
		return this.multiply(a, this.valueOf(b));
	}
	
	public long add(int a, long b) {
		return this.add(this.valueOf(a), b);
	}
	
	public long subtract(int a, long b) {
		return this.subtract(this.valueOf(a), b);
	}
	
	public long multiply(int a, long b) {
		return this.multiply(this.valueOf(a), b);
	}
	
	public long negate(int a) {
		return this.negate(this.valueOf(a));
	}
		
	public long power(long a, int n)	{
		if (n > 0) {
			return this.getZero();
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