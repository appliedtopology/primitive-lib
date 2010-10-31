package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class ObjectIntPairComparator<T> implements Comparator<ObjectIntPair<T>> {
		private final Comparator<T> firstComparator;
		
		
									public ObjectIntPairComparator(Comparator<T> firstComparator) {
				this.firstComparator = firstComparator;
			}
				
	public int compare(ObjectIntPair<T> o1, ObjectIntPair<T> o2) {
				{
			int difference = firstComparator.compare(o1.getFirst(), o2.getFirst());
			
			if (difference != 0) {
				return difference;
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