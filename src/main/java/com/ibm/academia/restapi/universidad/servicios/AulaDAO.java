package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;

public interface AulaDAO extends GenericoDAO<Aula> {

	public Iterable<Aula> findAulasByTipoPizarron(TipoPizarron tipoPizarron);
	public Iterable<Aula> findAulasByPabellonNombre(String nombrePabellon);
	public Optional<Aula> findAulaByNumeroAula(Integer numeroAula);
}
