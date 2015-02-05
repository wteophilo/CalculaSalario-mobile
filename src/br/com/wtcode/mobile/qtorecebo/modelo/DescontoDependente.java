package br.com.wtcode.mobile.qtorecebo.modelo;

import java.math.BigDecimal;

import br.com.wtcode.mobile.qtorecebo.util.ManipulaProperties;

public class DescontoDependente{
	
	private ManipulaProperties manipulaProperties = new ManipulaProperties();
	
	public Salario calculaDesconto(Salario salario){
		if(salario.getNumeroDeDependentes() >0 && salario.getLiquido().compareTo(new BigDecimal("2000.00"))>=0  ){
			salario.setLiquido(salario.getLiquido().subtract(getValorDesconto()));
		}
		return salario;
	}

	private BigDecimal getValorDesconto() {
		return manipulaProperties.buscaAliquota("dependente.desconto");
	}

}
