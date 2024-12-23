package com.frog.kbo_community.exception.code;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {
	String name();

	HttpStatus getHttpStatus();

	String getErrMsg();
}

