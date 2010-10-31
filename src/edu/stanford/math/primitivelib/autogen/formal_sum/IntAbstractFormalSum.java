package edu.stanford.math.primitivelib.autogen.formal_sum;



public interface IntAbstractFormalSum<M> {
	void put(int coefficient, M object);
	void remove(M object);
	
	boolean containsObject(M object);
	int getCoefficient(M object);
	
	int size();
	boolean isEmpty();
}