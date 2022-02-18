package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.AlumnoRepository;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;

@Service
public class AlumnoDAOImpl extends PersonaDAOImpl implements AlumnoDAO {

	@Autowired
	private CarreraDAO carreraDao;

	@Autowired
	public AlumnoDAOImpl(@Qualifier("repositorioAlumno") PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> buscarAlumnosPorNombreCarrera(String nombreCarrera) {
		return ((AlumnoRepository) repository).buscarAlumnosPorNombreCarrera(nombreCarrera);
	}

	@Override
	@Transactional
	public Persona actualizar(Long alumnoId, Persona alumno) {
		Optional<Persona> oAlumno = repository.findById(alumnoId);

		if (!oAlumno.isPresent())
			throw new NotFoundException(String.format("El alumno con id: %d no existe", alumnoId));

		Persona alumnoActualizado = null;

		oAlumno.get().setNombre(alumno.getNombre());
		oAlumno.get().setApellido(alumno.getApellido());
		oAlumno.get().setDireccion(alumno.getDireccion());
		alumnoActualizado = repository.save(oAlumno.get());

		return alumnoActualizado;
	}

	@Override
	@Transactional
	public Persona asociarCarreraAlumno(Long carreraId, Long alumnoId) {
		Optional<Persona> oAlumno = repository.findById(alumnoId);

		if (!oAlumno.isPresent())
			throw new NotFoundException(String.format("El alumno con id: %d no existe", alumnoId));

		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);

		if (!oCarrera.isPresent())
			throw new NotFoundException(String.format("La carrera con id: %d no existe", carreraId));
		
		((Alumno)oAlumno.get()).setCarrera(oCarrera.get());
		
		return repository.save(oAlumno.get());
	}
}