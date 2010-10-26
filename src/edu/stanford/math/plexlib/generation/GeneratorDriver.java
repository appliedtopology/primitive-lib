package edu.stanford.math.plexlib.generation;

import java.util.List;
import java.util.Vector;

public class GeneratorDriver {
	private static List<ClassSpecifier> classSpecifiers = new Vector<ClassSpecifier>();
	private static JavaGeneratorUtility utility = JavaGeneratorUtility.getInstance();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		addArrayClasses();
		generateClasses();
	}

	private static void addArrayClasses() {
		Vector<String> primitiveTypes = utility.getPrimitiveTypes();
		String[] classes = new String[]{"ArrayManipulation", "ArrayMath", "ArrayQuery", "ArrayUtility"};
		String packageId = "array";
		
		for (String type: primitiveTypes) {
			Vector<String> templateTypes = new Vector<String>();
			templateTypes.add(type);
			for (String classTag: classes) {
				ClassSpecifier specifier = new ClassSpecifier(packageId, classTag, templateTypes);
				classSpecifiers.add(specifier);
			}
		}
	}
	
	private static void generateClasses() {
		JavaCodeGenerator generator = new JavaCodeGenerator();
		generator.generateClasses(classSpecifiers);
	}
	
}
