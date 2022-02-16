package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.repositorios.CarreraRepository;

@SpringBootTest
public class CarreraDAOImplTest {

	@Autowired
	CarreraDAO carreraDAO;

	@MockBean
	CarreraRepository carreraRepository;

	@Test
	@DisplayName("Test: Buscar carrera por coincidencia en el nombre")
	void findCarrerasByNombreContains() {
		 carreraDAO.findCarrerasByNombreContains(anyString());

		verify(carreraRepository).findCarrerasByNombreContains(anyString());
	}

	@Test
	@DisplayName("Test: Buscar carreras por coincidencia en el nombre no sensible a mayusculas y minusculas")
	void findCarrerasByNombreContainsIgnoreCase() {
		carreraDAO.findCarrerasByNombreContainsIgnoreCase(anyString());

		verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase(anyString());
	}

	@Test
	@DisplayName("Test: Buscar carreras despues de n años")
	void findCarrerasByCantidadAniosAfter() {

		carreraDAO.findCarrerasByCantidadAniosAfter(anyInt());

		verify(carreraRepository).findCarrerasByCantidadAniosAfter(anyInt());
	}

}
