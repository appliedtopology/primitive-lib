package edu.stanford.math.primitivelib.generation;

import java.util.Hashtable;
import java.util.Vector;

public class ClassSpecifier {
	
	public ClassSpecifier(String packageSpecifier, String classTag) {
		super();
		this.packageSpecifier = packageSpecifier;
		this.classTag = classTag;
	}
	
	public ClassSpecifier(String packageSpecifier, String classTag, Vector<String> templateTypes) {
		super();
		this.packageSpecifier = packageSpecifier;
		this.classTag = classTag;
		this.templateTypes = templateTypes;
	}
	
	public ClassSpecifier(String packageSpecifier, String classTag, Vector<String> templateTypes, Vector<String> genericTypes) {
		super();
		this.packageSpecifier = packageSpecifier;
		this.classTag = classTag;
		this.templateTypes = templateTypes;
		this.genericTypes = genericTypes;
	}
	
	/**
	 * @return the packageSpecifier
	 */
	public String getPackageSpecifier() {
		return packageSpecifier;
	}

	/**
	 * @return the classTag
	 */
	public String getClassTag() {
		return classTag;
	}

	/**
	 * @return the templateTypes
	 */
	public Vector<String> getTemplateTypes() {
		return templateTypes;
	}

	/**
	 * @return the genericTypes
	 */
	public Vector<String> getGenericTypes() {
		return genericTypes;
	}

	/**
	 * @return the additionalContext
	 */
	public Hashtable<String, Object> getAdditionalContext() {
		return additionalContext;
	}
	
	public void addTemplateType(String type) {
		this.templateTypes.add(type);
	}
	
	public void addGenericType(String type) {
		this.genericTypes.add(type);
	}
	
	public void addAdditionalContext(String key, Object value) {
		this.additionalContext.put(key, value);
	}
	
	private String packageSpecifier;
	private String classTag;
	private Vector<String> templateTypes = new Vector<String>();
	private Vector<String> genericTypes = new Vector<String>();
	private Hashtable<String, Object> additionalContext = new Hashtable<String, Object>();
}
