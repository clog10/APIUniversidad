package com.ibm.academia.restapi.universidad.servicios;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;

@SpringBootTest
public class EmpleadoDAOImplTest {

	@Autowired
	private EmpleadoDAO empleadoDao;

	@MockBean
	private EmpleadoRepository empleadoRepository;

	@Test
	@DisplayName("Test: Buscar Empleado por su tipo")
	void findEmpleadoByTipoEmpleado() {
		when(empleadoRepository.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO))
				.thenReturn(Arrays.asList(DatosDummy.empleado01()));

		List<Persona> expected = (List<Persona>) empleadoDao.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);

		assertThat(expected.get(0)).isEqualTo(DatosDummy.empleado01());

		verify(empleadoRepository).findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
	}

}
