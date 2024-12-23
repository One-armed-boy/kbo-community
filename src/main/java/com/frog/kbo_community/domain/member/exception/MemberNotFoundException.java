package com.frog.kbo_community.domain.member.exception;

import com.frog.kbo_community.exception.DefinedException;
import com.frog.kbo_community.exception.code.MemberExceptionCode;

public class MemberNotFoundException extends DefinedException {
	public MemberNotFoundException() {
		super(MemberExceptionCode.MEMBER_NOT_FOUND);
	}
}
