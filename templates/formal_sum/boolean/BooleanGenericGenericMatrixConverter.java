
import java.util.Iterator;

import edu.stanford.math.plexlib.autogen.matrix.BooleanMatrixEntry;
import edu.stanford.math.plexlib.autogen.matrix.BooleanSparseMatrix;
import edu.stanford.math.plexlib.autogen.pair.GenericGenericUnorderedPair;



/**
 * This class computes matrix representations of module homomorphisms
 * T: F(M) -> F(N), where F(M) and F(N) are free modules on sets with 
 * underlying type M and N.
 * 
 * @author Andrew Tausz
 *
 * @param <boolean> the type of the underlying commutative ring
 * @param <M> the type of the generating set of the domain
 * @param <N> the type of the generating set of the codomain
 */
public class BooleanGenericGenericMatrixConverter<M, N> {
	protected final BooleanGenericVectorConverter<M> domainRepresentation;
	protected final BooleanGenericVectorConverter<N> codomainRepresentation;

	/**
	 * This constructor initializes the object with a basis for the domain and codomain.
	 * 
	 * @param domainBasis a collection consisting of basis elements for the domain
	 * @param codomainBasis a collection consisting of basis elements for the codomain
	 */
	public BooleanGenericGenericMatrixConverter(Iterable<M> domainBasis, Iterable<N> codomainBasis) {
		this(new BooleanGenericVectorConverter<M>(domainBasis), new BooleanGenericVectorConverter<N>(codomainBasis));
	}

	public BooleanGenericGenericMatrixConverter(BooleanGenericVectorConverter<M> domainRepresentation, BooleanGenericVectorConverter<N> codomainRepresentation) {
		this.domainRepresentation = domainRepresentation;
		this.codomainRepresentation = codomainRepresentation;
	}

	public BooleanGenericVectorConverter<M> getDomainRepresentation() {
		return this.domainRepresentation;
	}

	public BooleanGenericVectorConverter<N> getCodomainRepresentation() {
		return this.codomainRepresentation;
	}


	public boolean[][] toArray(BooleanGenericSparseFormalSum<GenericGenericUnorderedPair<M, N>> formalSum) {
		boolean[][] matrix = new boolean[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		

		for (Iterator<GenericGenericUnorderedPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			GenericGenericUnorderedPair<M, N> basisMappingPair = iterator.next();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());

			matrix[row][column] = true;
		}

		return matrix;
	}

	public boolean[][] toArray(BooleanSparseMatrix sparseMatrix) {
		boolean[][] matrix = new boolean[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		

		for (Iterator<BooleanMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			BooleanMatrixEntry entry = iterator.next();
			matrix[entry.getRow()][entry.getCol()] = true;
		}

		return matrix;
	}



	public BooleanSparseMatrix toSparseMatrix(BooleanGenericSparseFormalSum<GenericGenericUnorderedPair<M, N>> formalSum) {
		BooleanSparseMatrix sparseMatrix = new BooleanSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());

		for (Iterator<GenericGenericUnorderedPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			GenericGenericUnorderedPair<M, N> basisMappingPair = iterator.next();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());

			sparseMatrix.set(row, column, true);
		}

		return sparseMatrix;
	}


	public BooleanSparseMatrix toSparseMatrix(boolean[][] matrix) {
		BooleanSparseMatrix sparseMatrix = new BooleanSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j]) {
					sparseMatrix.set(i, j, matrix[i][j]);
				}
			}
		}

		return sparseMatrix;
	}

	public BooleanGenericSparseFormalSum<GenericGenericUnorderedPair<M, N>> toFormalSum(BooleanSparseMatrix sparseMatrix) {
		BooleanGenericSparseFormalSum<GenericGenericUnorderedPair<M, N>> formalSum = new BooleanGenericSparseFormalSum<GenericGenericUnorderedPair<M, N>>();

		for (Iterator<BooleanMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			BooleanMatrixEntry entry = iterator.next();
			M domainBasisElement = this.domainRepresentation.getBasisElement(entry.getRow());
			N codomainBasisElement = this.codomainRepresentation.getBasisElement(entry.getCol());
			GenericGenericUnorderedPair<M, N> basisPair = new GenericGenericUnorderedPair<M, N>(domainBasisElement, codomainBasisElement);
			formalSum.put(true, basisPair);
		}

		return formalSum;
	}

	public BooleanGenericSparseFormalSum<GenericGenericUnorderedPair<M, N>> toFormalSum(boolean[][] matrix) {
		BooleanGenericSparseFormalSum<GenericGenericUnorderedPair<M, N>> formalSum = new BooleanGenericSparseFormalSum<GenericGenericUnorderedPair<M, N>>();

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == false) {
					continue;
				}
				M domainBasisElement = this.domainRepresentation.getBasisElement(i);
				N codomainBasisElement = this.codomainRepresentation.getBasisElement(j);
				GenericGenericUnorderedPair<M, N> basisPair = new GenericGenericUnorderedPair<M, N>(domainBasisElement, codomainBasisElement);
				formalSum.put(matrix[i][j], basisPair);
			}
		}

		return formalSum;
	}
}