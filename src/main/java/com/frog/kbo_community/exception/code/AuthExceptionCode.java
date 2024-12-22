package com.frog.kbo_community.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthExceptionCode implements ExceptionCode  {
	INVALID_LOGIN_REQUEST(HttpStatus.BAD_REQUEST, "유효하지 않은 로그인 요청입니다.");

	private final HttpStatus httpStatus;
	private final String errMsg;
}
