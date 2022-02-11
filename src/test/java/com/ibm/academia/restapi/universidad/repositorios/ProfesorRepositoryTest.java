package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

@DataJpaTest
public class ProfesorRepositoryTest {

	@Autowired
	@Qualifier("repositorioProfesor")
	private PersonaRepository profesorRepository;

	@Autowired
	private CarreraRepository carreraRepository;

	@BeforeEach
	void setUp() {
		profesorRepository.save(DatosDummy.profesor01());
		profesorRepository.save(DatosDummy.profesor02());
	}

	@AfterEach
	void tearDown() {
		profesorRepository.deleteAll();
	}

	@Test
	@DisplayName("Test: Buscar profesores por carrera")
	void findProfesoresByCarrera() {
		Carrera carrera = carreraRepository.save(DatosDummy.carrera01());
		Set<Carrera> carreras = new HashSet<Carrera>();
		carreras.add(carrera);

		Iterable<Persona> profesores = profesorRepository.findAll();
		Set<Profesor> profes = new HashSet<Profesor>();

		profesores.forEach(profesor -> {
			((Profesor) profesor).setCarreras(carreras);
			profesorRepository.save(profesor);
			profes.add((Profesor) profesor);
		});

		carrera.setProfesores(profes);

		List<Persona> expected = (List<Persona>) ((ProfesorRepository) profesorRepository)
				.findProfesoresByCarrera(carrera.getNombre());

		assertThat(expected.size() == 2).isTrue();
	}
}
