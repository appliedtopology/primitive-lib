package edu.stanford.math.primitivelib.autogen.formal_sum;



public interface DoubleAbstractFormalSum<M> {
	void put(double coefficient, M object);
	void remove(M object);
	
	boolean containsObject(M object);
	double getCoefficient(M object);
	
	int size();
	boolean isEmpty();
}