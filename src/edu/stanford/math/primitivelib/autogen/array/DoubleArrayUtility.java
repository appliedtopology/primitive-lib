package edu.stanford.math.primitivelib.autogen.array;



public class DoubleArrayUtility {
	public static double[] createArray(int length) {
		return new double[length];
	}
	
	public static double[] createArray(int length, double filler) {
		double[] result = new double[length];
		for (int i = 0; i < length; i++) {
			result[i] = filler;
		}
		return result;
	}

	public static double[][] createMatrix(int rows, int columns) {
		double[][] result = new double[rows][];
		
		for (int i = 0; i < rows; i++) {
			result[i] = new double[columns];
		}
		
		return result;
	}
	
	public static double[][] createMatrix(int rows, int columns, double filler) {
		double[][] result = new double[rows][];
		
		for (int i = 0; i < rows; i++) {
			result[i] = new double[columns];
			for (int j = 0; j < columns; j++) {
				result[i][j] = filler;
			}
		}
		
		return result;
	}
	
	public static String toMatlabString(double[] array) {
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
	
	public static String toMatlabString(double[][] array) {
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