package edu.stanford.math.primitivelib.autogen.formal_sum;



public interface FloatAbstractFormalSum<M> {
	void put(float coefficient, M object);
	void remove(M object);
	
	boolean containsObject(M object);
	float getCoefficient(M object);
	
	int size();
	boolean isEmpty();
}