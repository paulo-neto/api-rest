package com.pauloneto.apirest.resources.handler;

import com.pauloneto.apirest.resources.handler.dto.ErroMessage;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Provider
public class ConstrainViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<ErroMessage> messages = new ArrayList<>();
        for(Object c : Arrays.asList(e.getConstraintViolations().toArray()) ){
            messages.add(new ErroMessage(((ConstraintViolation) c).getMessage(),null));
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(messages).type(MediaType.APPLICATION_JSON)
                .build();
    }
}
