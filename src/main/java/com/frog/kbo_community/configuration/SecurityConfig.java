package com.frog.kbo_community.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.frog.kbo_community.auth.RequestMatcherHolder;
import com.frog.kbo_community.auth.jwt.JwtAuthenticationFilter;

import static com.frog.kbo_community.domain.member.constant.PermissionEnum.*;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final RequestMatcherHolder requestMatcherHolder;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.formLogin(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			.addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(req ->
				req
					.requestMatchers(requestMatcherHolder.getRequestMatchersByMinPermission(null))
					.permitAll()
					.requestMatchers(requestMatcherHolder.getRequestMatchersByMinPermission(ADMIN))
					.hasAnyAuthority(ADMIN.name())
					.requestMatchers(requestMatcherHolder.getRequestMatchersByMinPermission(NORMAL))
					.hasAnyAuthority(ADMIN.name(), NORMAL.name())
					.anyRequest().authenticated()
			);

		return http.build();
	}
}
