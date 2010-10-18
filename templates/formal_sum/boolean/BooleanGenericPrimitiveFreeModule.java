
import java.util.Iterator;

import edu.stanford.math.plexlib.autogen.algebraic.BooleanGenericAbstractModule;


public abstract class BooleanGenericPrimitiveFreeModule<M> extends BooleanGenericAbstractModule<BooleanGenericSparseFormalSum<M>> {

	public BooleanGenericPrimitiveFreeModule() {}

	public BooleanGenericSparseFormalSum<M> add(BooleanGenericSparseFormalSum<M> a, BooleanGenericSparseFormalSum<M> b) {
		BooleanGenericSparseFormalSum<M> result = null;

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

	public BooleanGenericSparseFormalSum<M> subtract(BooleanGenericSparseFormalSum<M> a, BooleanGenericSparseFormalSum<M> b) {
		return add(a, b);
	}

	public BooleanGenericSparseFormalSum<M> multiply(boolean r, BooleanGenericSparseFormalSum<M> a) {
		if (r) {
			return a;
		} else {
			return this.createNewSum();
		}
	}

	public BooleanGenericSparseFormalSum<M> negate(BooleanGenericSparseFormalSum<M> a) {
		return a;
	}

	public BooleanGenericSparseFormalSum<M> multiply(int r, BooleanGenericSparseFormalSum<M> a) {
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
	public void accumulate(BooleanGenericSparseFormalSum<M> a, BooleanGenericSparseFormalSum<M> b) {
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
	public void accumulate(BooleanGenericSparseFormalSum<M> a, BooleanGenericSparseFormalSum<M> b, boolean r) {
		if (r) {
			for (M element: b.map) {
				addObject(a, element);
			}
		}
	}

	public void accumulate(BooleanGenericSparseFormalSum<M> a, M b) {
		this.addObject(a, b);
	}

	public void accumulate(BooleanGenericSparseFormalSum<M> a, M b, boolean r) {
		if (r) {
			this.addObject(a, b);
		}
	}

	private void addObject(BooleanGenericSparseFormalSum<M> formalSum, M object) {
		if (formalSum.containsObject(object)) {
			formalSum.remove(object);
		} else {
			formalSum.put(true, object);
		}
	}

	public BooleanGenericSparseFormalSum<M> createNewSum() {
		return new BooleanGenericSparseFormalSum<M>();
	}

	public BooleanGenericSparseFormalSum<M> createNewSum(boolean coefficient, M object) {
		return new BooleanGenericSparseFormalSum<M>(coefficient, object);
	}

	public BooleanGenericSparseFormalSum<M> createNewSum(BooleanGenericSparseFormalSum<M> contents) {
		return new BooleanGenericSparseFormalSum<M>(contents);
	}
}