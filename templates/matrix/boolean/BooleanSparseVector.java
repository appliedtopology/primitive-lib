
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

import edu.stanford.math.plexlib.autogen.pair.IntBooleanUnorderedPair;

import gnu.trove.TIntHashSet;
import gnu.trove.TIntIterator;


public class BooleanSparseVector implements BooleanAbstractVector {
	protected final TIntHashSet map = new TIntHashSet();
	protected final int size;

	public BooleanSparseVector(int size) {
		this.size = size;
	}

	public BooleanSparseVector(boolean[] array) {
		this.size = array.length;
		for (int i = 0; i < size; i++) {
			if (array[i] == false) {
				continue;
			}
			this.map.add(i);
		}
	}

	public BooleanAbstractVector like(int size) {
		return new BooleanSparseVector(size);
	}

	public int getNumNonzeroElements() {
		return this.map.size();
	}

	public double getDensity() {
		return ((double) this.getNumNonzeroElements()) / ((double) (size));
	}

	public void set(int index, boolean value) {
		if (value) {
			this.map.add(index);
		} else {
			this.map.remove(index);
		}
	}

	public boolean get(int index) {
		return this.map.contains(index);
	}

	public int getLength() {
		return this.size;
	}

	public boolean innerProduct(BooleanSparseVector other) {
		boolean sum = false;
		BooleanSparseVector smaller = (this.map.size() < other.map.size() ? this : other);
		BooleanSparseVector larger = (this.map.size() < other.map.size() ? other : this);

		for (TIntIterator iterator = smaller.map.iterator(); iterator.hasNext(); ) {
			if (larger.map.contains(iterator.next())) {
				sum ^= true;
			}
		}

		return sum;
	}

	public boolean innerProduct(boolean[] other) {
		boolean sum = false;
		for (TIntIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			sum ^= other[iterator.next()];
		}
		return sum;
	}

	public boolean innerProduct(BooleanAbstractVector other) {
		if (other instanceof BooleanSparseVector) {
			return this.innerProduct((BooleanSparseVector) other);
		}
		boolean sum = false;
		for (TIntIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			sum ^= other.get(iterator.next());
		}
		return sum;
	}

	public Iterator<BooleanVectorEntry> iterator() {
		return new BooleanSparseVectorIterator(this);
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		int index = 0;
		builder.append("[");
		for (TIntIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			if (index > 0) {
				builder.append(", ");
			}
			builder.append(iterator.next());
			builder.append(": 1");
			index++;
		}
		builder.append("]");

		return builder.toString();
	}

	public void write(Writer writer) throws IOException {
		int index = 0;
		writer.write("[");
		for (TIntIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			if (index > 0) {
				writer.write(", ");
			}
			writer.write(iterator.next());
			writer.write(": 1");
			index++;
		}
		writer.write("]");
	}


	public boolean[] toArray() {
		boolean[] array = new boolean[this.size];

		for (TIntIterator iterator = this.map.iterator(); iterator.hasNext(); ) {
			array[iterator.next()] = true;
		}

		return array;
	}

}