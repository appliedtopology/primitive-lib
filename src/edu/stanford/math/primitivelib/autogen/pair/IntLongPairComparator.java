package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class IntLongPairComparator implements Comparator<IntLongPair> {
		
		
									public IntLongPairComparator() {}
	
				
	public int compare(IntLongPair o1, IntLongPair o2) {
				{
			int difference = o1.getFirst() - o2.getFirst();
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