package com.ibm.academia.restapi.universidad.servicios;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;
import com.ibm.academia.restapi.universidad.repositorios.ProfesorRepository;


@Service
public class ProfesorDAOImpl extends PersonaDAOImpl implements ProfesorDAO {
	
	@Autowired
	private CarreraDAO carreraDao;

	@Autowired
	public ProfesorDAOImpl(@Qualifier("repositorioProfesor") PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findProfesoresByCarrera(String carrera) {
		return ((ProfesorRepository) repository).findProfesoresByCarrera(carrera);
	}
	
	@Override
	@Transactional
	public Persona asociarCarreraProfesor(Long carreraId, Long profesorId) {
		Optional<Persona> oProfesor = repository.findById(profesorId);

		if (!oProfesor.isPresent())
			throw new NotFoundException(String.format("El profesor con id: %d no existe", profesorId));

		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);

		if (!oCarrera.isPresent())
			throw new NotFoundException(String.format("La carrera con id: %d no existe", carreraId));
		
		Set<Carrera> carreras = new HashSet<Carrera>();
		carreras.add(oCarrera.get());
		
		Set<Profesor> profesores = new HashSet<Profesor>();
		profesores.add((Profesor)oProfesor.get());
		
		((Profesor)oProfesor.get()).setCarreras(carreras);
		
		oCarrera.get().setProfesores(profesores);
		
		carreraDao.guardar(oCarrera.get());
		
		return repository.save(oProfesor.get());
	}
	
	@Override
	@Transactional
	public Persona actualizar(Long profesorId, Persona profesor) {
		Optional<Persona> oProfesor = repository.findById(profesorId);

		if (!oProfesor.isPresent())
			throw new NotFoundException(String.format("El profesor con id: %d no existe", profesorId));

		Persona profesorActualizado = null;

		oProfesor.get().setNombre(profesor.getNombre());
		oProfesor.get().setApellido(profesor.getApellido());
		oProfesor.get().setDireccion(profesor.getDireccion());
		((Profesor)oProfesor.get()).setSueldo((((Profesor)profesor).getSueldo()));
		profesorActualizado = repository.save(oProfesor.get());

		return profesorActualizado;
	}

}
