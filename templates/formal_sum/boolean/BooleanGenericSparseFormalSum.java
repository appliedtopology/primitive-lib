
import java.util.Iterator;

import gnu.trove.THashSet;



/**
 * This class is a data structure for holding a formal sum.
 * Such an element can be thought of as being in the form
 * r_1 m_1 + ... + r_k m_k. 
 *
 * Note that the class GenericFormalSum is "unaware" of the 
 * arithmetic of the coefficient type. The arithmetic operations
 * of the free R-module whose elements are formal sums is 
 * implemented in GenericFreeModule<R, M>.
 * 
 * @author Andrew Tausz
 *
 * @param <boolean> the coefficient type
 * @param <M> the object type 
 */
public class BooleanGenericSparseFormalSum<M> implements BooleanGenericAbstractFormalSum<M> {
	/**
	 * The coefficient-object pairs are held in a hash map, where the
	 * key is the object), and the value is the coefficient.
	 * 
	 */
	protected final THashSet<M> map = new THashSet<M>();

	/**
	 * Default constructor which initializes the sum to be empty.
	 */
	protected BooleanGenericSparseFormalSum() {}

	/**
	 * This constructor initializes the sum to contain one object.
	 * 
	 * @param coefficient the coefficient of the initializing object
	 * @param object the object to initialize to
	 */
	protected BooleanGenericSparseFormalSum(boolean coefficient, M object) {
		this.put(coefficient, object);
	}

	/**
	 * This constructor constructs the sum from another hash map.
	 * 
	 * @param map the hash map to import from
	 */
	protected BooleanGenericSparseFormalSum(THashSet<M> map) {
		this.map.addAll(map);
	}

	/**
	 * This constructor initializes the sum from another DoubleFormalSum.
	 * 
	 * @param formalSum the DoubleFormalSum to import from
	 */
	protected BooleanGenericSparseFormalSum(BooleanGenericSparseFormalSum<M> formalSum) {
		this(formalSum.map);
	}

	public boolean containsObject(M object) {
		return this.map.contains(object);
	}

	public boolean getCoefficient(M object) {
		return this.map.contains(object);
	}

	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	public void put(boolean coefficient, M object) {
		if (coefficient) {
			this.map.add(object);
		}
	}

	public void remove(M object) {
		this.map.remove(object);
	}

	public int size() {
		return this.map.size();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		for (Iterator<M> iterator = this.map.iterator(); iterator.hasNext(); ) {
			if (index > 0) {
				builder.append(" + ");
			}
			builder.append(iterator.next().toString());
			index++;
		}
		return builder.toString();
	}

}