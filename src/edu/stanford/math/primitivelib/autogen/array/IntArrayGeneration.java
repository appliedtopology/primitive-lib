package edu.stanford.math.primitivelib.autogen.array;



public class IntArrayGeneration {
	/**
     * Generates an array consisting of the integers {start, ... , end - 1}
     * @param start The start element
     * @param end One past the last element
     * @return
     */
    public static int[] range(int start, int end) {
        int length = (int) (end - start);
        int[] values = new int[length];
        for (int i = 0; i < length; i++) {
                values[i] = start + i;
        }
        return values;
    }
    
    public static int[] range(int start, int end, int step) {
        int length = (int) ((end - start) / step);
        int[] values = new int[length];
        for (int i = 0; i < length; i ++) {
                values[i] = start + i * step;
        }
        return values;
    }
    
    public static int[] replicate(int value, int length) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
                result[i] = value;
        }
        return result;
    }
	
	}