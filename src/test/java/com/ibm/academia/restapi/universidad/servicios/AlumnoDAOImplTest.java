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
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.AlumnoRepository;

@SpringBootTest
public class AlumnoDAOImplTest {

	@Autowired
	private AlumnoDAO alumnoDao;

	@MockBean
	private AlumnoRepository alumnoRepository;

	@Test
	@DisplayName("Test: Buscar alumnos por el nombre de su carrera")
	void buscarAlumnosPorNombreCarrera() {

		Carrera carrera = DatosDummy.carrera01();
		Alumno alumno01 = (Alumno) DatosDummy.alumno01();
		alumno01.setCarrera(carrera);
		Alumno alumno02 = (Alumno) DatosDummy.alumno02();
		alumno02.setCarrera(carrera);
		Alumno alumno03 = (Alumno) DatosDummy.alumno03();
		alumno03.setCarrera(carrera);

		when(alumnoRepository.buscarAlumnosPorNombreCarrera(carrera.getNombre()))
				.thenReturn(Arrays.asList(alumno01, alumno02, alumno03));

		List<Persona> expected = (List<Persona>) alumnoDao.buscarAlumnosPorNombreCarrera(carrera.getNombre());

		assertThat(expected.get(0)).isEqualTo(DatosDummy.alumno01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.alumno02());
		assertThat(expected.get(2)).isEqualTo(DatosDummy.alumno03());

		verify(alumnoRepository).buscarAlumnosPorNombreCarrera(carrera.getNombre());
	}
}
