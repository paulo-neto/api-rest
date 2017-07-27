package com.apirest.service;

import com.apirest.mesages.MesagesProperties;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(Exception exc) {
		super(exc);
	}
	
	public ServiceException(String key){
		super(MesagesProperties.getInstancia().getMesage(key));
	}
	
	public ServiceException(String key, String... argumentos){
		super(MesagesProperties.getInstancia().getMesage(key,argumentos));
	}
	
	public ServiceException(String key, Throwable throwable){
		super(MesagesProperties.getInstancia().getMesage(key), throwable);
	}
	
	public ServiceException(String key, Throwable throwable, String... argumentos){
		super(MesagesProperties.getInstancia().getMesage(key,argumentos), throwable);
	}
}
