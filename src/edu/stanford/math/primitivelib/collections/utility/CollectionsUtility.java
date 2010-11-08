package edu.stanford.math.primitivelib.collections.utility;

import gnu.trove.THashSet;

import java.util.Set;

public class CollectionsUtility {
	public static <T> Set<T> dumpIterable(Iterable<T> stream) {
		Set<T> set = new THashSet<T>();
		
		for (T element: stream) {
			set.add(element);
		}
		
		return set;
	}
}
