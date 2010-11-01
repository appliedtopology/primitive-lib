package edu.stanford.math.primitivelib.metric.impl;

import edu.stanford.math.primitivelib.autogen.array.DoubleArrayMath;

public class EuclideanMetricSpace extends ObjectSearchableFiniteMetricSpace<double[]> {

	public EuclideanMetricSpace(double[][] array) {
		super(array);
	}

	@Override
	public double distance(double[] a, double[] b) {
		return DoubleArrayMath.distance(a, b);
	}
}
