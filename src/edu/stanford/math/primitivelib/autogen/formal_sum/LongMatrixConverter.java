package edu.stanford.math.primitivelib.autogen.formal_sum;


import java.util.Iterator;

import edu.stanford.math.primitivelib.autogen.matrix.LongMatrixEntry;
import edu.stanford.math.primitivelib.autogen.matrix.LongSparseMatrix;
import edu.stanford.math.primitivelib.autogen.pair.ObjectObjectPair;
import gnu.trove.TObjectLongIterator;



/**
 * This class computes matrix representations of module homomorphisms
 * T: F(M) -> F(N), where F(M) and F(N) are free modules on sets with 
 * underlying type M and N.
 * 
 * @author Andrew Tausz
 *
 * @param <long> the type of the underlying commutative ring
 * @param <M> the type of the generating set of the domain
 * @param <N> the type of the generating set of the codomain
 */
public class LongMatrixConverter<M, N> {
	protected final LongVectorConverter<M> domainRepresentation;
	protected final LongVectorConverter<N> codomainRepresentation;
	
	/**
	 * This constructor initializes the object with a basis for the domain and codomain.
	 * 
	 * @param domainBasis a collection consisting of basis elements for the domain
	 * @param codomainBasis a collection consisting of basis elements for the codomain
	 */
	public LongMatrixConverter(Iterable<M> domainBasis, Iterable<N> codomainBasis) {
		this(new LongVectorConverter<M>(domainBasis), new LongVectorConverter<N>(codomainBasis));
	}
	
	public LongMatrixConverter(LongVectorConverter<M> domainRepresentation, LongVectorConverter<N> codomainRepresentation) {
		this.domainRepresentation = domainRepresentation;
		this.codomainRepresentation = codomainRepresentation;
	}
	
	public LongVectorConverter<M> getDomainRepresentation() {
		return this.domainRepresentation;
	}
	
	public LongVectorConverter<N> getCodomainRepresentation() {
		return this.codomainRepresentation;
	}
	
		
	public long[][] toArray(LongSparseFormalSum<ObjectObjectPair<M, N>> formalSum) {
		long[][] matrix = new long[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		
		
		for (TObjectLongIterator<ObjectObjectPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			ObjectObjectPair<M, N> basisMappingPair = iterator.key();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());
			
			matrix[row][column] = iterator.value();
		}
		
		return matrix;
	}
	
	public long[][] toArray(LongSparseMatrix sparseMatrix) {
		long[][] matrix = new long[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		
		
		for (Iterator<LongMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			LongMatrixEntry entry = iterator.next();
			matrix[entry.getRow()][entry.getCol()] = entry.getValue();
		}
		
		return matrix;
	}
	
		
		
	public LongSparseMatrix toSparseMatrix(LongSparseFormalSum<ObjectObjectPair<M, N>> formalSum) {
		LongSparseMatrix sparseMatrix = new LongSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());
		
		for (TObjectLongIterator<ObjectObjectPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			ObjectObjectPair<M, N> basisMappingPair = iterator.key();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());
			
			sparseMatrix.set(row, column, iterator.value());
		}
		
		return sparseMatrix;
	}
	
		
	public LongSparseMatrix toSparseMatrix(long[][] matrix) {
		LongSparseMatrix sparseMatrix = new LongSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());
		
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
	
	public LongSparseFormalSum<ObjectObjectPair<M, N>> toFormalSum(LongSparseMatrix sparseMatrix) {
		LongSparseFormalSum<ObjectObjectPair<M, N>> formalSum = new LongSparseFormalSum<ObjectObjectPair<M, N>>();
		
		for (Iterator<LongMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			LongMatrixEntry entry = iterator.next();
			M domainBasisElement = this.domainRepresentation.getBasisElement(entry.getRow());
			N codomainBasisElement = this.codomainRepresentation.getBasisElement(entry.getCol());
			ObjectObjectPair<M, N> basisPair = new ObjectObjectPair<M, N>(domainBasisElement, codomainBasisElement);
			formalSum.put(entry.getValue(), basisPair);
		}
		
		return formalSum;
	}
	
	public LongSparseFormalSum<ObjectObjectPair<M, N>> toFormalSum(long[][] matrix) {
		LongSparseFormalSum<ObjectObjectPair<M, N>> formalSum = new LongSparseFormalSum<ObjectObjectPair<M, N>>();
		
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