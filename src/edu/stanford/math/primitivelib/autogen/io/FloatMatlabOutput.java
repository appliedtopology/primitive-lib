package edu.stanford.math.primitivelib.autogen.io;

import java.io.IOException;
import java.io.Writer;

import edu.stanford.math.primitivelib.autogen.matrix.FloatMatrixEntry;
import edu.stanford.math.primitivelib.autogen.matrix.FloatSparseMatrix;
import edu.stanford.math.primitivelib.autogen.matrix.FloatSparseVector;
import edu.stanford.math.primitivelib.autogen.matrix.FloatVectorEntry;



public class FloatMatlabOutput {

	public static void writeAsMatlabArray(Writer writer, float[] array, String separator) throws IOException {
		writer.write('[');
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				writer.write(separator);
			}
			writer.write(Float .toString(array[i]));
		}
		writer.write("]");
	}
	
	public static void writeAsMatlabRow(Writer writer, float[] array) throws IOException {
		writeAsMatlabArray(writer, array, ",");
	}
	
	public static void writeAsMatlabCol(Writer writer, float[] array) throws IOException {
		writeAsMatlabArray(writer, array, ";");
	}
	
	public static void writeAsMatlabRow(Writer writer, float[] array, String name) throws IOException {
		writer.write(name + " = ");
		writeAsMatlabArray(writer, array, ",");
		writer.write(";\n");
	}
	
	public static void writeAsMatlabCol(Writer writer, float[] array, String name) throws IOException {
		writer.write(name + " = ");
		writeAsMatlabArray(writer, array, ":");
		writer.write(";\n");
	}
	
	public static void writeAsMatlabMatrix(Writer writer, float[][] matrix) throws IOException {
		writer.write('[');
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (j > 0) {
					writer.write(", ");
				}
				writer.write(Float .toString(matrix[i][j]));
			}
			writer.write(";");
		}
		writer.write("]");
	}
	
	public static void writeAsMatlabMatrix(Writer writer, float[][] matrix, String name) throws IOException {
		writer.write(name + " = ");
		writeAsMatlabMatrix(writer, matrix);
		writer.write(";\n");
	}
	
	public static void writeAsMatlabSparseCol(Writer writer, float[] matrix, String name) throws IOException {
		int numSparseEntry = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] != 0.0f) {
				numSparseEntry++;
			}
		}
		
		int[] _tmp_i = new int[numSparseEntry];
		int[] _tmp_j = new int[numSparseEntry];
		float[] _tmp_s = new float[numSparseEntry];
		
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] != 0.0f) {
				_tmp_i[i] = i + 1;
				_tmp_j[i] = 1;
				_tmp_s[i] = matrix[i];
			}
		}
		
		writer.write("tmp_m = " + matrix.length + ";\n");
		writer.write("tmp_n = " + 1 + ";\n");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_i, "tmp_i");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_j, "tmp_j");
		writeAsMatlabRow(writer, _tmp_s, "tmp_s");
		writer.write(name + " = sparse(tmp_i, tmp_j, tmp_s, tmp_m, tmp_n);");
	}
	
	public static void writeAsMatlabSparseRow(Writer writer, float[] matrix, String name) throws IOException {
		int numSparseEntry = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] != 0.0f) {
				numSparseEntry++;
			}
		}
		
		int[] _tmp_i = new int[numSparseEntry];
		int[] _tmp_j = new int[numSparseEntry];
		float[] _tmp_s = new float[numSparseEntry];
		
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i] != 0.0f) {
				_tmp_i[i] = 1;
				_tmp_j[i] = i + 1;
				_tmp_s[i] = matrix[i];
			}
		}
		
		writer.write("tmp_m = " + 1 + ";\n");
		writer.write("tmp_n = " + matrix.length + ";\n");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_i, "tmp_i");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_j, "tmp_j");
		writeAsMatlabRow(writer, _tmp_s, "tmp_s");
		writer.write(name + " = sparse(tmp_i, tmp_j, tmp_s, tmp_m, tmp_n);");
	}
	
	public static void writeAsMatlabSparseMatrix(Writer writer, float[][] matrix, String name) throws IOException {
		int numSparseEntry = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] != 0.0f) {
					numSparseEntry++;
				}
			}
		}
		
		int[] _tmp_i = new int[numSparseEntry];
		int[] _tmp_j = new int[numSparseEntry];
		float[] _tmp_s = new float[numSparseEntry];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] != 0.0f) {
					_tmp_i[i] = i + 1;
					_tmp_j[i] = j + 1;
					_tmp_s[i] = matrix[i][j];
				}
			}
		}
		
		writer.write("tmp_m = " + matrix.length + ";\n");
		writer.write("tmp_n = " + matrix[0].length + ";\n");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_i, "tmp_i");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_j, "tmp_j");
		writeAsMatlabRow(writer, _tmp_s, "tmp_s");
		writer.write(name + " = sparse(tmp_i, tmp_j, tmp_s, tmp_m, tmp_n);");
	}

	public static void writeAsMatlabSparseCol(Writer writer, FloatSparseVector vector, String name) throws IOException {
		int numSparseEntry = vector.getNumNonzeroElements();
		
		int[] _tmp_i = new int[numSparseEntry];
		int[] _tmp_j = new int[numSparseEntry];
		float[] _tmp_s = new float[numSparseEntry];
		
		int i = 0;
		for (FloatVectorEntry pair: vector) {
			_tmp_i[i] = pair.getIndex() + 1;
			_tmp_j[i] = 1;
			_tmp_s[i] = pair.getValue();
			i++;
		}
		
		writer.write("tmp_m = " + vector.getLength() + ";\n");
		writer.write("tmp_n = " + 1 + ";\n");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_i, "tmp_i");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_j, "tmp_j");
		writeAsMatlabRow(writer, _tmp_s, "tmp_s");
		writer.write(name + " = sparse(tmp_i, tmp_j, tmp_s, tmp_m, tmp_n);");
	}
	
	public static void writeAsMatlabSparseRow(Writer writer, FloatSparseVector vector, String name) throws IOException {
		int numSparseEntry = vector.getNumNonzeroElements();
		
		int[] _tmp_i = new int[numSparseEntry];
		int[] _tmp_j = new int[numSparseEntry];
		float[] _tmp_s = new float[numSparseEntry];
		
		int i = 0;
		for (FloatVectorEntry pair: vector) {
			_tmp_i[i] = 1;
			_tmp_j[i] = pair.getIndex() + 1;
			_tmp_s[i] = pair.getValue();
			i++;
		}
		
		writer.write("tmp_m = " + 1 + ";\n");
		writer.write("tmp_n = " + vector.getLength() + ";\n");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_i, "tmp_i");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_j, "tmp_j");
		writeAsMatlabRow(writer, _tmp_s, "tmp_s");
		writer.write(name + " = sparse(tmp_i, tmp_j, tmp_s, tmp_m, tmp_n);");
	}
	
	public static void writeAsMatlabSparseCol(Writer writer, FloatSparseMatrix matrix, String name) throws IOException {
		int numSparseEntry = matrix.getNumNonzeroElements();
		
		int[] _tmp_i = new int[numSparseEntry];
		int[] _tmp_j = new int[numSparseEntry];
		float[] _tmp_s = new float[numSparseEntry];
		
		int i = 0;
		for (FloatMatrixEntry entry: matrix) {
			_tmp_i[i] = entry.getRow() + 1;
			_tmp_j[i] = entry.getCol() + 1;
			_tmp_s[i] = entry.getValue();
			i++;
		}
		
		writer.write("tmp_m = " + matrix.getNumRows() + ";\n");
		writer.write("tmp_n = " + matrix.getNumColumns() + ";\n");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_i, "tmp_i");
		IntMatlabOutput .writeAsMatlabRow(writer, _tmp_j, "tmp_j");
		writeAsMatlabRow(writer, _tmp_s, "tmp_s");
		writer.write(name + " = sparse(tmp_i, tmp_j, tmp_s, tmp_m, tmp_n);");
	}
}