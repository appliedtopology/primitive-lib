package edu.stanford.math.primitivelib.autogen.formal_sum;



public interface ObjectAbstractFormalSum<R, M> {
	void put(R coefficient, M object);
	void remove(M object);
	
	boolean containsObject(M object);
	R getCoefficient(M object);
	
	int size();
	boolean isEmpty();
}