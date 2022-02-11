package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public class GenericoDAOImpl<E, R extends CrudRepository<E, Long>> implements GenericoDAO<E> {
	protected final R repository;

	public GenericoDAOImpl(R repository) {
		this.repository = repository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<E> buscarPorId(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public E guardar(E entidad) {
		return repository.save(entidad);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<E> buscarTodos() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional
	public Iterable<E> guardarVarios(Iterable<E> entities) {
		return repository.saveAll(entities);
	}
}