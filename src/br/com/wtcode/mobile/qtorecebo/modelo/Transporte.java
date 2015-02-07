package br.com.wtcode.mobile.qtorecebo.modelo;

import java.math.BigDecimal;
import java.util.Properties;

import br.com.wtcode.mobile.qtorecebo.util.Calculadora;

public class Transporte extends Desconto{
	private static final long serialVersionUID = 1L;
	
	public Transporte(Salario salario, Properties aliquotas) {
		super(salario,aliquotas);
	}

	@Override
	public BigDecimal calculaPorcentagem() {
		return manipulaProperties.buscaAliquota("transporte.porcentagem");
	}

	@Override
	public BigDecimal calculaValorDesconto() {
		setPorcentagem(calculaPorcentagem());
		desconto = Calculadora.mult(salario.getBruto(), getPorcentagem());
		return  Calculadora.sub(salario.getLiquido(), desconto);
	}
}