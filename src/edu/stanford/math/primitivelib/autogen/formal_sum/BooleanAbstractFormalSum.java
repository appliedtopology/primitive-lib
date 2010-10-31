package edu.stanford.math.primitivelib.autogen.formal_sum;




public interface BooleanAbstractFormalSum<M> {
	void put(boolean coefficient, M object);
	void remove(M object);
	
	boolean containsObject(M object);
	boolean getCoefficient(M object);
	
	int size();
	boolean isEmpty();
};
