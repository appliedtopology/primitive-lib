

import java.util.Iterator;

import edu.stanford.math.plexlib.autogen.pair.IntBooleanUnorderedPair;
import gnu.trove.TIntIterator;


public class BooleanSparseVectorIterator implements Iterator<IntBooleanUnorderedPair> {
	private final TIntIterator iterator;
	
	public BooleanSparseVectorIterator(BooleanSparseVector vector) {
		this.iterator = vector.map.iterator();
	}
	
	public boolean hasNext() {
		return this.iterator.hasNext();
	}

	public IntBooleanUnorderedPair next() {
		return new IntBooleanUnorderedPair(this.iterator.next(), true);
	}

	public void remove() {
		this.iterator.remove();
	}
}