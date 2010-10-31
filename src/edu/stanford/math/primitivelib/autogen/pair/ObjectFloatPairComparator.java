package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class ObjectFloatPairComparator<T> implements Comparator<ObjectFloatPair<T>> {
		private final Comparator<T> firstComparator;
		
		
									public ObjectFloatPairComparator(Comparator<T> firstComparator) {
				this.firstComparator = firstComparator;
			}
				
	public int compare(ObjectFloatPair<T> o1, ObjectFloatPair<T> o2) {
				{
			int difference = firstComparator.compare(o1.getFirst(), o2.getFirst());
			
			if (difference != 0) {
				return difference;
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