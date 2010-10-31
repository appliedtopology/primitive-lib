package edu.stanford.math.primitivelib.metric.impl;

import edu.stanford.math.primitivelib.metric.interfaces.AbstractSearchableMetricSpace;
import gnu.trove.TIntHashSet;

public abstract class ObjectSearchableFiniteMetricSpace<T> implements AbstractSearchableMetricSpace<T> {
	protected final T[] elements;
	
	ObjectSearchableFiniteMetricSpace(T[] array) {
		this.elements = array;
	}
	
	@Override
	public TIntHashSet getKNearestNeighbors(T queryPoint, int k) {
		// TODO: complete
		return null;
	}

	@Override
	public int getNearestPointIndex(T queryPoint) {
		double minimumDistance = Double.MAX_VALUE;
		double currentDistance = 0;
		int nearestIndex = 0;
		int n = this.elements.length;
		
		for (int i = 0; i < n; i++) {
			currentDistance = this.distance(queryPoint, this.elements[i]);
			if (currentDistance < minimumDistance) {
				minimumDistance = currentDistance;
				nearestIndex = i;
			}
		}
		
		return nearestIndex;
	}

	@Override
	public TIntHashSet getOpenNeighborhood(T queryPoint, double epsilon) {
		TIntHashSet neighborhood = new TIntHashSet();
		if (epsilon == 0) {
			return neighborhood;
		}
		
		int n = this.elements.length;
		
		for (int i = 0; i < n; i++) {
			if (this.distance(queryPoint, this.elements[i]) < epsilon) {
				neighborhood.add(i);
			}	
		}

		return neighborhood;
	}

	@Override
	public TIntHashSet getClosedNeighborhood(T queryPoint, double epsilon) {
		TIntHashSet neighborhood = new TIntHashSet();
		if (epsilon == 0) {
			return neighborhood;
		}
		
		int n = this.elements.length;
		
		for (int i = 0; i < n; i++) {
			if (this.distance(queryPoint, this.elements[i]) <= epsilon) {
				neighborhood.add(i);
			}	
		}

		return neighborhood;
	}
	
	@Override
	public abstract double distance(T a, T b);

	@Override
	public T getPoint(int index) {
		return this.elements[index];
	}

	@Override
	public T[] getPoints() {
		return this.elements;
	}

	@Override
	public double distance(int i, int j) {
		return this.distance(this.elements[i], this.elements[j]);
	}

	@Override
	public int size() {
		return this.elements.length;
	}

}
