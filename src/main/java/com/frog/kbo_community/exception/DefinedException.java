package com.frog.kbo_community.exception;

import com.frog.kbo_community.exception.code.ExceptionCode;

import lombok.Getter;

@Getter
public class DefinedException extends RuntimeException{
	private final ExceptionCode exceptionCode;

	protected DefinedException(ExceptionCode exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	protected DefinedException(ExceptionCode exceptionCode, String message) {
		super(message);
		this.exceptionCode = exceptionCode;
	}
}
