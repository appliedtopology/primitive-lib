package edu.stanford.math.primitivelib.autogen.formal_sum;


import edu.stanford.math.primitivelib.autogen.algebraic.LongAbstractModule;
import gnu.trove.TObjectLongIterator;



public abstract class LongPrimitiveFreeModule<M> extends LongAbstractModule<M> {
	
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

	public LongSparseFormalSum<M> createNewSum() {
		return new LongSparseFormalSum<M>();
	}

	public LongSparseFormalSum<M> createNewSum(long coefficient, M object) {
		return new LongSparseFormalSum<M>(coefficient, object);
	}

	public LongSparseFormalSum<M> createNewSum(LongSparseFormalSum<M> contents) {
		return new LongSparseFormalSum<M>(contents);
	}
}