package edu.stanford.math.primitivelib.autogen.formal_sum;


import java.util.Iterator;
import java.util.Map;

import edu.stanford.math.primitivelib.autogen.matrix.ObjectMatrixEntry;
import edu.stanford.math.primitivelib.autogen.matrix.ObjectSparseMatrix;
import edu.stanford.math.primitivelib.autogen.pair.ObjectObjectPair;



/**
 * This class computes matrix representations of module homomorphisms
 * T: F(M) -> F(N), where F(M) and F(N) are free modules on sets with 
 * underlying type M and N.
 * 
 * @author Andrew Tausz
 *
 * @param <R> the type of the underlying commutative ring
 * @param <M> the type of the generating set of the domain
 * @param <N> the type of the generating set of the codomain
 */
public class ObjectMatrixConverter<R, M, N> {
	protected final ObjectVectorConverter<R, M> domainRepresentation;
	protected final ObjectVectorConverter<R, N> codomainRepresentation;
	
	/**
	 * This constructor initializes the object with a basis for the domain and codomain.
	 * 
	 * @param domainBasis a collection consisting of basis elements for the domain
	 * @param codomainBasis a collection consisting of basis elements for the codomain
	 */
	public ObjectMatrixConverter(Iterable<M> domainBasis, Iterable<N> codomainBasis) {
		this(new ObjectVectorConverter<R, M>(domainBasis), new ObjectVectorConverter<R, N>(codomainBasis));
	}
	
	public ObjectMatrixConverter(ObjectVectorConverter<R, M> domainRepresentation, ObjectVectorConverter<R, N> codomainRepresentation) {
		this.domainRepresentation = domainRepresentation;
		this.codomainRepresentation = codomainRepresentation;
	}
	
	public ObjectVectorConverter<R, M> getDomainRepresentation() {
		return this.domainRepresentation;
	}
	
	public ObjectVectorConverter<R, N> getCodomainRepresentation() {
		return this.codomainRepresentation;
	}
	
		
		
	public ObjectSparseMatrix<R> toSparseMatrix(ObjectSparseFormalSum<R, ObjectObjectPair<M, N>> formalSum) {
		ObjectSparseMatrix<R> sparseMatrix = new ObjectSparseMatrix<R>(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());
		
		for (Map.Entry<ObjectObjectPair<M, N>, R> entry : formalSum.map.entrySet()) {
			ObjectObjectPair<M, N> basisMappingPair = entry.getKey();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());
			
			sparseMatrix.set(row, column, entry.getValue());
		}
		
		return sparseMatrix;
	}
	
		
	public ObjectSparseMatrix<R> toSparseMatrix(R[][] matrix) {
		ObjectSparseMatrix<R> sparseMatrix = new ObjectSparseMatrix<R>(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
								if (matrix[i][j] == null) {
					continue;
				}
								sparseMatrix.set(i, j, matrix[i][j]);
			}
		}
		
		return sparseMatrix;
	}
	
	public ObjectSparseFormalSum<R, ObjectObjectPair<M, N>> toFormalSum(ObjectSparseMatrix<R> sparseMatrix) {
		ObjectSparseFormalSum<R, ObjectObjectPair<M, N>> formalSum = new ObjectSparseFormalSum<R, ObjectObjectPair<M, N>>();
		
		for (Iterator<ObjectMatrixEntry<R>> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			ObjectMatrixEntry<R> entry = iterator.next();
			M domainBasisElement = this.domainRepresentation.getBasisElement(entry.getRow());
			N codomainBasisElement = this.codomainRepresentation.getBasisElement(entry.getCol());
			ObjectObjectPair<M, N> basisPair = new ObjectObjectPair<M, N>(domainBasisElement, codomainBasisElement);
			formalSum.put(entry.getValue(), basisPair);
		}
		
		return formalSum;
	}
	
	public ObjectSparseFormalSum<R, ObjectObjectPair<M, N>> toFormalSum(R[][] matrix) {
		ObjectSparseFormalSum<R, ObjectObjectPair<M, N>> formalSum = new ObjectSparseFormalSum<R, ObjectObjectPair<M, N>>();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
								if (matrix[i][j] == null) {
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