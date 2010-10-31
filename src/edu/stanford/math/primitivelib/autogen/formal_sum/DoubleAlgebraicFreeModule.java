package edu.stanford.math.primitivelib.autogen.formal_sum;

import edu.stanford.math.primitivelib.autogen.algebraic.DoubleAbstractModule;
import edu.stanford.math.primitivelib.autogen.algebraic.DoubleAbstractRing;
import gnu.trove.TObjectDoubleIterator;



public abstract class DoubleAlgebraicFreeModule<M> extends DoubleAbstractModule<M> {
	private DoubleAbstractRing ring;
	
	public DoubleAlgebraicFreeModule(DoubleAbstractRing ring) {
		this.ring = ring;
	}
	
	public DoubleSparseFormalSum<M> add(DoubleSparseFormalSum<M> a, DoubleSparseFormalSum<M> b) {
		DoubleSparseFormalSum<M> result = null;
		
				
		TObjectDoubleIterator<M> iterator = null;
			
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
	
	public DoubleSparseFormalSum<M> subtract(DoubleSparseFormalSum<M> a, DoubleSparseFormalSum<M> b) {
		DoubleSparseFormalSum<M> result = null;
		
				
		TObjectDoubleIterator<M> iterator = b.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, ring.negate(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public DoubleSparseFormalSum<M> multiply(double r, DoubleSparseFormalSum<M> a) {
		DoubleSparseFormalSum<M> result = null;
		
				
		TObjectDoubleIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, ring.multiply(r, iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public DoubleSparseFormalSum<M> negate(DoubleSparseFormalSum<M> a) {
		DoubleSparseFormalSum<M> result = null;
		
				
		TObjectDoubleIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, ring.negate(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
		public DoubleSparseFormalSum<M> multiply(int r, DoubleSparseFormalSum<M> a) {
		DoubleSparseFormalSum<M> result = null;
		
				
		TObjectDoubleIterator<M> iterator = a.map.iterator();
		
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
	public void accumulate(DoubleSparseFormalSum<M> a, DoubleSparseFormalSum<M> b) {
		
				
		for (TObjectDoubleIterator<M> iterator = b.map.iterator(); iterator.hasNext(); ) {
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
	public void accumulate(DoubleSparseFormalSum<M> a, DoubleSparseFormalSum<M> b, double r) {
		
				
		for (TObjectDoubleIterator<M> iterator = b.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			this.addObject(a, ring.multiply(r, iterator.value()), iterator.key());
		}
		
			}
	
	public void accumulate(DoubleSparseFormalSum<M> a, M b) {
		this.addObject(a, this.ring.getOne(), b);
	}
	
	public void accumulate(DoubleSparseFormalSum<M> a, M b, double r) {
		this.addObject(a, r, b);
	}
	
	private void addObject(DoubleSparseFormalSum<M> formalSum, double coefficient, M object) {
		if (this.ring.isZero(coefficient)) {
			return;
		}
		
		if (formalSum.containsObject(object)) {
			double newCoefficient = this.ring.add(formalSum.getCoefficient(object), coefficient);
			if (ring.isZero(newCoefficient)) {
				formalSum.remove(object);
			} else {
				formalSum.put(newCoefficient, object);
			}
		} else {
			formalSum.put(coefficient, object);
		}
	}

	public DoubleSparseFormalSum<M> createNewSum() {
		return new DoubleSparseFormalSum<M>();
	}

	public DoubleSparseFormalSum<M> createNewSum(double coefficient, M object) {
		return new DoubleSparseFormalSum<M>(coefficient, object);
	}

	public DoubleSparseFormalSum<M> createNewSum(DoubleSparseFormalSum<M> contents) {
		return new DoubleSparseFormalSum<M>(contents);
	}
}