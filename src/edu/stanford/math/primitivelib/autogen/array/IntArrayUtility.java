package edu.stanford.math.primitivelib.autogen.array;



public class IntArrayUtility {
	public static int[] createArray(int length) {
		return new int[length];
	}
	
	public static int[] createArray(int length, int filler) {
		int[] result = new int[length];
		for (int i = 0; i < length; i++) {
			result[i] = filler;
		}
		return result;
	}

	public static int[][] createMatrix(int rows, int columns) {
		int[][] result = new int[rows][];
		
		for (int i = 0; i < rows; i++) {
			result[i] = new int[columns];
		}
		
		return result;
	}
	
	public static int[][] createMatrix(int rows, int columns, int filler) {
		int[][] result = new int[rows][];
		
		for (int i = 0; i < rows; i++) {
			result[i] = new int[columns];
			for (int j = 0; j < columns; j++) {
				result[i][j] = filler;
			}
		}
		
		return result;
	}
	
	public static String toMatlabString(int[] array) {
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
	
	public static String toMatlabString(int[][] array) {
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