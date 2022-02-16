package com.ibm.academia.restapi.universidad.servicios;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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
		 aulaDao.findAulasByTipoPizarron(any());

		verify(aulaRepository).findAulasByTipoPizarron(any());
	}

	@Test
	@DisplayName("Test: Buscar aulas por nombre del pabellon")
	void findAulasByPabellonNombre() {
		 aulaDao.findAulasByPabellonNombre(anyString());
		 
		verify(aulaRepository).findAulasByPabellonNombre(anyString());
	}

	@Test
	@DisplayName("Test: Buscar Aula por su numero")
	void findAulaByNumeroAula() {
		aulaDao.findAulaByNumeroAula(anyInt());

		verify(aulaRepository).findAulaByNumeroAula(anyInt());
	}

}
