package edu.stanford.math.primitivelib.autogen.formal_sum;



import java.util.Iterator;

import edu.stanford.math.primitivelib.autogen.algebraic.BooleanAbstractModule;


public abstract class BooleanPrimitiveFreeModule<M> extends BooleanAbstractModule<BooleanSparseFormalSum<M>> {

	public BooleanPrimitiveFreeModule() {}

	public BooleanSparseFormalSum<M> add(BooleanSparseFormalSum<M> a, BooleanSparseFormalSum<M> b) {
		BooleanSparseFormalSum<M> result = null;

		Iterator<M> iterator = null;

		if (a.size() > b.size()) {
			result = this.createNewSum(a);
			iterator = b.map.iterator();
		} else {
			result = this.createNewSum(b);
			iterator = a.map.iterator();
		}

		while (iterator.hasNext()) {
			addObject(result, iterator.next());
		}

		return result;
	}

	public BooleanSparseFormalSum<M> subtract(BooleanSparseFormalSum<M> a, BooleanSparseFormalSum<M> b) {
		return add(a, b);
	}

	public BooleanSparseFormalSum<M> multiply(boolean r, BooleanSparseFormalSum<M> a) {
		if (r) {
			return a;
		} else {
			return this.createNewSum();
		}
	}

	public BooleanSparseFormalSum<M> negate(BooleanSparseFormalSum<M> a) {
		return a;
	}

	public BooleanSparseFormalSum<M> multiply(int r, BooleanSparseFormalSum<M> a) {
		if (r % 2 != 0) {
			return a;
		} else {
			return this.createNewSum();
		}
	}

	/**
	 * This function performs the operation a = a + b.
	 * 
	 * @param a the value to accumulate in
	 * @param b the module element to add
	 */
	public void accumulate(BooleanSparseFormalSum<M> a, BooleanSparseFormalSum<M> b) {
		for (M element: b.map) {
			addObject(a, element);
		}
	}

	/**
	 * This function performs the operation a = a + r * b.
	 * 
	 * @param a the value to accumulate in
	 * @param b the module element to add
	 * @param c the scalar multiplier
	 */
	public void accumulate(BooleanSparseFormalSum<M> a, BooleanSparseFormalSum<M> b, boolean r) {
		if (r) {
			for (M element: b.map) {
				addObject(a, element);
			}
		}
	}

	public void accumulate(BooleanSparseFormalSum<M> a, M b) {
		this.addObject(a, b);
	}

	public void accumulate(BooleanSparseFormalSum<M> a, M b, boolean r) {
		if (r) {
			this.addObject(a, b);
		}
	}

	private void addObject(BooleanSparseFormalSum<M> formalSum, M object) {
		if (formalSum.containsObject(object)) {
			formalSum.remove(object);
		} else {
			formalSum.put(true, object);
		}
	}

	public BooleanSparseFormalSum<M> createNewSum() {
		return new BooleanSparseFormalSum<M>();
	}

	public BooleanSparseFormalSum<M> createNewSum(boolean coefficient, M object) {
		return new BooleanSparseFormalSum<M>(coefficient, object);
	}

	public BooleanSparseFormalSum<M> createNewSum(BooleanSparseFormalSum<M> contents) {
		return new BooleanSparseFormalSum<M>(contents);
	}
};
