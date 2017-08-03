package com.apirest.validation.locale;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class AcceptLanguageRequestFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (!requestContext.getAcceptableLanguages().isEmpty()) {
			LocaleThreadLocal.set(requestContext.getAcceptableLanguages().get(0));
		}
	}
}
