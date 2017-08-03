package com.apirest.validation.locale;

import java.util.Locale;

import javax.inject.Inject;
import javax.validation.MessageInterpolator;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LocaleSpecificMessageInterpolator implements MessageInterpolator {

	@Inject
	private Logger logger;
	
	private final MessageInterpolator defaultInterpolator;
	
	public LocaleSpecificMessageInterpolator(MessageInterpolator interpolator) {
        this.defaultInterpolator = interpolator;
	}
	
	@Override
	public String interpolate(String message, Context context) {
		logger.log(Level.INFO, "Setando idioma "+LocaleThreadLocal.get()+" para mensagem de erro.");
		return defaultInterpolator.interpolate(message, context, LocaleThreadLocal.get());
	}

	@Override
	public String interpolate(String message, Context context, Locale locale) {
		return defaultInterpolator.interpolate(message, context, locale);
	}

}
