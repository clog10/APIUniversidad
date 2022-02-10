package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

public interface GenericoDAO<E> 
{
	public Optional<E> buscarPorId(Long id);
	public E guardar(E entidad);
	public Iterable<E> buscarTodos();
	public void eliminarPorId(Long id);
}
