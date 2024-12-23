package com.frog.kbo_community.auth;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

public interface FilterExceptionResolver<T extends RuntimeException> {
	public void setResponse(HttpServletResponse response, T ex) throws IOException;
}

