package com.apirest.resources.validation;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.spi.validation.ValidatorAdapter;

@Provider
public class RESTValidatorContextResolver implements ContextResolver<ValidatorAdapter> {

    private static final RESTValidatorAdapter adapter = new RESTValidatorAdapter();

    @Override
    public ValidatorAdapter getContext(Class<?> type) {
        return adapter;
    }
}
