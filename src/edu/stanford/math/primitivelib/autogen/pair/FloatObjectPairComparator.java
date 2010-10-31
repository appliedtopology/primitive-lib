package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class FloatObjectPairComparator<U> implements Comparator<FloatObjectPair<U>> {
		
		private final Comparator<U> secondComparator;
		
									public FloatObjectPairComparator(Comparator<U> secondComparator) {
				this.secondComparator = secondComparator;
			}
				
	public int compare(FloatObjectPair<U> o1, FloatObjectPair<U> o2) {
				{
			float difference = o1.getFirst() - o2.getFirst();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
		}
				
				
		return secondComparator.compare(o1.getSecond(), o2.getSecond());
		
			}
}