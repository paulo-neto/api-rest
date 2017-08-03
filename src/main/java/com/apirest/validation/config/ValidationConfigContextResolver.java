package com.apirest.validation.config;

import javax.validation.BootstrapConfiguration;
import javax.validation.Configuration;
import javax.validation.Validation;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.plugins.validation.GeneralValidatorImpl;
import org.jboss.resteasy.spi.validation.GeneralValidator;

import com.apirest.validation.locale.LocaleSpecificMessageInterpolator;

@Provider
public class ValidationConfigContextResolver implements ContextResolver<GeneralValidator> {

	@Override
	public GeneralValidator getContext(Class<?> type) {
		Configuration<?> config = Validation.byDefaultProvider().configure();
		BootstrapConfiguration bootstrapConfiguration = config.getBootstrapConfiguration();

		config.messageInterpolator(new LocaleSpecificMessageInterpolator(
				Validation.byDefaultProvider().configure().getDefaultMessageInterpolator()));

		return new GeneralValidatorImpl(config.buildValidatorFactory(),
				bootstrapConfiguration.isExecutableValidationEnabled(),
				bootstrapConfiguration.getDefaultValidatedExecutableTypes());
	}
}
