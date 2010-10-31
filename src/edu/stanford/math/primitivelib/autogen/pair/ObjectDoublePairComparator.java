package edu.stanford.math.primitivelib.autogen.pair;

import java.util.Comparator;


public class ObjectDoublePairComparator<T> implements Comparator<ObjectDoublePair<T>> {
		private final Comparator<T> firstComparator;
		
		
									public ObjectDoublePairComparator(Comparator<T> firstComparator) {
				this.firstComparator = firstComparator;
			}
				
	public int compare(ObjectDoublePair<T> o1, ObjectDoublePair<T> o2) {
				{
			int difference = firstComparator.compare(o1.getFirst(), o2.getFirst());
			
			if (difference != 0) {
				return difference;
			}
		}
		
				
				{
			double difference = o1.getSecond() - o2.getSecond();
			if (difference > 0) {
				return 1;
			} else if (difference < 0) {
				return -1;
			}
			return 0;
		}
		
			}
}