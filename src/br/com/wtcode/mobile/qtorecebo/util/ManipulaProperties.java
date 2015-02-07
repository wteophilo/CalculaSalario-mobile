package br.com.wtcode.mobile.qtorecebo.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Properties;

public class ManipulaProperties implements Serializable {
	private static final long serialVersionUID = 1L;
	private Properties aliquotas;
	
	public ManipulaProperties(Properties aliquotas) {
		super();
		this.aliquotas = aliquotas;
	}
	
	public BigDecimal buscaAliquota(String aliquota){
		return new BigDecimal(aliquotas.getProperty(aliquota));
	}
}
