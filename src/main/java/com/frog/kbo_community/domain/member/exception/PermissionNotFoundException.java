package com.frog.kbo_community.domain.member.exception;

import com.frog.kbo_community.exception.DefinedException;
import com.frog.kbo_community.exception.code.MemberExceptionCode;

public class PermissionNotFoundException extends DefinedException {
	public PermissionNotFoundException() {
		super(MemberExceptionCode.PERMISSION_NOT_FOUND);
	}
}
