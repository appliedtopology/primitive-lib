package edu.stanford.math.primitivelib.autogen.formal_sum;

import java.util.Iterator;
import java.util.Map;

import edu.stanford.math.primitivelib.autogen.algebraic.ObjectAbstractModule;
import edu.stanford.math.primitivelib.autogen.algebraic.ObjectAbstractRing;



public abstract class ObjectAlgebraicFreeModule<R, M> extends ObjectAbstractModule<R, M> {
	private ObjectAbstractRing<R> ring;
	
	public ObjectAlgebraicFreeModule(ObjectAbstractRing<R> ring) {
		this.ring = ring;
	}
	
	public ObjectSparseFormalSum<R, M> add(ObjectSparseFormalSum<R, M> a, ObjectSparseFormalSum<R, M> b) {
		ObjectSparseFormalSum<R, M> result = null;
		
				
		Iterator<Map.Entry<M, R>> iterator = null;
		
		if (a.size() > b.size()) {
			result = this.createNewSum(a);
			iterator = b.map.entrySet().iterator();
		} else {
			result = this.createNewSum(b);
			iterator = a.map.entrySet().iterator();
		}
		
		while (iterator.hasNext()) {
			Map.Entry<M, R> entry = iterator.next();
			addObject(result, entry.getValue(), entry.getKey());
		}
		
				
		return result;
	}
	
	public ObjectSparseFormalSum<R, M> subtract(ObjectSparseFormalSum<R, M> a, ObjectSparseFormalSum<R, M> b) {
		ObjectSparseFormalSum<R, M> result = null;
		
				
		Iterator<Map.Entry<M, R>> iterator = b.map.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<M, R> entry = iterator.next();
			addObject(result, ring.negate(entry.getValue()), entry.getKey());
		}
		
				
		return result;
	}
	
	public ObjectSparseFormalSum<R, M> multiply(R r, ObjectSparseFormalSum<R, M> a) {
		ObjectSparseFormalSum<R, M> result = null;
		
				
		Iterator<Map.Entry<M, R>> iterator = a.map.entrySet().iterator();;
		
		while (iterator.hasNext()) {
			Map.Entry<M, R> entry = iterator.next();
			addObject(result, ring.multiply(r, entry.getValue()), entry.getKey());
		}
		
				
		return result;
	}
	
	public ObjectSparseFormalSum<R, M> negate(ObjectSparseFormalSum<R, M> a) {
		ObjectSparseFormalSum<R, M> result = null;
		
				
		Iterator<Map.Entry<M, R>> iterator = a.map.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<M, R> entry = iterator.next();
			addObject(result, ring.negate(entry.getValue()), entry.getKey());
		}
		
				
		return result;
	}
	
		public ObjectSparseFormalSum<R, M> multiply(int r, ObjectSparseFormalSum<R, M> a) {
		ObjectSparseFormalSum<R, M> result = null;
		
				
		Iterator<Map.Entry<M, R>> iterator = a.map.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Map.Entry<M, R> entry = iterator.next();
			addObject(result, ring.multiply(r, entry.getValue()), entry.getKey());
		}
		
				
		return result;
	}
		
	/**
	 * This function performs the operation a = a + b.
	 * 
	 * @param a the value to accumulate in
	 * @param b the module element to add
	 */
	public void accumulate(ObjectSparseFormalSum<R, M> a, ObjectSparseFormalSum<R, M> b) {
		
				
		for (Map.Entry<M, R> entry: b.map.entrySet()) {
			this.addObject(a, entry.getValue(), entry.getKey());
		}
		
			}
	
	/**
	 * This function performs the operation a = a + r * b.
	 * 
	 * @param a the value to accumulate in
	 * @param b the module element to add
	 * @param c the scalar multiplier
	 */
	public void accumulate(ObjectSparseFormalSum<R, M> a, ObjectSparseFormalSum<R, M> b, R r) {
		
				
		for (Map.Entry<M, R> entry: b.map.entrySet()) {
			this.addObject(a, ring.multiply(r, entry.getValue()), entry.getKey());
		}
		
			}
	
	public void accumulate(ObjectSparseFormalSum<R, M> a, M b) {
		this.addObject(a, this.ring.getOne(), b);
	}
	
	public void accumulate(ObjectSparseFormalSum<R, M> a, M b, R r) {
		this.addObject(a, r, b);
	}
	
	private void addObject(ObjectSparseFormalSum<R, M> formalSum, R coefficient, M object) {
		if (this.ring.isZero(coefficient)) {
			return;
		}
		
		if (formalSum.containsObject(object)) {
			R newCoefficient = this.ring.add(formalSum.getCoefficient(object), coefficient);
			if (ring.isZero(newCoefficient)) {
				formalSum.remove(object);
			} else {
				formalSum.put(newCoefficient, object);
			}
		} else {
			formalSum.put(coefficient, object);
		}
	}

	public ObjectSparseFormalSum<R, M> createNewSum() {
		return new ObjectSparseFormalSum<R, M>();
	}

	public ObjectSparseFormalSum<R, M> createNewSum(R coefficient, M object) {
		return new ObjectSparseFormalSum<R, M>(coefficient, object);
	}

	public ObjectSparseFormalSum<R, M> createNewSum(ObjectSparseFormalSum<R, M> contents) {
		return new ObjectSparseFormalSum<R, M>(contents);
	}
}