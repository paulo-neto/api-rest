package com.apirest.resources.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Path;
import javax.validation.Path.Node;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<MethodConstraintViolationException> ,ConfiguraResponseExceptionHandler{

    @Override
    public Response toResponse(MethodConstraintViolationException ex) {
    	ErroMessage erro = null;
    	List<ErroMessage> erros = new ArrayList<>();
        for (MethodConstraintViolation<?> methodConstraintViolation : ex.getConstraintViolations()) {
        	String msg = getPropertyMesage(methodConstraintViolation);
        	String atributo = getPropertyName(methodConstraintViolation);
			erro = new ErroMessage(msg, "Bean Validation: atributo: ".concat(atributo).concat(": ").concat(msg));
			erros.add(erro);
        }
        return configurar(MediaType.APPLICATION_JSON_TYPE,erros, Status.PRECONDITION_FAILED);
    }

	/* (non-Javadoc)
	 * @see com.apirest.resources.handler.ConfiguraResponseExceptionHandler#configurar(javax.ws.rs.core.MediaType, java.lang.Object, javax.ws.rs.core.Response.Status)
	 */
	@Override
	public Response configurar(MediaType mediaType, List<ErroMessage> erros, Status statusHttp){
		return Response.status(statusHttp).type(mediaType).entity(erros).build();
	}
    
	private String getPropertyMesage(MethodConstraintViolation<?> methodConstraintViolation) {
		return methodConstraintViolation.getMessage();
		
	}

	private String getPropertyName(MethodConstraintViolation<?> methodConstraintViolation) {
		Path path = methodConstraintViolation.getPropertyPath();
		Iterator<Node> it = path.iterator();
		String propName = "";
		while(it.hasNext()){
			Node node = it.next();
			propName = node.getName();
		}
		return propName;
	}
}
