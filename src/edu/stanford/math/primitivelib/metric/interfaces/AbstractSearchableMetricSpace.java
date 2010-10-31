package edu.stanford.math.primitivelib.metric.interfaces;

import gnu.trove.TIntHashSet;

public interface AbstractSearchableMetricSpace<T> extends AbstractObjectMetricSpace<T> {
	public int getNearestPointIndex(T queryPoint);
	public TIntHashSet getOpenNeighborhood(T queryPoint, double epsilon);
	public TIntHashSet getClosedNeighborhood(T queryPoint, double epsilon);
	public TIntHashSet getKNearestNeighbors(T queryPoint, int k);
}
