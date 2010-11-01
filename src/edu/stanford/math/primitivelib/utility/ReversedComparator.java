package edu.stanford.math.primitivelib.utility;

import java.util.Comparator;

public class ReversedComparator<T> implements Comparator<T> {
	private final Comparator<T> forwardComparator;
	
	public ReversedComparator(Comparator<T> forwardComparator) {
		this.forwardComparator = forwardComparator;
	}
	
	@Override
	public int compare(T arg0, T arg1) {
		return -this.forwardComparator.compare(arg0, arg1);
	}
}
