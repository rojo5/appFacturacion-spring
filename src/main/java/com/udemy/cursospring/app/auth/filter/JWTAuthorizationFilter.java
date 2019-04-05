package com.udemy.cursospring.app.auth.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udemy.cursospring.app.auth.SimpleGrantedAuthoritiesMixin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	public static final SecretKey CLAVESECRETA = Keys.secretKeyFor(SignatureAlgorithm.HS512);

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader("Authorization");

		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		
		boolean validoToken;
		Claims token = null;
		
		
		try {
			
			token = Jwts.parser()
					.setSigningKey(CLAVESECRETA)
					.parseClaimsJws(header.replace("Bearer ", ""))
					.getBody();
			
			validoToken = true;
		} catch (JwtException e) {
			validoToken = false;
		}
		
		UsernamePasswordAuthenticationToken authentication = null;
		
		if(validoToken) {
			String  username = token.getSubject();
			Object roles =  token.get("authorities");
			
			Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
					.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthoritiesMixin.class )
					.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
			
			authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

	}

	protected boolean requiresAuthentication(String header) {

		if (header == null || !header.startsWith("Bearer ")) {
			return false;
		}
		return true;
	}
}
