package edu.stanford.math.primitivelib.autogen.formal_sum;

import edu.stanford.math.primitivelib.autogen.algebraic.DoubleAbstractModule;
import edu.stanford.math.primitivelib.autogen.algebraic.DoubleAbstractRing;
import gnu.trove.TObjectDoubleIterator;




public class DoubleAlgebraicFreeModule<M> extends DoubleAbstractModule<DoubleSparseFormalSum<M>> {
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
		
	public double innerProduct(DoubleSparseFormalSum<M> a, DoubleSparseFormalSum<M> b) {
		double sum = ring.getZero();
		
				
		TObjectDoubleIterator<M> iterator = null;
		DoubleSparseFormalSum<M> other = null;
		
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
	
	public DoubleSparseFormalSum<M> add(M a, M b) {
		DoubleSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, ring.getOne(), b);
		return sum;
	}
	
	public DoubleSparseFormalSum<M> add(DoubleSparseFormalSum<M> a, M b) {
		DoubleSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, ring.getOne(), b);
		return sum;
	}
	
	public DoubleSparseFormalSum<M> subtract(M a, M b) {
		DoubleSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, ring.getNegativeOne(), b);
		return sum;
	}
	
	public DoubleSparseFormalSum<M> subtract(DoubleSparseFormalSum<M> a, M b) {
		DoubleSparseFormalSum<M> sum = this.createNewSum(a);
		this.addObject(sum, ring.getNegativeOne(), b);
		return sum;
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

	public DoubleSparseFormalSum<M> getAdditiveIdentity() {
		return new DoubleSparseFormalSum<M>();
	}

	public DoubleSparseFormalSum<M> createNewSum() {
		return new DoubleSparseFormalSum<M>();
	}

	public DoubleSparseFormalSum<M> createNewSum(M object) {
		return new DoubleSparseFormalSum<M>(this.ring.getOne(), object);
	}

	public DoubleSparseFormalSum<M> createNewSum(double coefficient, M object) {
		return new DoubleSparseFormalSum<M>(coefficient, object);
	}

	public DoubleSparseFormalSum<M> createNewSum(DoubleSparseFormalSum<M> contents) {
		return new DoubleSparseFormalSum<M>(contents);
	}
	
	public DoubleSparseFormalSum<M> createNewSum(double[] coefficients, M[] objects) {
		DoubleSparseFormalSum<M> sum = new DoubleSparseFormalSum<M>();
		
		for (int i = 0; i < coefficients.length; i++) {
			addObject(sum, coefficients[i], objects[i]);
		}
		
		return sum;
	}
	
		
	public DoubleSparseFormalSum<M> createNewSum(int[] coefficients, M[] objects) {
		DoubleSparseFormalSum<M> sum = new DoubleSparseFormalSum<M>();
		
		for (int i = 0; i < coefficients.length; i++) {
			addObject(sum, ring.valueOf(coefficients[i]), objects[i]);
		}
		
		return sum;
	}
	
	}