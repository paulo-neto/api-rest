/**
 * 
 */
package com.apirest.mesages;

import java.text.MessageFormat;
import java.util.Properties;

import com.apirest.util.ArquivoUtil;

/**
 * Classe que acessa as mensagens contidas no arquivo mesages.properties
 * @author Paulo Antonio
 */
public final class MesagesProperties{

	private static String mesagesProperties = "mesages.properties";
	private Properties properties;
	private static final MesagesProperties instancia = new MesagesProperties();
	
	private MesagesProperties(){
		getProperties();
	}
	
	public static MesagesProperties getInstancia() {
		return instancia;
	}

	protected Properties getProperties() {
		if (properties == null) {
			properties = ArquivoUtil.getProperties(mesagesProperties);
		}
		return properties;
	}
	
	public String getMesage(String key, String... argumentos){
		String msg =  properties.getProperty(key);
		return MessageFormat.format(msg, argumentos);
	}
}
