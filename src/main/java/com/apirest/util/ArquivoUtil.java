package com.apirest.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ArquivoUtil {

	//FIXME ADICIONAR CLASSE DE LOG
	
	public static final Properties getProperties(String propertiesName){
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(propertiesName);
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
			//FIXME ADICIONAR CLASSE DE LOG
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
					//FIXME ADICIONAR CLASSE DE LOG
				}
			}
		}
		return prop;
	}
}
