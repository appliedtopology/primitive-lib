package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class LongLongPairComparator implements Comparator<LongLongPair> {
		
		
									public LongLongPairComparator() {}
	
				
	public int compare(LongLongPair o1, LongLongPair o2) {
				{
			long difference = o1.getFirst() - o2.getFirst();
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