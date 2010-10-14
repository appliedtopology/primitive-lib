package edu.stanford.math.plexlib.generation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

public class ClassGenerator {
	private static GeneratorUtility utility = new GeneratorUtility();
	private static String basePackageName = "edu.stanford.math.plexlib.autogen";
	
	public static void main(String[] args) throws Exception {
		generateMatrix();
		generateAllPairs();
	}
	
	private static void generateAllPairs() throws Exception {
		List<String> types1 = new ArrayList<String>();
		types1.add("int");
		types1.add("double");
		types1.add("float");
		types1.add("T");
		
		List<String> types2 = new ArrayList<String>();
		types2.add("int");
		types2.add("double");
		types2.add("float");
		types2.add("U");

		generatePairs(types1, types2);
	}
	
	private static void generateMatrix() throws Exception {
		List<String> types = new ArrayList<String>();
		types.add("int");
		types.add("double");
		types.add("float");
		
		generateTemplate("AbstractVector", "matrix", types);
		generateTemplate("MatrixEntry", "matrix", types);
		generateTemplate("AbstractMatrix", "matrix", types);
		generateTemplate("SparseVector", "matrix", types);
		generateTemplate("SparseVectorIterator", "matrix", types);
	}
	
	private static void generateTemplate(String templateName, String packageSegment, Iterable<String> types) throws Exception {
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		
		BufferedWriter writer = null;
		try {
			String packageName = basePackageName + "." + packageSegment;
			String directoryPath = "src/" + convertToPath(packageName);
			directoryPath = createDirectory(directoryPath);
			
			for (String type: types) {
				String className = utility.getClassName(templateName, type);
				String filePath = directoryPath + className + ".java";
				
				Template t = ve.getTemplate("templates/" + templateName + ".vm");
				VelocityContext context = new VelocityContext();
				context.put("utility", utility);
				context.put("packageName", packageName);
				context.put("type", type);
				
				writer = new BufferedWriter(new FileWriter(filePath));
				t.merge(context, writer);
				writer.close();
				writer = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	private static void generatePairs(Iterable<String> types1, Iterable<String> types2) throws Exception {
		VelocityEngine ve = new VelocityEngine();
		ve.init();
		
		BufferedWriter writer = null;
		try {
			for (String type1: types1) {
				boolean type1IsPrimitive = utility.isPrimitive(type1);
				
				for (String type2: types2) {
					boolean type2IsPrimitive = utility.isPrimitive(type2);
					
					String templateName = null;
					if (type1IsPrimitive && type2IsPrimitive) {
						templateName = "OrderedPair";
					} else if (type1IsPrimitive && !type2IsPrimitive) {
						templateName = "OrderedPair";
					} else if (!type1IsPrimitive && type2IsPrimitive) {
						templateName = "OrderedPair";
					} else {
						templateName = "OrderedPair";
					}
					
					String packageName = basePackageName + "." + utility.toUnderscoreFormat(templateName);
					String directoryPath = "src/" + convertToPath(packageName);
					directoryPath = createDirectory(directoryPath);
					
					String className = utility.getClassName("Pair", type1, type2);
					String filePath = directoryPath + className + ".java";
					
					Template t = ve.getTemplate("templates/" + templateName + ".vm");
					VelocityContext context = new VelocityContext();
					context.put("utility", utility);
					context.put("packageName", packageName);
					context.put("type1", type1);
					context.put("type2", type2);
					
					writer = new BufferedWriter(new FileWriter(filePath));
					t.merge(context, writer);
					writer.close();
					writer = null;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
	
	private static String convertToPath(String javaPackageName) {
		return javaPackageName.replace('.', '/');
	}
	
	private static String createDirectory(String directoryPath) throws IOException {
		boolean directoryCreated = false;
		File directory = new File(directoryPath);
		directoryCreated = directory.isDirectory() || directory.mkdirs();
		if (!directoryCreated) {
			throw new IOException("Could not create directory: " + directoryPath);
		}
		return directory.getCanonicalPath() + System.getProperty("file.separator");
	}
}
