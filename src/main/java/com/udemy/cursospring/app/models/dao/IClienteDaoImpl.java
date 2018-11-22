package com.udemy.cursospring.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.cursospring.app.models.entity.Cliente;

@Repository()
public class IClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true) // Solo permite la lectura en la BD
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente findOne(Long id) {

		return em.find(Cliente.class, id);
	}

	@Override
	@Transactional // Permite la escritura en la BD
	public void save(Cliente cliente) {
		if (cliente.getId() != null && cliente.getId() > 0) {
			em.merge(cliente); // Actualiza la informacion en la BBDD
		} else {
			em.persist(cliente);
		}

	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
