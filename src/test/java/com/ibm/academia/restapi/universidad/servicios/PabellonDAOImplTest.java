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
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
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
		when(pabellonRepository.findPabellonesByDireccionLocalidad("Oaxaca"))
				.thenReturn(Arrays.asList(DatosDummy.pabellon01()));

		List<Pabellon> expected = (List<Pabellon>) pabellonDao.findPabellonesByDireccionLocalidad("Oaxaca");

		assertThat(expected.get(0)).isEqualTo(DatosDummy.pabellon01());

		verify(pabellonRepository).findPabellonesByDireccionLocalidad("Oaxaca");
	}

	@Test
	@DisplayName("Test: Buscar pabellon por nombre")
	void findPabellonByNombre() {
		Optional<Pabellon> pabellon = Optional.of(DatosDummy.pabellon01());
		when(pabellonRepository.findPabellonByNombre("Ingenierias"))
		.thenReturn(pabellon);
		
		Optional<Pabellon> pabellonNombre = pabellonDao.findPabellonByNombre("Ingenierias");
		
		assertThat(pabellon.get().equals(pabellonNombre.get()));
		
		verify(pabellonRepository).findPabellonByNombre("Ingenierias");
	}
}











