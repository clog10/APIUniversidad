package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.repositorios.AlumnoRepository;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;
import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;

@SpringBootTest
public class PersonaDAOImplTest {

	@Autowired
	private ProfesorDAO profesorDao;

	@Autowired
	private AlumnoDAO alumnoDao;

	@Autowired
	private EmpleadoDAO empleadoDao;

	@MockBean
	private ProfesorRepository profesorRepository;

	@MockBean
	private AlumnoRepository alumnoRepository;

	@MockBean
	private EmpleadoRepository empleadoRepository;

	@Test
	@DisplayName("Test: Buscar Persona por nombre y apellido")
	void buscarPorNombreYApellido() {
		profesorDao.buscarPorNombreYApellido(anyString(), anyString());

		verify(profesorRepository).buscarPorNombreYApellido(anyString(), anyString());
	}

	@Test
	@DisplayName("Test: Buscar Persona por DNI")
	void buscarPorDni() {
		 alumnoDao.buscarPorDni(anyString());

		verify(alumnoRepository).buscarPorDni(anyString());
	}

	@Test
	@DisplayName("Test: Buscar Personas por apellido")
	void buscarPersonaPorApellido() {
		empleadoDao.buscarPersonaPorApellido(anyString());

		verify(empleadoRepository).buscarPersonaPorApellido(anyString());
	}

}
