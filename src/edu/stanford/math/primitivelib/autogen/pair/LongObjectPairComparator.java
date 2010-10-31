package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class LongObjectPairComparator<U> implements Comparator<LongObjectPair<U>> {
		
		private final Comparator<U> secondComparator;
		
									public LongObjectPairComparator(Comparator<U> secondComparator) {
				this.secondComparator = secondComparator;
			}
				
	public int compare(LongObjectPair<U> o1, LongObjectPair<U> o2) {
				{
			long difference = o1.getFirst() - o2.getFirst();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
		}
				
				
		return secondComparator.compare(o1.getSecond(), o2.getSecond());
		
			}
}