package com.udemy.cursospring.app.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * Es un mixing de la clase SimpleGrantedAuthority que lo que haces complementarla
 * en este caso poniendolo un constructor que admita un rol
 */
public abstract class SimpleGrantedAuthorityMixin {

	@JsonCreator
	public SimpleGrantedAuthorityMixin(@JsonProperty("authority") String role) {}
	
	

}
