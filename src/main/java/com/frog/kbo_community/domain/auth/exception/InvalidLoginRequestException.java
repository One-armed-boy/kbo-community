package com.frog.kbo_community.domain.auth.exception;

import com.frog.kbo_community.exception.DefinedException;
import com.frog.kbo_community.exception.code.AuthExceptionCode;
import com.frog.kbo_community.exception.code.MemberExceptionCode;

public class InvalidLoginRequestException extends DefinedException {
	public InvalidLoginRequestException() {
		super(AuthExceptionCode.INVALID_LOGIN_REQUEST);
	}
}
