
import edu.stanford.math.plexlib.autogen.matrix.BooleanSparseVector;
import edu.stanford.math.plexlib.autogen.matrix.BooleanVectorEntry;
import edu.stanford.math.plexlib.autogen.pair.IntBooleanUnorderedPair;
import gnu.trove.TIntObjectHashMap;
import gnu.trove.TObjectIntHashMap;

import java.util.Iterator;


/**
 * This class implements the isomorphism between a free module on a set of
 * finite generators, and the module R^n, where R is a commutative ring.
 * In this case, the set of free generators is given by an Iterable stream
 * of values of type M. This class contains the necessary functionality for
 * converting between these two formats: formal sums over a generating set
 * and arrays of elements in R. Thus basis elements correspond to standard
 * unit vectors.
 * 
 * @author Andrew Tausz
 *
 * @param <boolean> the type of the underlying ring
 * @param <M> the type of the set of free generators
 */
public class BooleanVectorConverter<M> {
	/**
	 * This is the iterable collection of elements which constitute the free basis
	 * of the module.
	 */
	protected final Iterable<M> stream;

	/**
	 * This maps a basis element to an index. The index is the appropriate
	 * index of the standard basis vector the generating element is mapped to.
	 */
	protected final TObjectIntHashMap<M> indexMapping = new TObjectIntHashMap<M>();

	/**
	 * This maps an index (ie. the index of a standard basis vector) to a generating
	 * element of the free module.
	 */
	protected final TIntObjectHashMap<M> basisMapping = new TIntObjectHashMap<M>();

	/**
	 * This stores the cardinality of the generating set of the module.
	 */
	protected final int dimension;

	/**
	 * This constructor initializes the object with an Iterable stream of the 
	 * free generators of the module.
	 * 
	 * @param stream an iterable collection of free generators
	 */
	public BooleanVectorConverter(Iterable<M> stream) {
		this.stream = stream;
		this.initializeMappings();
		this.dimension = this.indexMapping.size();
	}

	/**
	 * This function initializes the basis-index mappings.
	 */
	private void initializeMappings() {
		int index = 0;
		for (M basisElement: this.stream) {
			this.indexMapping.put(basisElement, index);
			this.basisMapping.put(index, basisElement);
			index++;
		}
	}

	/**
	 * This function returns the cardinality of the generating set of the free
	 * module.
	 * 
	 * @return the dimension of the free module
	 */
	public int getDimension() {
		return this.dimension;
	}

	/**
	 * This returns the index of a basis element. The index is defined to be the
	 * index of the standard basis vector that the element maps to.
	 * 
	 * @param basisElement the basis element to query
	 * @return the index of the basis element
	 */
	public int getIndex(M basisElement) {
		return this.indexMapping.get(basisElement);
	}

	/**
	 * This returns the basis element corresponding to the supplied index.
	 * 
	 * @param index the index to query
	 * @return the basis element with the supplied index
	 */
	public M getBasisElement(int index) {
		return this.basisMapping.get(index);
	}


	public boolean[] toArray(BooleanSparseFormalSum<M> formalSum) {
		boolean[] array = new boolean[this.getDimension()];

		for (Iterator<M> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			int index = this.getIndex(iterator.next());
			array[index] = true;
		}

		return array;
	}

	public boolean[] toArray(BooleanSparseVector sparseVector) {
		return sparseVector.toArray();
	}


	public BooleanSparseVector toSparseVector(BooleanSparseFormalSum<M> formalSum) {
		BooleanSparseVector vector = new BooleanSparseVector(this.getDimension());

		for (Iterator<M> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			int index = this.getIndex(iterator.next());
			vector.set(index, true);
		}

		return vector;
	}


	public BooleanSparseVector toSparseVector(boolean[] array) {
		return new BooleanSparseVector(array);
	}

	public BooleanSparseFormalSum<M> toFormalSum(boolean[] array) {
		BooleanSparseFormalSum<M> sum = new BooleanSparseFormalSum<M>();

		for (int i = 0; i < array.length; i++) {
			if (array[i]) {
				sum.put(array[i], this.getBasisElement(i));
			}
		}

		return sum;
	}

	public BooleanSparseFormalSum<M> toFormalSum(BooleanSparseVector sparseVector) {
		BooleanSparseFormalSum<M> sum = new BooleanSparseFormalSum<M>();

		for (BooleanVectorEntry pair: sparseVector) {
			sum.put(pair.getValue(), this.getBasisElement(pair.getIndex()));
		}

		return sum;
	}
}