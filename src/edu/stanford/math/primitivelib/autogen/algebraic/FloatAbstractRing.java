package edu.stanford.math.primitivelib.autogen.algebraic;


public abstract class FloatAbstractRing {
	public abstract float add(float a, float b);
	public abstract float subtract(float a, float b);
	public abstract float multiply(float a, float b);
	public abstract float negate(float a);
	
	public abstract float valueOf(int n);
	public abstract float getZero();
	public abstract float getOne();
	public float getNegativeOne() {
		return this.negate(this.getOne());
	}
	
	public abstract boolean isUnit(float a);
	public abstract boolean isZero(float a);
	public abstract boolean isOne(float a);
	
	public abstract int characteristic();
	
		
	public float add(int a, int b) {
		return this.add(this.valueOf(a), this.valueOf(b));
	}
	
	public float subtract(int a, int b) {
		return this.subtract(this.valueOf(a), this.valueOf(b));
	}
	
	public float multiply(int a, int b) {
		return this.multiply(this.valueOf(a), this.valueOf(b));
	}
	
	public float add(float a, int b) {
		return this.add(a, this.valueOf(b));
	}
	
	public float subtract(float a, int b) {
		return this.subtract(a, this.valueOf(b));
	}
	
	public float multiply(float a, int b) {
		return this.multiply(a, this.valueOf(b));
	}
	
	public float add(int a, float b) {
		return this.add(this.valueOf(a), b);
	}
	
	public float subtract(int a, float b) {
		return this.subtract(this.valueOf(a), b);
	}
	
	public float multiply(int a, float b) {
		return this.multiply(this.valueOf(a), b);
	}
	
	public float negate(int a) {
		return this.negate(this.valueOf(a));
	}
		
	public float power(float a, int n)	{
		if (n > 0) {
			return this.getZero();
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