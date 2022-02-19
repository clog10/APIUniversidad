package com.ibm.academia.restapi.universidad.servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.repositorios.EmpleadoRepository;
import com.ibm.academia.restapi.universidad.repositorios.PersonaRepository;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO {
	
	@Autowired
	private PabellonDAO pabellonDao;

	@Autowired
	public EmpleadoDAOImpl(@Qualifier("repositorioEmpleado") PersonaRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
		return ((EmpleadoRepository) repository).findEmpleadoByTipoEmpleado(tipoEmpleado);
	}

	@Override
	public Persona actualizar(Long empleadoId, Persona empleado) {
		Optional<Persona> oEmpleado = repository.findById(empleadoId);

		if (!oEmpleado.isPresent())
			throw new NotFoundException(String.format("El empleado con id: %d no existe", empleadoId));

		Persona empleadoActualizado = null;

		oEmpleado.get().setNombre(empleado.getNombre());
		oEmpleado.get().setApellido(empleado.getApellido());
		oEmpleado.get().setDireccion(empleado.getDireccion());
		((Empleado)oEmpleado.get()).setSueldo(((Empleado)empleado).getSueldo());
		((Empleado)oEmpleado.get()).setTipoEmpleado(((Empleado)empleado).getTipoEmpleado());
		empleadoActualizado = repository.save(oEmpleado.get());

		return empleadoActualizado;
	}

	@Override
	public Persona asociarPabellon(Long empleadoId, Long pabellonId) {
		Optional<Persona> oEmpleado = repository.findById(empleadoId);

		if (!oEmpleado.isPresent())
			throw new NotFoundException(String.format("El empleado con id: %d no existe", empleadoId));

		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);

		if (!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con id: %d no existe", pabellonId));
		
		((Empleado)oEmpleado.get()).setPabellon(oPabellon.get());
		
		return repository.save(oEmpleado.get());
	}

}
