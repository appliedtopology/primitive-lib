

import java.util.Iterator;

import edu.stanford.math.plexlib.autogen.pair.IntBooleanUnorderedPair;
import gnu.trove.TIntObjectIterator;


public class BooleanSparseMatrixIterator implements Iterator<BooleanMatrixEntry> {
	private final TIntObjectIterator<BooleanSparseVector> rowIterator;
	private Iterator<BooleanVectorEntry> columnIterator;
	
	BooleanSparseMatrixIterator(BooleanSparseMatrix matrix) {
		this.rowIterator = matrix.map.iterator();
		this.rowIterator.advance();
		this.columnIterator = this.rowIterator.value().iterator();
	}
	
	public boolean hasNext() {
		if (this.rowIterator.hasNext()) {
			return true;
		} else {
			return this.columnIterator.hasNext();
		}
	}

	public BooleanMatrixEntry next() {
		if (!columnIterator.hasNext()) {
			// advance to the next row
			this.rowIterator.advance();
			this.columnIterator = this.rowIterator.value().iterator();
		}
		int row = rowIterator.key();
		BooleanVectorEntry columnValuePair = columnIterator.next();
		int column = columnValuePair.getIndex();
		return new BooleanMatrixEntry(row, column);
	}

	public void remove() {
		this.columnIterator.remove();
	}
}