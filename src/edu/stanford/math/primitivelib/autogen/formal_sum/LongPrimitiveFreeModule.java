package edu.stanford.math.primitivelib.autogen.formal_sum;


import edu.stanford.math.primitivelib.autogen.algebraic.LongAbstractModule;
import gnu.trove.TObjectLongIterator;




public class LongPrimitiveFreeModule<M> extends LongAbstractModule<LongSparseFormalSum<M>> {
	
	public LongPrimitiveFreeModule() {}
	
	public LongSparseFormalSum<M> add(LongSparseFormalSum<M> a, LongSparseFormalSum<M> b) {
		LongSparseFormalSum<M> result = null;
		
				
		TObjectLongIterator<M> iterator = null;
			
		if (a.size() > b.size()) {
			result = this.createNewSum(a);
			iterator = b.map.iterator();
		} else {
			result = this.createNewSum(b);
			iterator = a.map.iterator();
		}
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, iterator.value(), iterator.key());
		}
		
				
		return result;
	}
	
	public LongSparseFormalSum<M> subtract(LongSparseFormalSum<M> a, LongSparseFormalSum<M> b) {
		LongSparseFormalSum<M> result = null;
		
				
		TObjectLongIterator<M> iterator = b.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, -(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public LongSparseFormalSum<M> multiply(long r, LongSparseFormalSum<M> a) {
		LongSparseFormalSum<M> result = null;
		
				
		TObjectLongIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, (r * iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public LongSparseFormalSum<M> negate(LongSparseFormalSum<M> a) {
		LongSparseFormalSum<M> result = null;
		
				
		TObjectLongIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, -(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
		public LongSparseFormalSum<M> multiply(int r, LongSparseFormalSum<M> a) {
		LongSparseFormalSum<M> result = null;
		
				
		TObjectLongIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, (r * iterator.value()), iterator.key());
		}
		
				
		return result;
	}
		
	public long innerProduct(LongSparseFormalSum<M> a, LongSparseFormalSum<M> b) {
		long sum = 0L;
		
				
		TObjectLongIterator<M> iterator = null;
		LongSparseFormalSum<M> other = null;
		
		if (a.size() > b.size()) {
			iterator = b.map.iterator();
			other = a;
		} else {
			iterator = a.map.iterator();
			other = b;
		}
		
		while(iterator.hasNext()) {
			iterator.advance();
			sum += iterator.value() * other.getCoefficient(iterator.key());
		}
		
				
		return sum;
	}
	
	public LongSparseFormalSum<M> add(M a, M b) {
		LongSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, 1, b);
		return sum;
	}
	
	public LongSparseFormalSum<M> add(LongSparseFormalSum<M> a, M b) {
		LongSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, 1, b);
		return sum;
	}
	
	public LongSparseFormalSum<M> subtract(M a, M b) {
		LongSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, -1, b);
		return sum;
	}
	
	public LongSparseFormalSum<M> subtract(LongSparseFormalSum<M> a, M b) {
		LongSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, -1, b);
		return sum;
	}
	
	/**
	 * This function performs the operation a = a + b.
	 * 
	 * @param a the value to accumulate in
	 * @param b the module element to add
	 */
	public void accumulate(LongSparseFormalSum<M> a, LongSparseFormalSum<M> b) {
		
				
		for (TObjectLongIterator<M> iterator = b.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			this.addObject(a, iterator.value(), iterator.key());
		}
		
			}
	
	/**
	 * This function performs the operation a = a + r * b.
	 * 
	 * @param a the value to accumulate in
	 * @param b the module element to add
	 * @param c the scalar multiplier
	 */
	public void accumulate(LongSparseFormalSum<M> a, LongSparseFormalSum<M> b, long r) {
		
				
		for (TObjectLongIterator<M> iterator = b.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			this.addObject(a, (r * iterator.value()), iterator.key());
		}
		
			}
	
	public void accumulate(LongSparseFormalSum<M> a, M b) {
		this.addObject(a, 1, b);
	}
	
	public void accumulate(LongSparseFormalSum<M> a, M b, long r) {
		this.addObject(a, r, b);
	}
	
	private void addObject(LongSparseFormalSum<M> formalSum, long coefficient, M object) {
		if (coefficient == 0) {
			return;
		}
		
		if (formalSum.containsObject(object)) {
			long newCoefficient = formalSum.getCoefficient(object) + coefficient;
			if (newCoefficient == 0) {
				formalSum.remove(object);
			} else {
				formalSum.put(newCoefficient, object);
			}
		} else {
			formalSum.put(coefficient, object);
		}
	}

	public LongSparseFormalSum<M> getAdditiveIdentity() {
		return new LongSparseFormalSum<M>();
	}

	public LongSparseFormalSum<M> createNewSum() {
		return new LongSparseFormalSum<M>();
	}
	
	public LongSparseFormalSum<M> createNewSum(M object) {
		return new LongSparseFormalSum<M>(1, object);
	}

	public LongSparseFormalSum<M> createNewSum(long coefficient, M object) {
		return new LongSparseFormalSum<M>(coefficient, object);
	}

	public LongSparseFormalSum<M> createNewSum(LongSparseFormalSum<M> contents) {
		return new LongSparseFormalSum<M>(contents);
	}
	
	public LongSparseFormalSum<M> createNewSum(long[] coefficients, M[] objects) {
		LongSparseFormalSum<M> sum = new LongSparseFormalSum<M>();
		
		for (int i = 0; i < coefficients.length; i++) {
			addObject(sum, coefficients[i], objects[i]);
		}
		
		return sum;
	}
	
		
	public LongSparseFormalSum<M> createNewSum(int[] coefficients, M[] objects) {
		LongSparseFormalSum<M> sum = new LongSparseFormalSum<M>();
		
		for (int i = 0; i < coefficients.length; i++) {
			addObject(sum, (long) coefficients[i], objects[i]);
		}
		
		return sum;
	}
	
	}