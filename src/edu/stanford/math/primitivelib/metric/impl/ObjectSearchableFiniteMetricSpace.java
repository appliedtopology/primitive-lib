package edu.stanford.math.primitivelib.metric.impl;

import edu.stanford.math.primitivelib.metric.interfaces.AbstractSearchableMetricSpace;
import gnu.trove.TIntHashSet;

public abstract class ObjectSearchableFiniteMetricSpace<T> implements AbstractSearchableMetricSpace<T> {
	protected final T[] elements;
	
	ObjectSearchableFiniteMetricSpace(T[] array) {
		this.elements = array;
	}
	
	public TIntHashSet getKNearestNeighbors(T queryPoint, int k) {
		// TODO: complete
		return null;
	}

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
	
	public abstract double distance(T a, T b);

	public T getPoint(int index) {
		return this.elements[index];
	}

	public T[] getPoints() {
		return this.elements;
	}

	public double distance(int i, int j) {
		return this.distance(this.elements[i], this.elements[j]);
	}

	public int size() {
		return this.elements.length;
	}

}
