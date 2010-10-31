package edu.stanford.math.primitivelib.metric.interfaces;

public interface AbstractObjectMetricSpace<T> extends AbstractIntMetricSpace {
	public T getPoint(int index);
	public double distance(T a, T b);
	public T[] getPoints();
}
