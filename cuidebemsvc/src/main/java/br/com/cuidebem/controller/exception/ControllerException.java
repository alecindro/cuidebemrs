package br.com.cuidebem.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(ControllerException.class);
	public ControllerException(String message){
		super(message);
		logger.error(message);
		
	}
	
	public ControllerException(String message,Throwable cause){
		super(message,cause);
		logger.error(message,cause);
	}

	
	
	
	
	
}
