package edu.stanford.math.primitivelib.autogen.functional;


/**
 * This interface defines a function from type T to type int.
 * 
 * @author Andrew Tausz
 *
 */
public interface ObjectIntFunction<T> {
	public int evaluate(T argument);
}