package com.udemy.cursospring.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.udemy.cursospring.app.models.entity.Producto;

public interface IProductoDao  extends CrudRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.nombre like %?1%") //Es una query al objeto no a la tabla de la bbdd, "?1" se refiere al primer parametro
	public List<Producto> findByNombre(String term);
	
	public List<Producto> findByNombreLikeIgnoreCase(String term);

}
