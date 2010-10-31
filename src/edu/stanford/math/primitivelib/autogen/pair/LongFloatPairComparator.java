package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class LongFloatPairComparator implements Comparator<LongFloatPair> {
		
		
									public LongFloatPairComparator() {}
	
				
	public int compare(LongFloatPair o1, LongFloatPair o2) {
				{
			long difference = o1.getFirst() - o2.getFirst();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
		}
				
				{
			float difference = o1.getSecond() - o2.getSecond();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
			return 0;
		}
		
			}
}