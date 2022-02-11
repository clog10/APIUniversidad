package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

@DataJpaTest
public class PersonaRepositoryTest {
	@Autowired
	@Qualifier("repositorioAlumno")
	private PersonaRepository alumnoRepository;

	@Autowired
	@Qualifier("repositorioEmpleado")
	private PersonaRepository empleadoRepository;

	@Autowired
	@Qualifier("repositorioProfesor")
	private PersonaRepository profesorRepository;

	@Test
	@DisplayName("Test: Buscar persona por Nombre y Apellido")
	void buscarPorNombreYApellido() {
		Persona empleado01 = empleadoRepository.save(DatosDummy.empleado01());
		Persona empleado02 = empleadoRepository.save(DatosDummy.empleado02());

		Optional<Persona> expected = empleadoRepository.buscarPorNombreYApellido(DatosDummy.empleado01().getNombre(),
				DatosDummy.empleado01().getApellido());

		assertThat(expected.get()).isEqualTo(empleado01);
	}

	@Test
	@DisplayName("Test: Buscar persona por Dni")
	void buscarPorDni() {
		Persona alumno01 = alumnoRepository.save(DatosDummy.alumno01());
		Persona alumno02 = alumnoRepository.save(DatosDummy.alumno02());
		Persona alumno03 = alumnoRepository.save(DatosDummy.alumno03());

		Optional<Persona> expected = alumnoRepository.buscarPorDni(DatosDummy.alumno02().getDni());

		assertThat(expected.get()).isEqualTo(alumno02);
		assertThat(expected.get() instanceof Alumno);
	}

	@Test
	@DisplayName("Test: Buscar personas por Apellido")
	void buscarPersonaPorApellido() {
		profesorRepository.save(DatosDummy.profesor01());
		profesorRepository.save(DatosDummy.profesor02());

		List<Persona> expected = (List<Persona>) profesorRepository.buscarPersonaPorApellido("Segura");

		assertThat(expected.size() == 2).isTrue();
	}

}
