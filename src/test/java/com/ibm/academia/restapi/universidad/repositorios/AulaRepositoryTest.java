package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

@DataJpaTest
public class AulaRepositoryTest {

	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private PabellonRepository pabellonRepository;
	
	@BeforeEach
	void setUp() {
		aulaRepository.save(DatosDummy.aula01());
		aulaRepository.save(DatosDummy.aula02());
		aulaRepository.save(DatosDummy.aula03());
		aulaRepository.save(DatosDummy.aula04());
	}

	@AfterEach
	void tearDown() {
		aulaRepository.deleteAll();
	}
	
	@Test
	@DisplayName("Test: Buscar Aulas por el tipo de pizarrón")
	void findAulasByTipoPizarron() {
		Iterable<Aula> expected = aulaRepository.findAulasByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);
		
		assertThat(((List<Aula>) expected).size() == 2).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar Aulas por el tipo de pizarrón")
	void findAulasByPabellonNombre() {
		Pabellon pabellon = pabellonRepository.save(new Pabellon(null, 1000.00, "Ingenierias",
				new Direccion("Victor Ahuja", "150", "68000", "1", "1", "Oaxaca"), "Clog10"));
		Iterable<Aula> aulas = aulaRepository.findAll();
		
		Set<Aula> aulasPabellon = new HashSet<Aula>();
		aulas.forEach(aula->{
			aula.setPabellon(pabellon);
			aulasPabellon.add(aula);
			aulaRepository.save(aula);
		});
		
		pabellon.setAulas(aulasPabellon);
		pabellonRepository.save(pabellon);
		
		Iterable<Aula> expected = aulaRepository.findAulasByPabellonNombre("Ingenierias");
		
		assertThat(((List<Aula>) expected).size() == 4).isTrue();
	}
	
	@Test
	@DisplayName("Test: Buscar Aulas por numero de aula")
	void findAulaByNumeroAula() {
		Optional<Aula> expected = aulaRepository.findAulaByNumeroAula(2);
		
		assertThat(expected.isPresent());
	}
}
