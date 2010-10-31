package edu.stanford.math.primitivelib.metric.impl;

import edu.stanford.math.primitivelib.metric.interfaces.AbstractIntMetricSpace;

public class ExplicitIntMetricSpace implements AbstractIntMetricSpace {
	private final double[][] distanceMatrix;
	
	public ExplicitIntMetricSpace(double[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}
	
	@Override
	public double distance(int i, int j) {
		return this.distanceMatrix[i][j];
	}

	@Override
	public int size() {
		return this.distanceMatrix.length;
	}
}
