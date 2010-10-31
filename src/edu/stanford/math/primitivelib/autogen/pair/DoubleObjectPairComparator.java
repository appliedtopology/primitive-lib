package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class DoubleObjectPairComparator<U> implements Comparator<DoubleObjectPair<U>> {
		
		private final Comparator<U> secondComparator;
		
									public DoubleObjectPairComparator(Comparator<U> secondComparator) {
				this.secondComparator = secondComparator;
			}
				
	public int compare(DoubleObjectPair<U> o1, DoubleObjectPair<U> o2) {
				{
			double difference = o1.getFirst() - o2.getFirst();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
		}
				
				
		return secondComparator.compare(o1.getSecond(), o2.getSecond());
		
			}
}