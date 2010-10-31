package edu.stanford.math.primitivelib.metric;

public interface ObjectMetricSpace<T> extends IntMetricSpace {
	public T getPoint(int index);
	public double distance(T a, T b);
	public T[] getPoints();
}
