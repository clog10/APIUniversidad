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
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
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
		when(carreraRepository.findCarrerasByNombreContains("Ingenieria"))
				.thenReturn(Arrays.asList(DatosDummy.carrera01(), DatosDummy.carrera03(), DatosDummy.carrera04()));

		List<Carrera> expected = (List<Carrera>) carreraDAO.findCarrerasByNombreContains("Ingenieria");

		assertThat(expected.get(0)).isEqualTo(DatosDummy.carrera01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.carrera03());
		assertThat(expected.get(2)).isEqualTo(DatosDummy.carrera04());

		verify(carreraRepository).findCarrerasByNombreContains("Ingenieria");
	}

	@Test
	@DisplayName("Test: Buscar carreras por coincidencia en el nombre no sensible a mayusculas y minusculas")
	void findCarrerasByNombreContainsIgnoreCase() {
		when(carreraRepository.findCarrerasByNombreContainsIgnoreCase("ingenieria"))
				.thenReturn(Arrays.asList(DatosDummy.carrera01(), DatosDummy.carrera03(), DatosDummy.carrera04()));

		List<Carrera> expected = (List<Carrera>) carreraDAO.findCarrerasByNombreContainsIgnoreCase("ingenieria");

		assertThat(expected.get(0)).isEqualTo(DatosDummy.carrera01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.carrera03());
		assertThat(expected.get(2)).isEqualTo(DatosDummy.carrera04());

		verify(carreraRepository).findCarrerasByNombreContainsIgnoreCase("ingenieria");
	}

	@Test
	@DisplayName("Test: Buscar carreras despues de n años")
	void findCarrerasByCantidadAniosAfter() {

		when(carreraRepository.findCarrerasByCantidadAniosAfter(4))
				.thenReturn(Arrays.asList(DatosDummy.carrera01(), DatosDummy.carrera03()));

		List<Carrera> expected = (List<Carrera>) carreraDAO.findCarrerasByCantidadAniosAfter(4);

		assertThat(expected.get(0)).isEqualTo(DatosDummy.carrera01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.carrera03());

		verify(carreraRepository).findCarrerasByCantidadAniosAfter(4);
	}

}
