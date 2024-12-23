package com.frog.kbo_community.domain.auth.exception;

import com.frog.kbo_community.exception.DefinedException;
import com.frog.kbo_community.exception.code.AuthExceptionCode;

public class AuthInfoNotFoundException extends DefinedException {
	public AuthInfoNotFoundException() {
		super(AuthExceptionCode.AUTH_INFO_NOT_FOUND);
	}
}
