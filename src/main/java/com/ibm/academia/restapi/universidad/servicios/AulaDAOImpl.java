package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.repositorios.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO {
	
	@Autowired
	private PabellonDAO pabellonDao;

	@Autowired
	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> findAulasByTipoPizarron(TipoPizarron tipoPizarron) {
		return repository.findAulasByTipoPizarron(tipoPizarron);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Aula> findAulasByPabellonNombre(String nombrePabellon) {
		return repository.findAulasByPabellonNombre(nombrePabellon);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Aula> findAulaByNumeroAula(Integer numeroAula) {
		return repository.findAulaByNumeroAula(numeroAula);
	}

	@Override
	@Transactional
	public Aula actualizar(Long aulaId, Aula aula) {
		Optional<Aula> oAula = repository.findById(aulaId);

		if (!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id: %d no existe", aulaId));

		Aula aulaActualizada = null;
		oAula.get().setNumeroAula(aula.getNumeroAula());
		oAula.get().setMedidas(aula.getMedidas());
		oAula.get().setCantidadPupitres(aula.getCantidadPupitres());
		oAula.get().setTipoPizarron(aula.getTipoPizarron());
		aulaActualizada = repository.save(oAula.get());

		return aulaActualizada;
	}

	@Override
	public Aula asociarPabellon(Long aulaId, Long pabellonId) {
		Optional<Aula> oAula = repository.findById(aulaId);
		if(!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id: %d no existe", aulaId));
		
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);
		if(!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con id: %d no existe", pabellonId));
		
		oAula.get().setPabellon(oPabellon.get());
		
		return repository.save(oAula.get());
	}

}
