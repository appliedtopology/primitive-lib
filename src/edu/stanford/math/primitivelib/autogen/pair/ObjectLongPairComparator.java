package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class ObjectLongPairComparator<T> implements Comparator<ObjectLongPair<T>> {
		private final Comparator<T> firstComparator;
		
		
									public ObjectLongPairComparator(Comparator<T> firstComparator) {
				this.firstComparator = firstComparator;
			}
				
	public int compare(ObjectLongPair<T> o1, ObjectLongPair<T> o2) {
				{
			int difference = firstComparator.compare(o1.getFirst(), o2.getFirst());
			
			if (difference != 0) {
				return difference;
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