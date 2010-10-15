package edu.stanford.math.plexlib.generation;

import java.util.ArrayList;
import java.util.List;

public class GeneratorUtility {
	public GeneratorUtility(){}
	
	public String firstToUpperCase(String s) {
		if (s.length() == 0) return s;
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
	}
	
	public String getClassNameModifier(String type) {
		if (isPrimitive(type)) {
			return this.firstToUpperCase(type);
		} else {
			return "Generic";
		}
	}
	
	public String getClassName(String baseName, String type) {
		return this.getClassNameModifier(type) + baseName;
	}
	
	public String getClassName(String baseName, String type1, String type2) {
		return this.getClassNameModifier(type1) + this.getClassNameModifier(type2) + baseName;
	}
	
	public String getClassName(String baseName, String type1, String type2, String type3) {
		return this.getClassNameModifier(type1) + this.getClassNameModifier(type2) + this.getClassNameModifier(type3) + baseName;
	}
	
	public String generateGenericAnnotation(String type1) {
		List<String> list = new ArrayList<String>();
		list.add(type1);
		return this.generateGenericAnnotation(list);
	}
	
	public String generateGenericAnnotation(String type1, String type2) {
		List<String> list = new ArrayList<String>();
		list.add(type1);
		list.add(type2);
		return this.generateGenericAnnotation(list);
	}
	
	public String generateGenericAnnotation(String type1, String type2, String type3) {
		List<String> list = new ArrayList<String>();
		list.add(type1);
		list.add(type2);
		list.add(type3);
		return this.generateGenericAnnotation(list);
	}
	
	public String generateGenericAnnotation(Iterable<String> typeList) {
		boolean containsNonPrimitive = false;
		for (String type: typeList) {
			if (!isPrimitive(type)) {
				containsNonPrimitive = true;
				break;
			}
		}
		
		if (!containsNonPrimitive) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		int count = 0;
		
		builder.append("<");
		
		for (String type: typeList) {
			if (!isPrimitive(type)) {
				if (count > 0) {
					builder.append(", ");
				}
				builder.append(type);
				count++;
			}
		}
		
		builder.append(">");
		
		return builder.toString();
	}
	
	public String generateGenericAnnotationImplements(String type1, String interfaceName) {
		List<String> list = new ArrayList<String>();
		list.add(type1);
		return this.generateGenericAnnotationImplements(list, interfaceName);
	}
	
	public String generateGenericAnnotationImplements(String type1, String type2, String interfaceName) {
		List<String> list = new ArrayList<String>();
		list.add(type1);
		list.add(type2);
		return this.generateGenericAnnotationImplements(list, interfaceName);
	}
	
	public String generateGenericAnnotationImplements(String type1, String type2, String type3, String interfaceName) {
		List<String> list = new ArrayList<String>();
		list.add(type1);
		list.add(type2);
		list.add(type3);
		return this.generateGenericAnnotationImplements(list, interfaceName);
	}
	
	public String generateGenericAnnotationImplements(Iterable<String> typeList, String interfaceName) {
		boolean containsNonPrimitive = false;
		for (String type: typeList) {
			if (!isPrimitive(type)) {
				containsNonPrimitive = true;
				break;
			}
		}
		
		if (!containsNonPrimitive) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		int count = 0;
		
		builder.append("<");
		
		for (String type: typeList) {
			if (!isPrimitive(type)) {
				if (count > 0) {
					builder.append(", ");
				}
				builder.append(type);
				builder.append(" extends " + interfaceName + "<" + type + ">");
				count++;
			}
		}
		
		builder.append(">");
		
		return builder.toString();
	}
	
	public String generateGenericWildcards(String type1) {
		List<String> list = new ArrayList<String>();
		list.add(type1);
		return this.generateGenericWildcards(list);
	}
	
	public String generateGenericWildcards(String type1, String type2) {
		List<String> list = new ArrayList<String>();
		list.add(type1);
		list.add(type2);
		return this.generateGenericWildcards(list);
	}
	
	public String generateGenericWildcards(String type1, String type2, String type3) {
		List<String> list = new ArrayList<String>();
		list.add(type1);
		list.add(type2);
		list.add(type3);
		return this.generateGenericWildcards(list);
	}
	
	public String generateGenericWildcards(Iterable<String> typeList) {
		boolean containsNonPrimitive = false;
		for (String type: typeList) {
			if (!isPrimitive(type)) {
				containsNonPrimitive = true;
				break;
			}
		}
		
		if (!containsNonPrimitive) {
			return "";
		}
		
		StringBuilder builder = new StringBuilder();
		int count = 0;
		
		builder.append("<");
		
		for (String type: typeList) {
			if (!isPrimitive(type)) {
				if (count > 0) {
					builder.append(", ");
				}
				builder.append("?");
				count++;
			}
		}
		
		builder.append(">");
		
		return builder.toString();
	}
	
	public String getFullClassName(String baseName, String type1) {
		return this.getClassName(baseName, type1) + this.generateGenericAnnotation(type1);
	}
	
	public String getFullClassName(String baseName, String type1, String type2) {
		return this.getClassName(baseName, type1, type2) + this.generateGenericAnnotation(type1, type2);
	}
	
	public String getFullClassName(String baseName, String type1, String type2, String type3) {
		return this.getClassName(baseName, type1, type2, type3) + this.generateGenericAnnotation(type1, type2, type3);
	}
	
	public String toUnderscoreFormat(String javaName) {
		int underscoreCount = 0;
		char[] characters = javaName.toCharArray();
		
		for (int i = 0; i < characters.length - 1; i++) {
			if (isLower(characters[i]) && !isLower(characters[i + 1])) {
				underscoreCount++;
			}
		}
		
		char[] newCharacters = new char[characters.length + underscoreCount];
		int targetIndex = 0;
		
		for (int i = 0; i < characters.length; i++) {
			newCharacters[targetIndex] = characters[i];
			targetIndex++;
			if (i < characters.length - 1 && isLower(characters[i]) && !isLower(characters[i + 1])) {
				newCharacters[targetIndex] = '_';
				targetIndex++;
			}
		}
		
		return new String(newCharacters).toLowerCase();
	}
	
	private boolean isLower(char c) {
		return (c >= 'a' && c <= 'z');
	}
	
	public boolean isPrimitive(String primitiveName) {
		if (primitiveName.equals("byte")) {
			return true;
		} else if (primitiveName.equals("short")) {
			return true;
		} else if (primitiveName.equals("int")) {
			return true;
		} else if (primitiveName.equals("long")) {
			return true;
		} else if (primitiveName.equals("float")) {
			return true;
		} else if (primitiveName.equals("double")) {
			return true;
		} else if (primitiveName.equals("char")) {
			return true;
		} else if (primitiveName.equals("boolean")) {
			return true;
		} else {
			return false;
		}
	}
	
	public String getBoxedName(String primitiveName) {
		if (primitiveName.equals("byte")) {
			return "Byte";
		} else if (primitiveName.equals("short")) {
			return "Short";
		} else if (primitiveName.equals("int")) {
			return "Integer";
		} else if (primitiveName.equals("long")) {
			return "Long";
		} else if (primitiveName.equals("float")) {
			return "Float";
		} else if (primitiveName.equals("double")) {
			return "Double";
		} else if (primitiveName.equals("char")) {
			return "Char";
		} else if (primitiveName.equals("boolean")) {
			return "Boolean";
		} else {
			return "";
		}
	}
	
	public String getComparisonName(String comparison) {
		if (comparison.equals("==")) {
			return "Equal";
		} else if (comparison.equals("!=")) {
			return "NotEqual";
		} else if (comparison.equals("<")) {
			return "LessThan";
		} else if (comparison.equals(">")) {
			return "GreaterThan";
		} else if (comparison.equals("<=")) {
			return "LessThanOrEqual";
		} else if (comparison.equals(">=")) {
			return "GreaterThanOrEqual";
		} else {
			return "";
		}
	}
	
	public Iterable<String> getAllComparisons() { 
		ArrayList<String> list = new ArrayList<String>();
		list.add("==");
		list.add("!=");
		list.add("<");
		list.add(">");
		list.add("<=");
		list.add(">=");
		return list;
	}
	
	public String getTroveNameModifier(String type) {
		if (isPrimitive(type)) {
			return this.firstToUpperCase(type);
		} else {
			return "Object";
		}
	}
	
	public String getTroveMapName(String type1, String type2) {
		if (!isPrimitive(type1) && !isPrimitive(type2)) {
			return "THashMap";
		}
		// eg. TIntDoubleHashMap
		return "T" + getTroveNameModifier(type1) + getTroveNameModifier(type2) + "HashMap";
	}
	
	public String getTroveIteratorName(String type1, String type2) {
		// eg. TIntDoubleIterator
		return "T" + getTroveNameModifier(type1) + getTroveNameModifier(type2) + "Iterator";
	}
}
