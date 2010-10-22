package edu.stanford.math.plexlib.generation;

import java.util.ArrayList;
import java.util.Collection;

public class JavaGeneratorUtility {
	
	public String getFullClassName(String tag, Collection<String> parameters, Collection<String> generics) {
		return getBasicClassName(tag, parameters) + getGenericAnnotation(parameters, generics);
	}
	
	public String getWildcardClassName(String tag, Collection<String> parameters, Collection<String> generics) {
		return getBasicClassName(tag, parameters) + getGenericWildcardAnnotation(parameters, generics);
	}
	
	public String getBasicClassName(String tag, Collection<String> parameters) {
		StringBuilder builder = new StringBuilder();
		
		for (String type: parameters) {
			builder.append(getClassNameModifier(type));
		}
		
		builder.append(firstToUpperCase(tag));
		
		return builder.toString();
	}
	
	public String getGenericAnnotation(Collection<String> parameters, Collection<String> generics) {
		return getGenericAnnotation(parameters, generics, false);
	}
	
	public String getGenericWildcardAnnotation(Collection<String> parameters, Collection<String> generics) {
		return getGenericAnnotation(parameters, generics, true);
	}
	
	public String getGenericAnnotation(Collection<String> parameters, Collection<String> generics, boolean wildcard) {
		boolean containsNonPrimitive = false;
		if (!generics.isEmpty()) {
			containsNonPrimitive = true;
		} else {
			for (String type: parameters) {
				if (!isPrimitive(type)) {
					containsNonPrimitive = true;
					break;
				}
			}
		}
		
		if (!containsNonPrimitive) {
			return "";
		}
		
		// at this point we have at least 1 non-primitive type parameter
		
		StringBuilder builder = new StringBuilder();
		int count = 0;
		
		builder.append("<");
		
		// add all generic types
		for (String type: generics) {
			if (count > 0) {
				builder.append(" , ");
			}
			if (wildcard) {
				builder.append("?");
			} else {
				builder.append(type);
			}
			count++;
		}
		
		for (String type: parameters) {
			if (!isPrimitive(type)) {
				if (count > 0) {
					builder.append(" , ");
				}
				if (wildcard) {
					builder.append("?");
				} else {
					builder.append(type);
				}
				count++;
			}
		}
		
		builder.append(">");
		
		return builder.toString();
	}
	
	private String firstToUpperCase(String s) {
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
	
	public String getTroveNameModifier(String type) {
		if (isPrimitive(type)) {
			return this.firstToUpperCase(type);
		} else {
			return "Object";
		}
	}
	
	public String getMapType(String keyType, String valueType) {
		if (!isPrimitive(keyType) && !isPrimitive(valueType)) {
			return "THashMap";
		}
		// eg. TIntDoubleHashMap
		return "T" + getTroveNameModifier(keyType) + getTroveNameModifier(valueType) + "HashMap";
	}
	
	public String getMapIteratorType(String keyType, String valueType) {
		// eg. TIntDoubleIterator
		return "T" + getTroveNameModifier(keyType) + getTroveNameModifier(valueType) + "Iterator";
	}
	
	public String beginMapIteration(String keyType, String valueType, String mapName, String keyVariable, String valueVariable) {
		String iteratorType = getMapIteratorType(keyType, valueType);
		
		StringBuilder builder = new StringBuilder();
		
		if (!isPrimitive(keyType) && !isPrimitive(valueType)) {
			// we have a THashMap - we have to use the standard Iterable 
			builder.append("for (Map.Entry<" + keyType + ", " + valueType + "> __entry : " + mapName + ".entrySet()) {");
			builder.append(keyType + " " + keyVariable + " = " + "__entry.getKey();");
			builder.append(valueType + " " + valueVariable + " = " + "__entry.getValue();");
		} else {
			// we use Trove-style iteration
			builder.append("for (" + iteratorType + " __iterator = " + mapName + ".iterator(); __iterator.hasNext(); ) {");
			builder.append("__iterator.advance();");
			builder.append(keyType + " " + keyVariable + " = " + "__iterator.key();");
			builder.append(valueType + " " + valueVariable + " = " + "__iterator.value();");
		}
		
		return builder.toString();
	}
	
	public String endMapIteration() {
		return "}";
	}
	
	public String beginMapIteration(String keyType, String valueType, String mapName) {
		return beginMapIteration(keyType, valueType, mapName, "__key", "__value");
	}
	
	public String getDistinguishedElement(String type) {
		if (type.equals("byte")) {
			return "0";
		} else if (type.equals("short")) {
			return "0";
		} else if (type.equals("int")) {
			return "0";
		} else if (type.equals("long")) {
			return "0";
		} else if (type.equals("float")) {
			return "0";
		} else if (type.equals("double")) {
			return "0";
		} else if (type.equals("char")) {
			return "0";
		} else if (type.equals("boolean")) {
			return "false";
		} else {
			return "null";
		}
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
}
