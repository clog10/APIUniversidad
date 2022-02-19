package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface ProfesorDAO extends PersonaDAO {

	public Iterable<Persona> findProfesoresByCarrera(String carrera);
	
	public Persona actualizar(Long profesorId, Persona profesor);
	
	public Persona asociarCarreraProfesor(Long carreraId, Long profesorId);
}
