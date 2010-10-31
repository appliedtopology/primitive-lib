package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class LongDoublePairComparator implements Comparator<LongDoublePair> {
		
		
									public LongDoublePairComparator() {}
	
				
	public int compare(LongDoublePair o1, LongDoublePair o2) {
				{
			long difference = o1.getFirst() - o2.getFirst();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
		}
				
				{
			double difference = o1.getSecond() - o2.getSecond();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
			return 0;
		}
		
			}
}