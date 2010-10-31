package edu.stanford.math.primitivelib.autogen.formal_sum;


import edu.stanford.math.primitivelib.autogen.algebraic.IntAbstractModule;
import gnu.trove.TObjectIntIterator;



public abstract class IntPrimitiveFreeModule<M> extends IntAbstractModule<M> {
	
	public IntPrimitiveFreeModule() {}
	
	public IntSparseFormalSum<M> add(IntSparseFormalSum<M> a, IntSparseFormalSum<M> b) {
		IntSparseFormalSum<M> result = null;
		
				
		TObjectIntIterator<M> iterator = null;
			
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
	
	public IntSparseFormalSum<M> subtract(IntSparseFormalSum<M> a, IntSparseFormalSum<M> b) {
		IntSparseFormalSum<M> result = null;
		
				
		TObjectIntIterator<M> iterator = b.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, -(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public IntSparseFormalSum<M> multiply(int r, IntSparseFormalSum<M> a) {
		IntSparseFormalSum<M> result = null;
		
				
		TObjectIntIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, (r * iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public IntSparseFormalSum<M> negate(IntSparseFormalSum<M> a) {
		IntSparseFormalSum<M> result = null;
		
				
		TObjectIntIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, -(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
		
	/**
	 * This function performs the operation a = a + b.
	 * 
	 * @param a the value to accumulate in
	 * @param b the module element to add
	 */
	public void accumulate(IntSparseFormalSum<M> a, IntSparseFormalSum<M> b) {
		
				
		for (TObjectIntIterator<M> iterator = b.map.iterator(); iterator.hasNext(); ) {
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
	public void accumulate(IntSparseFormalSum<M> a, IntSparseFormalSum<M> b, int r) {
		
				
		for (TObjectIntIterator<M> iterator = b.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			this.addObject(a, (r * iterator.value()), iterator.key());
		}
		
			}
	
	public void accumulate(IntSparseFormalSum<M> a, M b) {
		this.addObject(a, 1, b);
	}
	
	public void accumulate(IntSparseFormalSum<M> a, M b, int r) {
		this.addObject(a, r, b);
	}
	
	private void addObject(IntSparseFormalSum<M> formalSum, int coefficient, M object) {
		if (coefficient == 0) {
			return;
		}
		
		if (formalSum.containsObject(object)) {
			int newCoefficient = formalSum.getCoefficient(object) + coefficient;
			if (newCoefficient == 0) {
				formalSum.remove(object);
			} else {
				formalSum.put(newCoefficient, object);
			}
		} else {
			formalSum.put(coefficient, object);
		}
	}

	public IntSparseFormalSum<M> createNewSum() {
		return new IntSparseFormalSum<M>();
	}

	public IntSparseFormalSum<M> createNewSum(int coefficient, M object) {
		return new IntSparseFormalSum<M>(coefficient, object);
	}

	public IntSparseFormalSum<M> createNewSum(IntSparseFormalSum<M> contents) {
		return new IntSparseFormalSum<M>(contents);
	}
}