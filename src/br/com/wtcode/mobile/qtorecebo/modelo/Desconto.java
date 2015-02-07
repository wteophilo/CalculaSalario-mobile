package br.com.wtcode.mobile.qtorecebo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Properties;

import br.com.wtcode.mobile.qtorecebo.util.ManipulaProperties;

public abstract class Desconto implements Serializable {
	private static final long serialVersionUID = 1L;
	protected  BigDecimal desconto;
	private  BigDecimal porcentagem;
	protected Salario salario;
	protected ManipulaProperties manipulaProperties;
	
	public Desconto(Salario salario,Properties aliquotas) {
		this.salario = salario;
		this.manipulaProperties = new ManipulaProperties(aliquotas);
	}	
	
	public abstract BigDecimal calculaPorcentagem();
	public abstract BigDecimal calculaValorDesconto();
	
	public BigDecimal getDesconto() {
		return desconto;
	}
	
	public BigDecimal getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(BigDecimal porcentagem) {
		this.porcentagem = porcentagem;
	}
}
