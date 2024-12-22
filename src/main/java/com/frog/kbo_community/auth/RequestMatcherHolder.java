package com.frog.kbo_community.auth;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.frog.kbo_community.domain.member.constant.PermissionEnum;

import jakarta.annotation.Nullable;

@Component
public class RequestMatcherHolder {

	private static final List<RequestInfo> REQUEST_INFO_LIST = List.of(
		// member
		new RequestInfo(HttpMethod.POST, "/members", null)
	);
	private final ConcurrentHashMap<String, RequestMatcher> reqMatcherCacheMap = new ConcurrentHashMap<>();

	/**
	 * if role == null, return permitAll Path
	 */
	public RequestMatcher getRequestMatchersByMinPermission(@Nullable PermissionEnum minPermission) {
		var key = getKeyByPermission(minPermission);
		if (!reqMatcherCacheMap.containsKey(key)) {
			var requestMatcherByMinRole = new OrRequestMatcher(REQUEST_INFO_LIST.stream()
				.filter(reqInfo -> Objects.equals(reqInfo.minPermission(), minPermission))
				.map(reqInfo -> new AntPathRequestMatcher(reqInfo.pattern(), reqInfo.method().name()))
				.toArray(AntPathRequestMatcher[]::new));
			reqMatcherCacheMap.put(key, requestMatcherByMinRole);
		}
		return reqMatcherCacheMap.get(key);
	}

	private String getKeyByPermission(@Nullable PermissionEnum minPermission) {
		if (minPermission == null) {
			return "VISITOR";
		}
		return minPermission.name();
	}

	private record RequestInfo(HttpMethod method, String pattern, PermissionEnum minPermission) {
	}
}

