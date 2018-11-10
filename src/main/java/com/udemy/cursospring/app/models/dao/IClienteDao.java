package com.udemy.cursospring.app.models.dao;

import java.util.List;

import com.udemy.cursospring.app.models.entity.Cliente;

public interface IClienteDao {
	
	public List<Cliente> findAll();
	
	public void save(Cliente cliente);

}
