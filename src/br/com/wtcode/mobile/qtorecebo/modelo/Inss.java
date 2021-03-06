package br.com.wtcode.mobile.qtorecebo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Properties;

import br.com.wtcode.mobile.qtorecebo.util.Calculadora;

public class Inss extends Desconto implements Serializable{
	private static final long serialVersionUID = 1L;
	public final static BigDecimal LIMITE = new BigDecimal("482.93");
	
	public Inss(Salario salario,Properties aliquotas) {
		super(salario,aliquotas);
	}

	@Override
	public BigDecimal calculaPorcentagem() {
		if ((salario.getBruto().compareTo((new BigDecimal("0.0"))) > 0)  
			&& (salario.getBruto().compareTo((new BigDecimal("1317.07"))) <= 0)) {
			return manipulaProperties.buscaAliquota("inss.porcentagem.minimo");
		} else if (salario.getBruto().compareTo((new BigDecimal("1317.08"))) >= 0
				&& salario.getBruto().compareTo((new BigDecimal("2195.12"))) <= 0) {
			return manipulaProperties.buscaAliquota("inss.porcentagem.medio");
		} else if (salario.getBruto().compareTo((new BigDecimal("2195.13"))) >= 0
				&& salario.getBruto().compareTo((new BigDecimal("4390.24"))) <= 0) {
			return manipulaProperties.buscaAliquota("inss.porcentagem.maximo");
		} else if (salario.getBruto().compareTo(new BigDecimal("4390.24")) > 0){
			return manipulaProperties.buscaAliquota("inss.desconto.maximo");
		}else{
			return new  BigDecimal("0.0");
		}
	}

	@Override
	public BigDecimal calculaValorDesconto() {
		setPorcentagem(calculaPorcentagem());
		desconto = calculaLimite(getPorcentagem());
		return Calculadora.sub(salario.getBruto(), desconto);
	}

	private BigDecimal calculaLimite(BigDecimal porcentagem2) {
		if (getPorcentagem().compareTo(LIMITE) == 0) {
			return LIMITE;
		} else {
			return Calculadora.mult(salario.getBruto(), getPorcentagem());
		}
	}
	
	@Override
	public String toString() {
		return "Desconto: " + this.desconto + "Porcetagem: " +this.getPorcentagem();
	}
}
