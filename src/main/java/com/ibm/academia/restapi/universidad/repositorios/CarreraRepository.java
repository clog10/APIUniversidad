package com.ibm.academia.restapi.universidad.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;

@Repository
public interface CarreraRepository extends CrudRepository<Carrera, Long> {
	// @Query("select c from Carrera c where c.nombre like %?1%")
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre);

	// @Query("select c from Carrera c where upper(c.nombre) like upper(%?1%"))
	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);

	// @Query("select c from Carrera c where c.cantidadAnios > ?1)
	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);

	@Query("select c from Carrera c join fetch c.profesores p where p.nombre=?1 and p.apellido=?2")
	public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
}