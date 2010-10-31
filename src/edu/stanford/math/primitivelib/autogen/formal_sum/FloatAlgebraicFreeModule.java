package edu.stanford.math.primitivelib.autogen.formal_sum;

import edu.stanford.math.primitivelib.autogen.algebraic.FloatAbstractModule;
import edu.stanford.math.primitivelib.autogen.algebraic.FloatAbstractRing;
import gnu.trove.TObjectFloatIterator;



public abstract class FloatAlgebraicFreeModule<M> extends FloatAbstractModule<M> {
	private FloatAbstractRing ring;
	
	public FloatAlgebraicFreeModule(FloatAbstractRing ring) {
		this.ring = ring;
	}
	
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
			addObject(result, ring.negate(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public FloatSparseFormalSum<M> multiply(float r, FloatSparseFormalSum<M> a) {
		FloatSparseFormalSum<M> result = null;
		
				
		TObjectFloatIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, ring.multiply(r, iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public FloatSparseFormalSum<M> negate(FloatSparseFormalSum<M> a) {
		FloatSparseFormalSum<M> result = null;
		
				
		TObjectFloatIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, ring.negate(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
		public FloatSparseFormalSum<M> multiply(int r, FloatSparseFormalSum<M> a) {
		FloatSparseFormalSum<M> result = null;
		
				
		TObjectFloatIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, ring.multiply(r, iterator.value()), iterator.key());
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
			this.addObject(a, ring.multiply(r, iterator.value()), iterator.key());
		}
		
			}
	
	public void accumulate(FloatSparseFormalSum<M> a, M b) {
		this.addObject(a, this.ring.getOne(), b);
	}
	
	public void accumulate(FloatSparseFormalSum<M> a, M b, float r) {
		this.addObject(a, r, b);
	}
	
	private void addObject(FloatSparseFormalSum<M> formalSum, float coefficient, M object) {
		if (this.ring.isZero(coefficient)) {
			return;
		}
		
		if (formalSum.containsObject(object)) {
			float newCoefficient = this.ring.add(formalSum.getCoefficient(object), coefficient);
			if (ring.isZero(newCoefficient)) {
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