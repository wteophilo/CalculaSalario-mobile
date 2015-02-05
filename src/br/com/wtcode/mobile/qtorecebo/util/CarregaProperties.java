package br.com.wtcode.mobile.qtorecebo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CarregaProperties {
	
	private static final String FILE_NAME = "/aliquotas.properties";

	public Properties carregar() {
		Properties properties = new Properties();
		InputStream inputStream =  getClass().getResourceAsStream(FILE_NAME);
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}catch(NullPointerException e){
			throw new NullPointerException("Nenhum arquivo encontrado");
		}
		return properties;
	}
}
