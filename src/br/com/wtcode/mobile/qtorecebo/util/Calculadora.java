package br.com.wtcode.mobile.qtorecebo.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora {

	public static BigDecimal add(BigDecimal num1, BigDecimal num2) {
		num1 = num1.add(num2);
		return num1.setScale(2, RoundingMode.HALF_UP);
	}

	public static BigDecimal sub(BigDecimal num1, BigDecimal num2) {
		num1 = num1.subtract(num2);
		return num1.setScale(2, RoundingMode.HALF_UP);
	}

	public static BigDecimal mult(BigDecimal num1, BigDecimal num2) {
		num1 = num1.multiply(num2);
		return num1.setScale(2, RoundingMode.HALF_UP);
	}

	public static BigDecimal div(BigDecimal num1, BigDecimal num2) {
		num1 = num1.divide(num2);
		return num1.setScale(2, RoundingMode.HALF_UP);
	}
}
