package com.ibm.academia.restapi.universidad.servicios;

import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

public interface ProfesorDAO extends PersonaDAO {

	public Iterable<Persona> findProfesoresByCarrera(String carrera);
}
