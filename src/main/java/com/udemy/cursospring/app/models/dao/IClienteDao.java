package com.udemy.cursospring.app.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.udemy.cursospring.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {


}
