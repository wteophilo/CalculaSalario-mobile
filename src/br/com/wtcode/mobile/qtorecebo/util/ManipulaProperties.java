package br.com.wtcode.mobile.qtorecebo.util;

import java.math.BigDecimal;
import java.util.Properties;

public class ManipulaProperties {
	private Properties aliquotas;
	private CarregaProperties carregaProperties = new CarregaProperties();
	
	public ManipulaProperties() {
		aliquotas =  carregaProperties.carregar();
	}
	
	public BigDecimal buscaAliquota(String aliquota){
		return new BigDecimal(aliquotas.getProperty(aliquota));
	}

}
