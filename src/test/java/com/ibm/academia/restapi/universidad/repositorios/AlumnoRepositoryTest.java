package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

@DataJpaTest
public class AlumnoRepositoryTest {

	@Autowired
	@Qualifier("repositorioAlumno")
	private PersonaRepository alumnoRepository;

	@Autowired
	private CarreraRepository carreraRepository;

	@BeforeEach
	void setUp() {
		alumnoRepository.save(DatosDummy.alumno01());
		alumnoRepository.save(DatosDummy.alumno02());
		alumnoRepository.save(DatosDummy.alumno03());
	}

	@AfterEach
	void tearDown() {
		alumnoRepository.deleteAll();
	}

	@Test
	@DisplayName("Test: Buscar alumnos por el nombre de su carrera")
	void buscarAlumnosPorNombreCarrera() {
		Carrera carrera = carreraRepository.save(DatosDummy.carrera01());

		Iterable<Persona> alumnos = alumnoRepository.findAll();

		alumnos.forEach(alumno -> {
			((Alumno) alumno).setCarrera(carrera);
			alumnoRepository.save(alumno);
		});

		List<Persona> expected = (List<Persona>) ((AlumnoRepository) alumnoRepository)
				.buscarAlumnosPorNombreCarrera(carrera.getNombre());

		assertThat(expected.size() == 3).isTrue();
	}

}
