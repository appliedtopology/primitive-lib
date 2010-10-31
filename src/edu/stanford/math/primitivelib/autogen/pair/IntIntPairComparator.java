package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class IntIntPairComparator implements Comparator<IntIntPair> {
		
		
									public IntIntPairComparator() {}
	
				
	public int compare(IntIntPair o1, IntIntPair o2) {
				{
			int difference = o1.getFirst() - o2.getFirst();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
		}
				
				{
			int difference = o1.getSecond() - o2.getSecond();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
			return 0;
		}
		
			}
}