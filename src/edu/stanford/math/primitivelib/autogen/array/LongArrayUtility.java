package edu.stanford.math.primitivelib.autogen.array;



public class LongArrayUtility {
	public static long[] createArray(int length) {
		return new long[length];
	}
	
	public static long[] createArray(int length, long filler) {
		long[] result = new long[length];
		for (int i = 0; i < length; i++) {
			result[i] = filler;
		}
		return result;
	}

	public static long[][] createMatrix(int rows, int columns) {
		long[][] result = new long[rows][];
		
		for (int i = 0; i < rows; i++) {
			result[i] = new long[columns];
		}
		
		return result;
	}
	
	public static long[][] createMatrix(int rows, int columns, long filler) {
		long[][] result = new long[rows][];
		
		for (int i = 0; i < rows; i++) {
			result[i] = new long[columns];
			for (int j = 0; j < columns; j++) {
				result[i][j] = filler;
			}
		}
		
		return result;
	}
	
	public static String toMatlabString(long[] array) {
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				builder.append(", ");
			}
			builder.append(array[i]);
		}
		builder.append("]");
		return builder.toString();		
	}
	
	public static String toMatlabString(long[][] array) {
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (j > 0) {
					builder.append(", ");
				}
				builder.append(array[i][j]);
			}
			builder.append(";\n");
		}
		builder.append("]\n");
		return builder.toString();
	}
}