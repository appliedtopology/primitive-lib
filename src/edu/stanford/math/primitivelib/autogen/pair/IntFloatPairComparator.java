package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class IntFloatPairComparator implements Comparator<IntFloatPair> {
		
		
									public IntFloatPairComparator() {}
	
				
	public int compare(IntFloatPair o1, IntFloatPair o2) {
				{
			int difference = o1.getFirst() - o2.getFirst();
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