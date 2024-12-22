package com.frog.kbo_community.domain.member.exception;

import com.frog.kbo_community.exception.DefinedException;
import com.frog.kbo_community.exception.code.MemberExceptionCode;

public class DuplicatedEmailException extends DefinedException {
	public DuplicatedEmailException() {
		super(MemberExceptionCode.DUPLICATED_EMAIL);
	}
}
