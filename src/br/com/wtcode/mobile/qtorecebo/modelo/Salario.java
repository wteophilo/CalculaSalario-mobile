package br.com.wtcode.mobile.qtorecebo.modelo;

import java.math.BigDecimal;

public class Salario {
	private BigDecimal bruto;
	private Integer numeroDeDependentes;
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

	public Integer getNumeroDeDependentes() {
		return numeroDeDependentes;
	}

	public void setNumeroDeDependentes(Integer numeroDeDependentes) {
		this.numeroDeDependentes = numeroDeDependentes;
	}

	public BigDecimal getLiquido() {
		return liquido;
	}

	public void setLiquido(BigDecimal liquido) {
		this.liquido = liquido;
	}

	@Override
	public String toString() {
		return "Salario [bruto=" + bruto + ", numeroDeDependentes="
				+ numeroDeDependentes + ", liquido=" + liquido + "]";
	}

}
