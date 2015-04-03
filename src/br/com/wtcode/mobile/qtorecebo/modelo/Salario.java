package br.com.wtcode.mobile.qtorecebo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Salario implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal bruto;
	private BigDecimal liquido;

	public Salario() {}

	
	public Salario(BigDecimal bruto) {
		this.bruto = bruto;
	}
		
	public BigDecimal getBruto() {
		return bruto;
	}

	public void setBruto(BigDecimal bruto) {
		this.bruto = bruto;
	}

	public BigDecimal getLiquido() {
		return liquido;
	}

	public void setLiquido(BigDecimal liquido) {
		this.liquido = liquido;
	}

	@Override
	public String toString() {
		return "Salario [bruto=" + bruto +", liquido=" + liquido + "]";
	}

}
