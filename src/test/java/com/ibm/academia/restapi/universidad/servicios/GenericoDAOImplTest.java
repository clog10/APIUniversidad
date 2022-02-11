package com.ibm.academia.restapi.universidad.servicios;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
public class GenericoDAOImplTest {

	@Autowired
	private GenericoDAO<Pabellon> genericoDao;

	@MockBean
	private PabellonRepository pabellonRepository;

	@Test
	@DisplayName("Test: Buscar por ID")
	void buscarPorId() {
		Optional<Pabellon> pabellon = Optional.of(DatosDummy.pabellon01());

		when(pabellonRepository.findById(1L)).thenReturn(pabellon);

		Optional<Pabellon> expected = genericoDao.buscarPorId(1L);

		assertThat(expected.get().equals(pabellon.get()));

		verify(pabellonRepository).findById(1L);
	}

	@Test
	@DisplayName("Test: Guardar")
	void guardar() {
		when(pabellonRepository.save(DatosDummy.pabellon01())).thenReturn(DatosDummy.pabellon01());

		Pabellon expected = genericoDao.guardar(DatosDummy.pabellon01());

		assertThat(expected.equals(DatosDummy.pabellon01()));

		verify(pabellonRepository).save(DatosDummy.pabellon01());
	}

	@Test
	@DisplayName("Test: Buscar todos los elementos")
	void buscarTodos() {
		when(pabellonRepository.findAll()).thenReturn(Arrays.asList(DatosDummy.pabellon01(), DatosDummy.pabellon02()));

		List<Pabellon> expected = (List<Pabellon>) genericoDao.buscarTodos();

		assertThat(expected.get(0)).isEqualTo(DatosDummy.pabellon01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.pabellon02());
		assertThat(expected.size() == 2).isTrue();

		verify(pabellonRepository).findAll();
	}

	@Test
	@DisplayName("Test: Guardar Varios")
	void guardarVarios() {
		List<Pabellon> pabellones = new ArrayList<Pabellon>();
		pabellones.add(DatosDummy.pabellon01());
		pabellones.add(DatosDummy.pabellon02());
		when(pabellonRepository.saveAll(pabellones)).thenReturn(pabellones);

		List<Pabellon> expected = (List<Pabellon>) genericoDao.guardarVarios(pabellones);

		assertThat(expected.get(0)).isEqualTo(DatosDummy.pabellon01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.pabellon02());
		assertThat(expected.size() == 2).isTrue();

		verify(pabellonRepository).saveAll(pabellones);

	}
}
