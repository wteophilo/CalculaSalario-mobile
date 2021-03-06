package br.com.wtcode.mobile.qtorecebo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Properties;

import br.com.wtcode.mobile.qtorecebo.util.ManipulaProperties;

public class TabelaDescontoIrrf implements Serializable{
	private static final long serialVersionUID = 1L;
	private ManipulaProperties manipulaProperties;
	
	public TabelaDescontoIrrf(Properties aliquotas) {
		this.manipulaProperties = new ManipulaProperties(aliquotas);
	}

	public BigDecimal buscaParcelaDeducaoDoImposto(BigDecimal aliquota){
		if (aliquota.compareTo((new BigDecimal("0.075"))) <= 0) {
			return manipulaProperties.buscaAliquota("irrf.desconto.minimo");
		}else if(aliquota.compareTo((new BigDecimal("0.15"))) <= 0){
			return manipulaProperties.buscaAliquota("irrf.desconto.maisde2k");
		}else if(aliquota.compareTo((new BigDecimal("0.225"))) <= 0){
			return manipulaProperties.buscaAliquota("irrf.desconto.menosde4k");
		}else if(aliquota.compareTo((new BigDecimal("0.275"))) <= 0){
			return manipulaProperties.buscaAliquota("irrf.desconto.maximo");
		}else{
			return manipulaProperties.buscaAliquota("irrf.desconto.isento");
		}	
	}


}
