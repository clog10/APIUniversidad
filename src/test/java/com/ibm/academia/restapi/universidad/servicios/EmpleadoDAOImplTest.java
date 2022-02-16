package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
		
		empleadoDao.findEmpleadoByTipoEmpleado(any());

		verify(empleadoRepository).findEmpleadoByTipoEmpleado(any());
	}

}
