package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class DoubleIntPairComparator implements Comparator<DoubleIntPair> {
		
		
									public DoubleIntPairComparator() {}
	
				
	public int compare(DoubleIntPair o1, DoubleIntPair o2) {
				{
			double difference = o1.getFirst() - o2.getFirst();
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