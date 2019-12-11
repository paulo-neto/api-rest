package com.pauloneto.apirest.resources.handler;

import com.pauloneto.apirest.resources.handler.dto.ErroMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception e) {
        List<ErroMessage> messages = new ArrayList<>();
        messages.add(new ErroMessage(ExceptionUtils.getMessage(e),ExceptionUtils.getStackTrace(e)));
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(messages).build();
    }
}
