package edu.stanford.math.primitivelib.autogen.formal_sum;



import java.util.Iterator;

import edu.stanford.math.primitivelib.autogen.matrix.BooleanMatrixEntry;
import edu.stanford.math.primitivelib.autogen.matrix.BooleanSparseMatrix;
import edu.stanford.math.primitivelib.autogen.pair.ObjectObjectPair;



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
public class BooleanMatrixConverter<M, N> {
	protected final BooleanVectorConverter<M> domainRepresentation;
	protected final BooleanVectorConverter<N> codomainRepresentation;

	/**
	 * This constructor initializes the object with a basis for the domain and codomain.
	 * 
	 * @param domainBasis a collection consisting of basis elements for the domain
	 * @param codomainBasis a collection consisting of basis elements for the codomain
	 */
	public BooleanMatrixConverter(Iterable<M> domainBasis, Iterable<N> codomainBasis) {
		this(new BooleanVectorConverter<M>(domainBasis), new BooleanVectorConverter<N>(codomainBasis));
	}

	public BooleanMatrixConverter(BooleanVectorConverter<M> domainRepresentation, BooleanVectorConverter<N> codomainRepresentation) {
		this.domainRepresentation = domainRepresentation;
		this.codomainRepresentation = codomainRepresentation;
	}

	public BooleanVectorConverter<M> getDomainRepresentation() {
		return this.domainRepresentation;
	}

	public BooleanVectorConverter<N> getCodomainRepresentation() {
		return this.codomainRepresentation;
	}


	public boolean[][] toArray(BooleanSparseFormalSum<ObjectObjectPair<M, N>> formalSum) {
		boolean[][] matrix = new boolean[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		

		for (Iterator<ObjectObjectPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			ObjectObjectPair<M, N> basisMappingPair = iterator.next();
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



	public BooleanSparseMatrix toSparseMatrix(BooleanSparseFormalSum<ObjectObjectPair<M, N>> formalSum) {
		BooleanSparseMatrix sparseMatrix = new BooleanSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());

		for (Iterator<ObjectObjectPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			ObjectObjectPair<M, N> basisMappingPair = iterator.next();
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

	public BooleanSparseFormalSum<ObjectObjectPair<M, N>> toFormalSum(BooleanSparseMatrix sparseMatrix) {
		BooleanSparseFormalSum<ObjectObjectPair<M, N>> formalSum = new BooleanSparseFormalSum<ObjectObjectPair<M, N>>();

		for (Iterator<BooleanMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			BooleanMatrixEntry entry = iterator.next();
			M domainBasisElement = this.domainRepresentation.getBasisElement(entry.getRow());
			N codomainBasisElement = this.codomainRepresentation.getBasisElement(entry.getCol());
			ObjectObjectPair<M, N> basisPair = new ObjectObjectPair<M, N>(domainBasisElement, codomainBasisElement);
			formalSum.put(true, basisPair);
		}

		return formalSum;
	}

	public BooleanSparseFormalSum<ObjectObjectPair<M, N>> toFormalSum(boolean[][] matrix) {
		BooleanSparseFormalSum<ObjectObjectPair<M, N>> formalSum = new BooleanSparseFormalSum<ObjectObjectPair<M, N>>();

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == false) {
					continue;
				}
				M domainBasisElement = this.domainRepresentation.getBasisElement(i);
				N codomainBasisElement = this.codomainRepresentation.getBasisElement(j);
				ObjectObjectPair<M, N> basisPair = new ObjectObjectPair<M, N>(domainBasisElement, codomainBasisElement);
				formalSum.put(matrix[i][j], basisPair);
			}
		}

		return formalSum;
	}
};
