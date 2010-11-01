package edu.stanford.math.primitivelib.autogen.formal_sum;


import gnu.trove.TObjectFloatHashMap;
import gnu.trove.TObjectFloatIterator;



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
 * @param <float> the coefficient type
 * @param <M> the object type 
 */
public class FloatSparseFormalSum<M> implements FloatAbstractFormalSum<M> {
	/**
	 * The coefficient-object pairs are held in a hash map, where the
	 * key is the object), and the value is the coefficient.
	 * 
	 */
	protected final TObjectFloatHashMap<M> map = new TObjectFloatHashMap<M>();
	
	/**
	 * Default constructor which initializes the sum to be empty.
	 */
	public FloatSparseFormalSum() {}
	
	/**
	 * This constructor initializes the sum to contain one object.
	 * 
	 * @param coefficient the coefficient of the initializing object
	 * @param object the object to initialize to
	 */
	public FloatSparseFormalSum(float coefficient, M object) {
		this.put(coefficient, object);
	}
	
	/**
	 * This constructor constructs the sum from another hash map.
	 * 
	 * @param map the hash map to import from
	 */
	public FloatSparseFormalSum(TObjectFloatHashMap<M> map) {
				this.map.putAll(map);
			}
	
	/**
	 * This constructor initializes the sum from another DoubleFormalSum.
	 * 
	 * @param formalSum the DoubleFormalSum to import from
	 */
	public FloatSparseFormalSum(FloatSparseFormalSum<M> formalSum) {
		this(formalSum.map);
	}
	
	public boolean containsObject(M object) {
		return this.map.containsKey(object);
	}

	public float getCoefficient(M object) {
		return this.map.get(object);
	}

	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	public void put(float coefficient, M object) {
		this.map.put(object, coefficient);
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
		for (TObjectFloatIterator<M> iterator = this.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			if (index > 0) {
				builder.append(" + ");
			}
			if (iterator.value() == -1) {
				builder.append('-');
			} else if (iterator.value() != 1) {
				builder.append(iterator.value());
			}
			builder.append(iterator.key().toString());
			index++;
		}
		return builder.toString();
	}
	
		
		
	public TObjectFloatIterator<M> iterator() {
		return this.map.iterator();
	}
	
	}