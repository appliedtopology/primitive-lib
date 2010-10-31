package edu.stanford.math.primitivelib.autogen.formal_sum;


import java.util.Iterator;

import edu.stanford.math.primitivelib.autogen.matrix.DoubleMatrixEntry;
import edu.stanford.math.primitivelib.autogen.matrix.DoubleSparseMatrix;
import edu.stanford.math.primitivelib.autogen.pair.ObjectObjectPair;
import gnu.trove.TObjectDoubleIterator;



/**
 * This class computes matrix representations of module homomorphisms
 * T: F(M) -> F(N), where F(M) and F(N) are free modules on sets with 
 * underlying type M and N.
 * 
 * @author Andrew Tausz
 *
 * @param <double> the type of the underlying commutative ring
 * @param <M> the type of the generating set of the domain
 * @param <N> the type of the generating set of the codomain
 */
public class DoubleMatrixConverter<M, N> {
	protected final DoubleVectorConverter<M> domainRepresentation;
	protected final DoubleVectorConverter<N> codomainRepresentation;
	
	/**
	 * This constructor initializes the object with a basis for the domain and codomain.
	 * 
	 * @param domainBasis a collection consisting of basis elements for the domain
	 * @param codomainBasis a collection consisting of basis elements for the codomain
	 */
	public DoubleMatrixConverter(Iterable<M> domainBasis, Iterable<N> codomainBasis) {
		this(new DoubleVectorConverter<M>(domainBasis), new DoubleVectorConverter<N>(codomainBasis));
	}
	
	public DoubleMatrixConverter(DoubleVectorConverter<M> domainRepresentation, DoubleVectorConverter<N> codomainRepresentation) {
		this.domainRepresentation = domainRepresentation;
		this.codomainRepresentation = codomainRepresentation;
	}
	
	public DoubleVectorConverter<M> getDomainRepresentation() {
		return this.domainRepresentation;
	}
	
	public DoubleVectorConverter<N> getCodomainRepresentation() {
		return this.codomainRepresentation;
	}
	
		
	public double[][] toArray(DoubleSparseFormalSum<ObjectObjectPair<M, N>> formalSum) {
		double[][] matrix = new double[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		
		
		for (TObjectDoubleIterator<ObjectObjectPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			ObjectObjectPair<M, N> basisMappingPair = iterator.key();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());
			
			matrix[row][column] = iterator.value();
		}
		
		return matrix;
	}
	
	public double[][] toArray(DoubleSparseMatrix sparseMatrix) {
		double[][] matrix = new double[this.codomainRepresentation.getDimension()][this.domainRepresentation.getDimension()];		
		
		for (Iterator<DoubleMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			DoubleMatrixEntry entry = iterator.next();
			matrix[entry.getRow()][entry.getCol()] = entry.getValue();
		}
		
		return matrix;
	}
	
		
		
	public DoubleSparseMatrix toSparseMatrix(DoubleSparseFormalSum<ObjectObjectPair<M, N>> formalSum) {
		DoubleSparseMatrix sparseMatrix = new DoubleSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());
		
		for (TObjectDoubleIterator<ObjectObjectPair<M, N>> iterator = formalSum.map.iterator(); iterator.hasNext(); ) {
			iterator.advance();
			ObjectObjectPair<M, N> basisMappingPair = iterator.key();
			int row = this.domainRepresentation.getIndex(basisMappingPair.getFirst());
			int column = this.codomainRepresentation.getIndex(basisMappingPair.getSecond());
			
			sparseMatrix.set(row, column, iterator.value());
		}
		
		return sparseMatrix;
	}
	
		
	public DoubleSparseMatrix toSparseMatrix(double[][] matrix) {
		DoubleSparseMatrix sparseMatrix = new DoubleSparseMatrix(this.codomainRepresentation.getDimension(), this.domainRepresentation.getDimension());
		
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
	
	public DoubleSparseFormalSum<ObjectObjectPair<M, N>> toFormalSum(DoubleSparseMatrix sparseMatrix) {
		DoubleSparseFormalSum<ObjectObjectPair<M, N>> formalSum = new DoubleSparseFormalSum<ObjectObjectPair<M, N>>();
		
		for (Iterator<DoubleMatrixEntry> iterator = sparseMatrix.iterator(); iterator.hasNext(); ) {
			DoubleMatrixEntry entry = iterator.next();
			M domainBasisElement = this.domainRepresentation.getBasisElement(entry.getRow());
			N codomainBasisElement = this.codomainRepresentation.getBasisElement(entry.getCol());
			ObjectObjectPair<M, N> basisPair = new ObjectObjectPair<M, N>(domainBasisElement, codomainBasisElement);
			formalSum.put(entry.getValue(), basisPair);
		}
		
		return formalSum;
	}
	
	public DoubleSparseFormalSum<ObjectObjectPair<M, N>> toFormalSum(double[][] matrix) {
		DoubleSparseFormalSum<ObjectObjectPair<M, N>> formalSum = new DoubleSparseFormalSum<ObjectObjectPair<M, N>>();
		
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