package br.com.wtcode.mobile.qtorecebo.util;

import java.math.BigDecimal;
import java.util.Properties;

public class ManipulaProperties {
	private Properties aliquotas;
	
	public ManipulaProperties(Properties aliquotas) {
		super();
		this.aliquotas = aliquotas;
	}
	
	public BigDecimal buscaAliquota(String aliquota){
		return new BigDecimal(aliquotas.getProperty(aliquota));
	}
}
