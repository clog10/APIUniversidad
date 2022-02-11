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
import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

@DataJpaTest
public class EmpleadoRepositoryTest {

	@Autowired
	@Qualifier("repositorioEmpleado")
	private PersonaRepository empleadoRepository;

	@BeforeEach
	void setUp() {
		empleadoRepository.save(DatosDummy.empleado01());
		empleadoRepository.save(DatosDummy.empleado02());
	}

	@AfterEach
	void tearDown() {
		empleadoRepository.deleteAll();
	}

	@Test
	@DisplayName("Test: Buscar empleados por su tipo")
	void findEmpleadoByTipoEmpleado() {

		List<Persona> expected = (List<Persona>) ((EmpleadoRepository) empleadoRepository)
				.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);

		assertThat(expected.size() == 1).isTrue();
	}
}
