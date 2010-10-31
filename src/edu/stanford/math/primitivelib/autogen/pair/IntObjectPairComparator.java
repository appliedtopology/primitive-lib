package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class IntObjectPairComparator<U> implements Comparator<IntObjectPair<U>> {
		
		private final Comparator<U> secondComparator;
		
									public IntObjectPairComparator(Comparator<U> secondComparator) {
				this.secondComparator = secondComparator;
			}
				
	public int compare(IntObjectPair<U> o1, IntObjectPair<U> o2) {
				{
			int difference = o1.getFirst() - o2.getFirst();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
		}
				
				
		return secondComparator.compare(o1.getSecond(), o2.getSecond());
		
			}
}