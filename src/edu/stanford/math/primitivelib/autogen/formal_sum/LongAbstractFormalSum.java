package edu.stanford.math.primitivelib.autogen.formal_sum;



public interface LongAbstractFormalSum<M> {
	void put(long coefficient, M object);
	void remove(M object);
	
	boolean containsObject(M object);
	long getCoefficient(M object);
	
	int size();
	boolean isEmpty();
}