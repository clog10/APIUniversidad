package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
		genericoDao.buscarPorId(anyLong());

		verify(pabellonRepository).findById(anyLong());
	}

	@Test
	@DisplayName("Test: Guardar")
	void guardar() {

		genericoDao.guardar(any());

		verify(pabellonRepository).save(any());
	}

	@Test
	@DisplayName("Test: Buscar todos los elementos")
	void buscarTodos() {
		genericoDao.buscarTodos();

		verify(pabellonRepository).findAll();
	}

	@Test
	@DisplayName("Test: Guardar Varios")
	void guardarVarios() {
		genericoDao.guardarVarios(anyList());

		verify(pabellonRepository).saveAll(anyList());

	}
}
