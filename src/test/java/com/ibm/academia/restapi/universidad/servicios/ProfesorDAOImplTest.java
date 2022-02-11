package com.ibm.academia.restapi.universidad.servicios;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;
import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;

@SpringBootTest
public class ProfesorDAOImplTest {

	@Autowired
	private ProfesorDAO profesorDao;

	@MockBean
	private ProfesorRepository profesorRepository;

	@Test
	@DisplayName("Test: Buscar profesores por carrera")
	void findProfesoresByCarrera() {
		Carrera carrera01 = DatosDummy.carrera01();
		Carrera carrera02 = DatosDummy.carrera02();

		Set<Carrera> carreras = new HashSet<Carrera>();
		carreras.add(carrera01);
		carreras.add(carrera02);

		Set<Profesor> profesores = new HashSet<Profesor>();

		Profesor profesor01 = (Profesor) DatosDummy.profesor01();
		profesor01.setCarreras(carreras);
		profesores.add(profesor01);
		Profesor profesor02 = (Profesor) DatosDummy.profesor02();
		profesor02.setCarreras(carreras);
		profesores.add(profesor02);

		carrera01.setProfesores(profesores);
		carrera02.setProfesores(profesores);

		when(profesorRepository.findProfesoresByCarrera(carrera01.getNombre()))
				.thenReturn(Arrays.asList(profesor01, profesor02));

		List<Persona> expected = (List<Persona>) profesorDao.findProfesoresByCarrera(carrera01.getNombre());

		assertThat(expected.get(0)).isEqualTo(profesor01);
		assertThat(expected.get(1)).isEqualTo(profesor02);
		assertThat(expected.size() == 2).isTrue();

		verify(profesorRepository).findProfesoresByCarrera(carrera01.getNombre());

	}

}
