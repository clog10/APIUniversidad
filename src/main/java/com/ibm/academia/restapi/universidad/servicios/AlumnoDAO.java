package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface AlumnoDAO extends PersonaDAO{
	public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombreCarrera);
	
	public Persona actualizar(Long alumnoId, Persona alumno);
	
	public Persona asociarCarreraAlumno(Long carreraId, Long alumnoId);
}