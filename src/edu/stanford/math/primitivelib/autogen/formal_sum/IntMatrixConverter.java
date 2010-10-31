package edu.stanford.math.primitivelib.autogen.formal_sum;


import java.util.Iterator;

import edu.stanford.math.primitivelib.autogen.matrix.IntMatrixEntry;
import edu.stanford.math.primitivelib.autogen.matrix.IntSparseMatrix;
import edu.stanford.math.primitivelib.autogen.pair.ObjectObjectPair;
import gnu.trove.TObjectIntIterator;



/**
 * This class computes matrix representations of module homomorphisms
 * T: F(M) -> F(N), where F(M) and F(N) are free modules on sets with 
 * underlying type M and N.
 * 
 * @author Andrew Tausz
 *
 * @param <int> the type of the underlying commutative ring
 * @param <M> the type of the generating set of the domain
 * @param <N> the type of the generating set of the codomain
 */
public class IntMatrixConverter<M, N> {
	protected final IntVectorConverter<M> domainRepresentation;
	protected final IntVectorConverter<N> codomainRepresentation;
	
	/**
	 * This constructor initializes the object with a basis for the domain and codomain.
	 * 
	 * @param domainBasis a collection consisting of basis elements for the domain
	 * @param codomainBasis a collection consisting of basis elements for the codomain
	 */
	public IntMatrixConverter(Iterable<M> domainBasis, Iterable<N> codomainBasis) {
		this(new IntVectorConverter<M>(domainBasis), new IntVectorConverter<N>(codomainBasis));
	}
	
	public IntMatrixConverter(IntVectorConverter<M> domainRepresentation, IntVectorConverter<N> codomainRepresentation) {
		this.domainRepresentation = domainRepresentation;
		this.codomainRepresentation = codomainRepresentation;
	}
	
	public IntVectorConverter<M> getDomainRepresentation() {
		return this.domainRepresentation;
	}
	
	public IntVectorConverter<N> getCodomainRepresentation() {
		return this.codomainRepresentation;
	}
	
		
	public int[][] toArray(IntSparseFormalSum<ObjectObjectPair<M, N>> formalSum) {
		int[][] matrix = new int[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		
		
		for (TObjectIntIterator<ObjectObjectPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			ObjectObjectPair<M, N> basisMappingPair = iterator.key();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());
			
			matrix[row][column] = iterator.value();
		}
		
		return matrix;
	}
	
	public int[][] toArray(IntSparseMatrix sparseMatrix) {
		int[][] matrix = new int[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		
		
		for (Iterator<IntMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			IntMatrixEntry entry = iterator.next();
			matrix[entry.getRow()][entry.getCol()] = entry.getValue();
		}
		
		return matrix;
	}
	
		
		
	public IntSparseMatrix toSparseMatrix(IntSparseFormalSum<ObjectObjectPair<M, N>> formalSum) {
		IntSparseMatrix sparseMatrix = new IntSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());
		
		for (TObjectIntIterator<ObjectObjectPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			ObjectObjectPair<M, N> basisMappingPair = iterator.key();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());
			
			sparseMatrix.set(row, column, iterator.value());
		}
		
		return sparseMatrix;
	}
	
		
	public IntSparseMatrix toSparseMatrix(int[][] matrix) {
		IntSparseMatrix sparseMatrix = new IntSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());
		
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
	
	public IntSparseFormalSum<ObjectObjectPair<M, N>> toFormalSum(IntSparseMatrix sparseMatrix) {
		IntSparseFormalSum<ObjectObjectPair<M, N>> formalSum = new IntSparseFormalSum<ObjectObjectPair<M, N>>();
		
		for (Iterator<IntMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			IntMatrixEntry entry = iterator.next();
			M domainBasisElement = this.domainRepresentation.getBasisElement(entry.getRow());
			N codomainBasisElement = this.codomainRepresentation.getBasisElement(entry.getCol());
			ObjectObjectPair<M, N> basisPair = new ObjectObjectPair<M, N>(domainBasisElement, codomainBasisElement);
			formalSum.put(entry.getValue(), basisPair);
		}
		
		return formalSum;
	}
	
	public IntSparseFormalSum<ObjectObjectPair<M, N>> toFormalSum(int[][] matrix) {
		IntSparseFormalSum<ObjectObjectPair<M, N>> formalSum = new IntSparseFormalSum<ObjectObjectPair<M, N>>();
		
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