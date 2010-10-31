package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class DoubleLongPairComparator implements Comparator<DoubleLongPair> {
		
		
									public DoubleLongPairComparator() {}
	
				
	public int compare(DoubleLongPair o1, DoubleLongPair o2) {
				{
			double difference = o1.getFirst() - o2.getFirst();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
		}
				
				{
			long difference = o1.getSecond() - o2.getSecond();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
			return 0;
		}
		
			}
}