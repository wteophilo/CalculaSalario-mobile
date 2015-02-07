package br.com.wtcode.mobile.qtorecebo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Properties;

public class DescontoDependente extends Desconto implements Serializable{
	private static final long serialVersionUID = 1L;

	public DescontoDependente(Salario salario,Properties aliquotas) {
		super(salario,aliquotas);
	}
	
	private BigDecimal getValorDesconto() {
		return manipulaProperties.buscaAliquota("dependente.desconto");
	}

	@Override
	public BigDecimal calculaPorcentagem() {
		return new BigDecimal(0.0);
	}

	@Override
	public BigDecimal calculaValorDesconto() {
		setPorcentagem(calculaPorcentagem());
		if(salario.getNumeroDeDependentes() >0 && salario.getBruto().compareTo(new BigDecimal("2000.00"))>=0  ){
			salario.setLiquido(salario.getBruto().subtract(getValorDesconto()));
		}
		return salario.getBruto();
	}

}
