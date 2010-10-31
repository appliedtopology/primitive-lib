package edu.stanford.math.primitivelib.algebraic.impl;

import org.apache.commons.lang.math.Fraction;

import edu.stanford.math.primitivelib.autogen.algebraic.ObjectAbstractField;

public class RationalField extends ObjectAbstractField<Fraction> {
	private RationalField() {}

	private static final RationalField instance = new RationalField();

	public static RationalField getInstance() {
		return instance;
	}

	@Override
	public Fraction add(Fraction a, Fraction b) {
		return a.add(b);
	}

	@Override
	public Fraction getOne() {
		return Fraction.ONE;
	}

	@Override
	public Fraction getZero() {
		return Fraction.ZERO;
	}

	@Override
	public Fraction multiply(Fraction a, Fraction b) {
		return a.multiplyBy(b);
	}

	@Override
	public Fraction negate(Fraction a) {
		return a.negate();
	}

	@Override
	public Fraction subtract(Fraction a, Fraction b) {
		return a.subtract(b);
	}

	@Override
	public Fraction valueOf(int n) {
		return Fraction.getFraction(n, 1);
	}

	@Override
	public Fraction divide(Fraction a, Fraction b) {
		return a.divideBy(b);
	}

	@Override
	public Fraction invert(Fraction a) {
		return a.invert();
	}

	@Override
	public boolean isUnit(Fraction a) {
		return (a.getNumerator() != 0);
	}

	@Override
	public boolean isZero(Fraction a) {
		return (a.equals(Fraction.ZERO));
	}

	@Override
	public boolean isOne(Fraction a) {
		return (a.equals(Fraction.ONE));
	}

	@Override
	public int characteristic() {
		return 0;
	}

	public int compare(Fraction o1, Fraction o2) {
		return o1.compareTo(o2);
	}

	public Fraction abs(Fraction a) {
		return a.abs();
	}
}
