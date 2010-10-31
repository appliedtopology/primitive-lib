package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class ObjectObjectPairComparator<T, U> implements Comparator<ObjectObjectPair<T, U>> {
		private final Comparator<T> firstComparator;
		
		private final Comparator<U> secondComparator;
		
									public ObjectObjectPairComparator(Comparator<T> firstComparator, Comparator<U> secondComparator) {
				this.firstComparator = firstComparator;
				this.secondComparator = secondComparator;
			}
				
	public int compare(ObjectObjectPair<T, U> o1, ObjectObjectPair<T, U> o2) {
				{
			int difference = firstComparator.compare(o1.getFirst(), o2.getFirst());
			
			if (difference != 0) {
				return difference;
			}
		}
		
				
				
		return secondComparator.compare(o1.getSecond(), o2.getSecond());
		
			}
}