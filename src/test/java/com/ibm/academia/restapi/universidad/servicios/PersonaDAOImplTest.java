package com.ibm.academia.restapi.universidad.servicios;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
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
		Optional<Persona> persona = Optional.of(DatosDummy.profesor01());
		String nombre = "David";
		String apellido = "Segura";

		when(profesorRepository.buscarPorNombreYApellido(nombre, apellido)).thenReturn(persona);

		Optional<Persona> expected = profesorDao.buscarPorNombreYApellido(nombre, apellido);

		assertThat(expected.get().equals(persona.get()));

		verify(profesorRepository).buscarPorNombreYApellido(nombre, apellido);
	}

	@Test
	@DisplayName("Test: Buscar Persona por DNI")
	void buscarPorDni() {
		Optional<Persona> persona = Optional.of(DatosDummy.alumno01());
		String dni = "5714186";

		when(alumnoRepository.buscarPorDni(dni)).thenReturn(persona);

		Optional<Persona> expected = alumnoDao.buscarPorDni(dni);

		assertThat(expected.get().equals(persona.get()));

		verify(alumnoRepository).buscarPorDni(dni);
	}

	@Test
	@DisplayName("Test: Buscar Personas por apellido")
	void buscarPersonaPorApellido() {
		String apellido = "Loaeza";
		when(empleadoRepository.buscarPersonaPorApellido(apellido))
				.thenReturn(Arrays.asList(DatosDummy.empleado01(), DatosDummy.empleado02()));

		List<Persona> expected = (List<Persona>) empleadoDao.buscarPersonaPorApellido(apellido);

		assertThat(expected.get(0)).isEqualTo(DatosDummy.empleado01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.empleado02());
		assertThat(expected.size() == 2).isTrue();

		verify(empleadoRepository).buscarPersonaPorApellido(apellido);
	}

}
