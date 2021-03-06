package com.udemy.cursospring.app.view.xml;

/*
 * ESTA CLASE ES UN WRAPER
 *  LOS WRAPER SON ENVOLTORIOS PARA LOS TIPOS PRIMITIVOS COMO INT, STRING BOUBLE... 
 *  Y NOS PERMITE TRATARLOS COMO OBJETOS
 */

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.udemy.cursospring.app.models.entity.Cliente;

@XmlRootElement(name="clientes")
public class ClienteList {
	
	@XmlElement(name="cliente")
	public List<Cliente> clientes;
	
	public ClienteList() {
		
	}
	
	public ClienteList(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public List<Cliente> getClientes(){
		return clientes;
	}
}
