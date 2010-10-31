package edu.stanford.math.primitivelib.autogen.formal_sum;


import java.util.Iterator;

import edu.stanford.math.primitivelib.autogen.matrix.FloatMatrixEntry;
import edu.stanford.math.primitivelib.autogen.matrix.FloatSparseMatrix;
import edu.stanford.math.primitivelib.autogen.pair.ObjectObjectPair;
import gnu.trove.TObjectFloatIterator;



/**
 * This class computes matrix representations of module homomorphisms
 * T: F(M) -> F(N), where F(M) and F(N) are free modules on sets with 
 * underlying type M and N.
 * 
 * @author Andrew Tausz
 *
 * @param <float> the type of the underlying commutative ring
 * @param <M> the type of the generating set of the domain
 * @param <N> the type of the generating set of the codomain
 */
public class FloatMatrixConverter<M, N> {
	protected final FloatVectorConverter<M> domainRepresentation;
	protected final FloatVectorConverter<N> codomainRepresentation;
	
	/**
	 * This constructor initializes the object with a basis for the domain and codomain.
	 * 
	 * @param domainBasis a collection consisting of basis elements for the domain
	 * @param codomainBasis a collection consisting of basis elements for the codomain
	 */
	public FloatMatrixConverter(Iterable<M> domainBasis, Iterable<N> codomainBasis) {
		this(new FloatVectorConverter<M>(domainBasis), new FloatVectorConverter<N>(codomainBasis));
	}
	
	public FloatMatrixConverter(FloatVectorConverter<M> domainRepresentation, FloatVectorConverter<N> codomainRepresentation) {
		this.domainRepresentation = domainRepresentation;
		this.codomainRepresentation = codomainRepresentation;
	}
	
	public FloatVectorConverter<M> getDomainRepresentation() {
		return this.domainRepresentation;
	}
	
	public FloatVectorConverter<N> getCodomainRepresentation() {
		return this.codomainRepresentation;
	}
	
		
	public float[][] toArray(FloatSparseFormalSum<ObjectObjectPair<M, N>> formalSum) {
		float[][] matrix = new float[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		
		
		for (TObjectFloatIterator<ObjectObjectPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			ObjectObjectPair<M, N> basisMappingPair = iterator.key();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());
			
			matrix[row][column] = iterator.value();
		}
		
		return matrix;
	}
	
	public float[][] toArray(FloatSparseMatrix sparseMatrix) {
		float[][] matrix = new float[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		
		
		for (Iterator<FloatMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			FloatMatrixEntry entry = iterator.next();
			matrix[entry.getRow()][entry.getCol()] = entry.getValue();
		}
		
		return matrix;
	}
	
		
		
	public FloatSparseMatrix toSparseMatrix(FloatSparseFormalSum<ObjectObjectPair<M, N>> formalSum) {
		FloatSparseMatrix sparseMatrix = new FloatSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());
		
		for (TObjectFloatIterator<ObjectObjectPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			ObjectObjectPair<M, N> basisMappingPair = iterator.key();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());
			
			sparseMatrix.set(row, column, iterator.value());
		}
		
		return sparseMatrix;
	}
	
		
	public FloatSparseMatrix toSparseMatrix(float[][] matrix) {
		FloatSparseMatrix sparseMatrix = new FloatSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
								if (matrix[i][j] == 0) {
					continue;
				}
								sparseMatrix.set(i, j, matrix[i][j]);
			}
		}
		
		return sparseMatrix;
	}
	
	public FloatSparseFormalSum<ObjectObjectPair<M, N>> toFormalSum(FloatSparseMatrix sparseMatrix) {
		FloatSparseFormalSum<ObjectObjectPair<M, N>> formalSum = new FloatSparseFormalSum<ObjectObjectPair<M, N>>();
		
		for (Iterator<FloatMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			FloatMatrixEntry entry = iterator.next();
			M domainBasisElement = this.domainRepresentation.getBasisElement(entry.getRow());
			N codomainBasisElement = this.codomainRepresentation.getBasisElement(entry.getCol());
			ObjectObjectPair<M, N> basisPair = new ObjectObjectPair<M, N>(domainBasisElement, codomainBasisElement);
			formalSum.put(entry.getValue(), basisPair);
		}
		
		return formalSum;
	}
	
	public FloatSparseFormalSum<ObjectObjectPair<M, N>> toFormalSum(float[][] matrix) {
		FloatSparseFormalSum<ObjectObjectPair<M, N>> formalSum = new FloatSparseFormalSum<ObjectObjectPair<M, N>>();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
								if (matrix[i][j] == 0) {
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
}