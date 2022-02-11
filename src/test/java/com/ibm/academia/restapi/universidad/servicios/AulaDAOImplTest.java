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
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;

@SpringBootTest
public class AulaDAOImplTest {

	@Autowired
	private AulaDAO aulaDao;

	@MockBean
	private AulaRepository aulaRepository;

	@Test
	@DisplayName("Test: Buscar Aulas por tipo de pizarron")
	void findAulasByTipoPizarron() {
		when(aulaRepository.findAulasByTipoPizarron(TipoPizarron.PIZARRON_BLANCO))
				.thenReturn(Arrays.asList(DatosDummy.aula01(), DatosDummy.aula03()));

		List<Aula> expected = (List<Aula>) aulaDao.findAulasByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);

		assertThat(expected.get(0)).isEqualTo(DatosDummy.aula01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.aula03());

		verify(aulaRepository).findAulasByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);
	}

	@Test
	@DisplayName("Test: Buscar aulas por nombre del pabellon")
	void findAulasByPabellonNombre() {
		Pabellon pabellon = DatosDummy.pabellon01();

		Aula aula01 = DatosDummy.aula01();
		aula01.setPabellon(pabellon);
		Aula aula02 = DatosDummy.aula02();
		aula02.setPabellon(pabellon);
		Aula aula03 = DatosDummy.aula03();
		aula03.setPabellon(pabellon);
		Aula aula04 = DatosDummy.aula04();
		aula04.setPabellon(pabellon);

		when(aulaRepository.findAulasByPabellonNombre(pabellon.getNombre()))
				.thenReturn(Arrays.asList(aula01, aula02, aula03, aula04));

		List<Aula> expected = (List<Aula>) aulaDao.findAulasByPabellonNombre(pabellon.getNombre());

		assertThat(((List<Aula>) expected).size() == 4).isTrue();

		verify(aulaRepository).findAulasByPabellonNombre(pabellon.getNombre());
	}

	@Test
	@DisplayName("Test: Buscar Aula por su numero")
	void findAulaByNumeroAula() {
		Optional<Aula> aula04 = Optional.of(DatosDummy.aula04());

		when(aulaRepository.findAulaByNumeroAula(4)).thenReturn(aula04);

		Optional<Aula> expected = aulaDao.findAulaByNumeroAula(4);

		assertThat(aula04.get().equals(expected.get()));

		verify(aulaRepository).findAulaByNumeroAula(4);
	}

}
