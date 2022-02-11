package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;

public interface CarreraDAO extends GenericoDAO<Carrera> {
	public Iterable<Carrera> findCarrerasByNombreContains(String nombre);

	public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);

	public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);

	public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
}