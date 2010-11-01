package edu.stanford.math.primitivelib.autogen.functional;


/**
 * This interface defines a function from type T to type U.
 * 
 * @author Andrew Tausz
 *
 */
public interface ObjectObjectFunction<T, U> {
	public U evaluate(T argument);
}