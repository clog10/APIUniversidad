package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.repositorios.PabellonRepository;

@SpringBootTest
public class PabellonDAOImplTest {

	@Autowired
	private PabellonDAO pabellonDao;

	@MockBean
	private PabellonRepository pabellonRepository;

	@Test
	@DisplayName("Test: Buscar pabellones por localidad")
	void findPabellonesByDireccionLocalidad() {
		pabellonDao.findPabellonesByDireccionLocalidad(anyString());

		verify(pabellonRepository).findPabellonesByDireccionLocalidad(anyString());
	}

	@Test
	@DisplayName("Test: Buscar pabellon por nombre")
	void findPabellonByNombre() {
		pabellonDao.findPabellonByNombre(anyString());

		verify(pabellonRepository).findPabellonByNombre(anyString());
	}
}
