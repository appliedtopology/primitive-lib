package edu.stanford.math.primitivelib.autogen.formal_sum;


import edu.stanford.math.primitivelib.autogen.algebraic.FloatAbstractModule;
import gnu.trove.TObjectFloatIterator;



public abstract class FloatPrimitiveFreeModule<M> extends FloatAbstractModule<M> {
	
	public FloatPrimitiveFreeModule() {}
	
	public FloatSparseFormalSum<M> add(FloatSparseFormalSum<M> a, FloatSparseFormalSum<M> b) {
		FloatSparseFormalSum<M> result = null;
		
				
		TObjectFloatIterator<M> iterator = null;
			
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
	
	public FloatSparseFormalSum<M> subtract(FloatSparseFormalSum<M> a, FloatSparseFormalSum<M> b) {
		FloatSparseFormalSum<M> result = null;
		
				
		TObjectFloatIterator<M> iterator = b.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, -(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public FloatSparseFormalSum<M> multiply(float r, FloatSparseFormalSum<M> a) {
		FloatSparseFormalSum<M> result = null;
		
				
		TObjectFloatIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, (r * iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public FloatSparseFormalSum<M> negate(FloatSparseFormalSum<M> a) {
		FloatSparseFormalSum<M> result = null;
		
				
		TObjectFloatIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, -(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
		public FloatSparseFormalSum<M> multiply(int r, FloatSparseFormalSum<M> a) {
		FloatSparseFormalSum<M> result = null;
		
				
		TObjectFloatIterator<M> iterator = a.map.iterator();
		
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
	public void accumulate(FloatSparseFormalSum<M> a, FloatSparseFormalSum<M> b) {
		
				
		for (TObjectFloatIterator<M> iterator = b.map.iterator(); iterator.hasNext(); ) {
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
	public void accumulate(FloatSparseFormalSum<M> a, FloatSparseFormalSum<M> b, float r) {
		
				
		for (TObjectFloatIterator<M> iterator = b.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			this.addObject(a, (r * iterator.value()), iterator.key());
		}
		
			}
	
	public void accumulate(FloatSparseFormalSum<M> a, M b) {
		this.addObject(a, 1, b);
	}
	
	public void accumulate(FloatSparseFormalSum<M> a, M b, float r) {
		this.addObject(a, r, b);
	}
	
	private void addObject(FloatSparseFormalSum<M> formalSum, float coefficient, M object) {
		if (coefficient == 0) {
			return;
		}
		
		if (formalSum.containsObject(object)) {
			float newCoefficient = formalSum.getCoefficient(object) + coefficient;
			if (newCoefficient == 0) {
				formalSum.remove(object);
			} else {
				formalSum.put(newCoefficient, object);
			}
		} else {
			formalSum.put(coefficient, object);
		}
	}

	public FloatSparseFormalSum<M> createNewSum() {
		return new FloatSparseFormalSum<M>();
	}

	public FloatSparseFormalSum<M> createNewSum(float coefficient, M object) {
		return new FloatSparseFormalSum<M>(coefficient, object);
	}

	public FloatSparseFormalSum<M> createNewSum(FloatSparseFormalSum<M> contents) {
		return new FloatSparseFormalSum<M>(contents);
	}
}