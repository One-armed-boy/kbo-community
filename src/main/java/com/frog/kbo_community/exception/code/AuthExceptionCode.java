package com.frog.kbo_community.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthExceptionCode implements ExceptionCode  {
	INVALID_LOGIN_REQUEST(HttpStatus.BAD_REQUEST, "유효하지 않은 로그인 요청입니다."),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 인증 정보입니다."),
	AUTH_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 기기 접속 정보입니다.");

	private final HttpStatus httpStatus;
	private final String errMsg;
}
