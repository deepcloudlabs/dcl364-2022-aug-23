package com.example.market.api.handler;

import javax.json.Json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.market.dto.RestErrorMessage;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<Exception>{

	@Override
	public Response toResponse(Exception e) {
		var restErrorMessage = new RestErrorMessage(e.getMessage());
		return Response.status(Response.Status.BAD_REQUEST)
				       .entity(toJson(restErrorMessage))
				       .type(MediaType.APPLICATION_JSON)
				       .build();
	}

	private String toJson(RestErrorMessage restErrorMessage) {
		return Json.createObjectBuilder()
				   .add("reason", restErrorMessage.getReason())
				   .build()
				   .toString();
	}

}
