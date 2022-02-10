package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface AlumnoDAO extends PersonaDAO{
	public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombreCarrera);
}