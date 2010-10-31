package edu.stanford.math.primitivelib.algebraic.impl;

import java.util.HashMap;
import java.util.Map;

import edu.stanford.math.primitivelib.autogen.algebraic.IntAbstractField;

public class ModularIntField extends IntAbstractField {
	private final int p;
	private final int[] inverses;
	private static Map<Integer, ModularIntField> map = new HashMap<Integer, ModularIntField>();
	
	public static ModularIntField getInstance(int p) {
		if (map.containsKey(p)) {
			return map.get(p);
		} else {
			ModularIntField finiteField = new ModularIntField(p);
			map.put(p, finiteField);
			return finiteField;
		}
	}
	
	/**
	 * This function returns the multiplicative inverses of the integers
	 * [0, 1, ..., p-1] in mod p arithmetic. Note that the 0-th index is 
	 * simply set to 0 for convenience so that the inverse of n is in the n-th
	 * component of the returned array.
	 * 
	 * TODO:
	 * This is a somewhat inefficient way to compute the inverses, however it works
	 * fine for small p.
	 * 
	 * @param p
	 * @return an array containing the multiplicative inverses in the field Z/pZ
	 */
	public static int[] modularInverses(int p) {
		int[] inverses = new int[p];
		for (int i = 1; i < p; i++) {
			int inverse = 0;
			for (int j = 1; j < p; j++) {
				if (((j * i) % p) == 1) {
					inverse = j;
					break;
				}
			}

			if (inverse == 0) {
				throw new IllegalArgumentException(p + " is not a prime.");
			}

			inverses[i] = inverse;
		}
		
		return inverses;
	}
	
	private ModularIntField(int p) {
		this.p = p;
		this.inverses = modularInverses(p);
	}

	@Override
	public int divide(int a, int b) {
		if ((b % p) == 0) {
			throw new ArithmeticException();
		}
		int index = b % p;
		if (index < 0) {
			index += p;
		}
		return ((a * this.inverses[index]) % p);
	}

	@Override
	public int invert(int a) {
		if ((a % p) == 0) {
			throw new ArithmeticException();
		}
		int r = a % p;
		if (r < 0) {
			r += p;
		}
		return this.inverses[r];
	}

	@Override
	public int add(int a, int b) {
		int r = (a + b) % p;
		if (r < 0) {
			r += p;
		}
		return r;
	}

	@Override
	public int getOne() {
		return 1;
	}

	@Override
	public int getZero() {
		return 0;
	}

	@Override
	public int multiply(int a, int b) {
		int r = (a * b) % p;
		if (r < 0) {
			r += p;
		}
		return r;
	}

	@Override
	public int negate(int a) {
		int r = (-a) % p;
		if (r < 0) {
			r += p;
		}
		return r;
	}

	@Override
	public int subtract(int a, int b) {
		int r = (a - b) % p;
		if (r < 0) {
			r += p;
		}
		return r;
	}

	@Override
	public int valueOf(int n) {
		int r = n % p;
		if (r < 0) {
			r += p;
		}
		return r;
	}

	@Override
	public boolean isUnit(int a) {
		return (a % p != 0);
	}
	
	@Override
	public boolean isZero(int a) {
		return (a % p == 0);
	}
	
	@Override
	public boolean isOne(int a) {
		return (a % p == 1);
	}

	@Override
	public int characteristic() {
		return this.p;
	}
}
