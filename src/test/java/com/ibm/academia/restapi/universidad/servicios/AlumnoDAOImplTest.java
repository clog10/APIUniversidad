package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.repositorios.AlumnoRepository;

@SpringBootTest
public class AlumnoDAOImplTest {

	@Autowired
	private AlumnoDAO alumnoDao;

	@MockBean
	private AlumnoRepository alumnoRepository;

	@Test
	@DisplayName("Test: Buscar alumnos por el nombre de su carrera")
	void buscarAlumnosPorNombreCarrera() {

		alumnoDao.buscarAlumnosPorNombreCarrera(anyString());
		
		verify(alumnoRepository).buscarAlumnosPorNombreCarrera(anyString());
	}
}
