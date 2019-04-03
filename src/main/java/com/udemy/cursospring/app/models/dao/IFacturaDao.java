package com.udemy.cursospring.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.udemy.cursospring.app.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{
	//?1 significa el primer argumento
	@Query("select f from Factura f  join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id=?1")
	public Factura fetchByIdWithClienteWithItemFacturaWithProducto(long id);

}
