package com.frog.kbo_community.domain.auth.exception;

import com.frog.kbo_community.exception.DefinedException;
import com.frog.kbo_community.exception.code.AuthExceptionCode;

public class InvalidTokenException extends DefinedException {
	public InvalidTokenException() {
		super(AuthExceptionCode.INVALID_TOKEN);
	}
}
