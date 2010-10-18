

public interface BooleanGenericAbstractFormalSum<M> {
	void put(boolean coefficient, M object);
	void remove(M object);
	
	boolean containsObject(M object);
	boolean getCoefficient(M object);
	
	int size();
	boolean isEmpty();
}