package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

@DataJpaTest
public class PabellonRepositoryTest {

	@Autowired
	private PabellonRepository pabellonRepository;

	@BeforeEach
	void setUp() {
		pabellonRepository.save(DatosDummy.pabellon01());
	}

	@AfterEach
	void tearDown() {
		pabellonRepository.deleteAll();
	}

	@Test
	@DisplayName("Test: Buscar Pabellones por localidad")
	void findPabellonesByDireccionLocalidad() {
		Iterable<Pabellon> expected = pabellonRepository.findPabellonesByDireccionLocalidad("Oaxaca");

		assertThat(((List<Pabellon>) expected).size() == 1).isTrue();
	}

	@Test
	@DisplayName("Test: Buspar Pabellon por nombre")
	void findPabellonByNombre() {
		Optional<Pabellon> expected = pabellonRepository.findPabellonByNombre("Ingenierias");
		
		assertThat(expected.isPresent());
	}
}
