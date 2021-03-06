package br.com.wtcode.mobile.qtorecebo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Properties;

import br.com.wtcode.mobile.qtorecebo.util.Calculadora;

public class Irrf  extends Desconto implements Serializable{
	private static final long serialVersionUID = 1L;
	private TabelaDescontoIrrf tabelaDescontoIrrf;
	
	public Irrf(Salario salario,Properties aliquotas) {
		super(salario,aliquotas);
		tabelaDescontoIrrf = new TabelaDescontoIrrf(aliquotas);
	}	
	
	@Override
	public BigDecimal calculaPorcentagem() {
		if (salario.getLiquido().compareTo(new BigDecimal("1787.77")) <= 0) {
			return manipulaProperties.buscaAliquota("irrf.porcentagem.isento");
		} else if (salario.getLiquido().compareTo((new BigDecimal("1787.78"))) >= 0
				&& salario.getLiquido().compareTo((new BigDecimal("2679.29"))) <= 0) {
			return manipulaProperties.buscaAliquota("irrf.porcentagem.minimo");
		} else if (salario.getLiquido().compareTo((new BigDecimal("2679.30"))) >= 0
				&& salario.getLiquido().compareTo((new BigDecimal("3572.43"))) <= 0) {
			return manipulaProperties.buscaAliquota("irrf.porcentagem.maisde2k");
		} else if (salario.getLiquido().compareTo((new BigDecimal("3572.44"))) >= 0
				&& salario.getLiquido().compareTo((new BigDecimal("4463.81"))) <= 0) {
			return manipulaProperties.buscaAliquota("irrf.porcentagem.menosde4k");
		} else {
			return manipulaProperties.buscaAliquota("irrf.porcentagem.maximo");
		}
	}

	@Override
	public BigDecimal calculaValorDesconto() {
		setPorcentagem(calculaPorcentagem());
		desconto = Calculadora.mult(salario.getLiquido(), getPorcentagem());
		desconto = Calculadora.sub(desconto, tabelaDescontoIrrf.buscaParcelaDeducaoDoImposto(getPorcentagem()));
		if(desconto.compareTo(new BigDecimal("1")) < 0) desconto =  new BigDecimal("0.00");
		return  Calculadora.sub(salario.getLiquido(), desconto);
	}
	
	@Override
	public String toString() {
		return "Desconto: " + this.desconto + "Porcetagem: " +this.getPorcentagem();
	}

}
