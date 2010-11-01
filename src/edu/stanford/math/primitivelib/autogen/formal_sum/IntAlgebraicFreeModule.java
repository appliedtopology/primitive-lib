package edu.stanford.math.primitivelib.autogen.formal_sum;

import edu.stanford.math.primitivelib.autogen.algebraic.IntAbstractModule;
import edu.stanford.math.primitivelib.autogen.algebraic.IntAbstractRing;
import gnu.trove.TObjectIntIterator;




public class IntAlgebraicFreeModule<M> extends IntAbstractModule<IntSparseFormalSum<M>> {
	private IntAbstractRing ring;
	
	public IntAlgebraicFreeModule(IntAbstractRing ring) {
		this.ring = ring;
	}
	
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
			addObject(result, ring.negate(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public IntSparseFormalSum<M> multiply(int r, IntSparseFormalSum<M> a) {
		IntSparseFormalSum<M> result = null;
		
				
		TObjectIntIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, ring.multiply(r, iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
	public IntSparseFormalSum<M> negate(IntSparseFormalSum<M> a) {
		IntSparseFormalSum<M> result = null;
		
				
		TObjectIntIterator<M> iterator = a.map.iterator();
		
		while(iterator.hasNext()) {
			iterator.advance();
			addObject(result, ring.negate(iterator.value()), iterator.key());
		}
		
				
		return result;
	}
	
		
	public int innerProduct(IntSparseFormalSum<M> a, IntSparseFormalSum<M> b) {
		int sum = ring.getZero();
		
				
		TObjectIntIterator<M> iterator = null;
		IntSparseFormalSum<M> other = null;
		
		if (a.size() > b.size()) {
			iterator = b.map.iterator();
			other = a;
		} else {
			iterator = a.map.iterator();
			other = b;
		}
		
		while(iterator.hasNext()) {
			iterator.advance();
			sum = ring.add(sum, ring.multiply(iterator.value(), other.getCoefficient(iterator.key())));
		}
		
				
		return sum;
	}
	
	public IntSparseFormalSum<M> add(M a, M b) {
		IntSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, ring.getOne(), b);
		return sum;
	}
	
	public IntSparseFormalSum<M> add(IntSparseFormalSum<M> a, M b) {
		IntSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, ring.getOne(), b);
		return sum;
	}
	
	public IntSparseFormalSum<M> subtract(M a, M b) {
		IntSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, ring.getNegativeOne(), b);
		return sum;
	}
	
	public IntSparseFormalSum<M> subtract(IntSparseFormalSum<M> a, M b) {
		IntSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, ring.getNegativeOne(), b);
		return sum;
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
			this.addObject(a, ring.multiply(r, iterator.value()), iterator.key());
		}
		
			}
	
	public void accumulate(IntSparseFormalSum<M> a, M b) {
		this.addObject(a, this.ring.getOne(), b);
	}
	
	public void accumulate(IntSparseFormalSum<M> a, M b, int r) {
		this.addObject(a, r, b);
	}
	
	private void addObject(IntSparseFormalSum<M> formalSum, int coefficient, M object) {
		if (this.ring.isZero(coefficient)) {
			return;
		}
		
		if (formalSum.containsObject(object)) {
			int newCoefficient = this.ring.add(formalSum.getCoefficient(object), coefficient);
			if (ring.isZero(newCoefficient)) {
				formalSum.remove(object);
			} else {
				formalSum.put(newCoefficient, object);
			}
		} else {
			formalSum.put(coefficient, object);
		}
	}

	public IntSparseFormalSum<M> getAdditiveIdentity() {
		return new IntSparseFormalSum<M>();
	}

	public IntSparseFormalSum<M> createNewSum() {
		return new IntSparseFormalSum<M>();
	}

	public IntSparseFormalSum<M> createNewSum(M object) {
		return new IntSparseFormalSum<M>(this.ring.getOne(), object);
	}

	public IntSparseFormalSum<M> createNewSum(int coefficient, M object) {
		return new IntSparseFormalSum<M>(coefficient, object);
	}

	public IntSparseFormalSum<M> createNewSum(IntSparseFormalSum<M> contents) {
		return new IntSparseFormalSum<M>(contents);
	}
	
	public IntSparseFormalSum<M> createNewSum(int[] coefficients, M[] objects) {
		IntSparseFormalSum<M> sum = new IntSparseFormalSum<M>();
		
		for (int i = 0; i < coefficients.length; i++) {
			addObject(sum, coefficients[i], objects[i]);
		}
		
		return sum;
	}
	
	}