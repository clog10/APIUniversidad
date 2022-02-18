package com.ibm.academia.restapi.universidad.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.servicios.AlumnoDAO;
import com.ibm.academia.restapi.universidad.servicios.CarreraDAO;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;

@RestController
@RequestMapping("/restapi")
public class AlumnoController {
	
	private final static Logger logger = LoggerFactory.getLogger(AlumnoController.class);

	@Autowired
	@Qualifier("alumnoDAOImpl")
	private PersonaDAO alumnoDao;
	
	@Autowired
	private CarreraDAO carreraDao;

	/**
	 * Enpoint para guardar un alumno
	 * @param alumno
	 * @return Retorna el alumno guardado
	 * @author CJGL - 16-02-2022
	 */
	@PostMapping("/alumno")
	public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno) {
		Persona alumnoGuardado = alumnoDao.guardar(alumno);
		return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
	}

	/**
	 * Endpoint para buscar todos los alumnos
	 * @return Retorna una lista con los alumnos
	 * @exception NotFoundException En caso de que no existan alumnos
	 * @author CJGL - 16-02-2022
	 */
	@GetMapping("/alumnos/lista")
	public ResponseEntity<?> obtenerTodos() {
		List<Persona> alumnos = (List<Persona>) alumnoDao.buscarTodos();
		if (alumnos.isEmpty())
			throw new NotFoundException("No existen alumnos");

		return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
	}

	/**
	 * Endpoint para buscar un alumno por id
	 * @param alumnoId
	 * @return Retorna el alumno encontrado
	 * @exception NotFoundException En caso de que no existan alumnos
	 * @author CJGL - 16-02-2022
	 */
	@GetMapping("/alumno/{alumnoId}")
	public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Long alumnoId) {
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		if (!oAlumno.isPresent())
			throw new NotFoundException(String.format("El alumno con id: %d no existe", alumnoId));
		return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
	}

	/**
	 * Endpoint para eliminar un alumno por su id
	 * @param alumnoId
	 * @return @return Solo retorna el estatus
	 * @exception NotFoundException En caso de que falle buscando el alumno o no
	 *                              exista
	 * @author CJGL - 16-02-2022
	 */
	@DeleteMapping("/alumno/eliminar/alumnoId/{alumnoId}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long alumnoId) {
		Optional<Persona> oAlumno = alumnoDao.buscarPorId(alumnoId);
		if (!oAlumno.isPresent())
			throw new NotFoundException(String.format("El alumno con id: %d no existe", alumnoId));

		alumnoDao.eliminarPorId(oAlumno.get().getId());
		return new ResponseEntity<String>("El alumno con id: " + alumnoId + " se eliminó", HttpStatus.NO_CONTENT);
	}

	/**
	 * Endpoint para actualizar un alumno
	 * @param alumnoId
	 * @param alumno
	 * @param result
	 * @return Retorna la carrera actualizada
	 * @author CJGL - 16-02-2022
	 */
	@PutMapping("/alumno/actualizar/alumnoId/{alumnoId}")
	public ResponseEntity<?> actualizarAlumno(@PathVariable Long alumnoId, @RequestBody Persona alumno, BindingResult result) {
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		Persona alumnoActualizado = null;

		try {
			alumnoActualizado = ((AlumnoDAO) alumnoDao).actualizar(alumnoId, alumno);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}

		return new ResponseEntity<Persona>(alumnoActualizado, HttpStatus.OK);
	}

	/**
	 * Endpoint para asociar un alumno a una carrera
	 * @param carreraId
	 * @param alumnoId
	 * @return Retorna el alumno con su asociación
	 * @author CJGL - 16-02-2022
	 */
	@PutMapping("/alumno/asociar-carrera")
	public ResponseEntity<?> asignarCarrera(@RequestParam Long carreraId,
			@RequestParam(name = "alumno_id") Long alumnoId) {
		Persona alumno = ((AlumnoDAO) alumnoDao).asociarCarreraAlumno(carreraId, alumnoId);
		return new ResponseEntity<Persona>(alumno, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para buscar alumnos por el nombre de su carrera
	 * @param nombreCarrera
	 * @return Retorna una lista de alumnos que están asociados a esa carrera
	 * @exception NotFoundException En caso de que no existan alumnos asociados a esa carrera
	 * @author CJGL - 16-02-2022
	 */
	@GetMapping("/alumnos/nombreCarrera/{nombreCarrera}")
	public ResponseEntity<?> buscarPorNombreCarrera(@PathVariable String nombreCarrera){
		List<Carrera> carreras = (List<Carrera>) carreraDao.findCarrerasByNombreContainsIgnoreCase(nombreCarrera);
		if(carreras.isEmpty())
			throw new NotFoundException("No hay coincidencias para la carrera ingresada");

		List<Persona> alumnosPorCarrera = new ArrayList<Persona>();
		carreras.forEach(carrera->{
			alumnosPorCarrera.addAll((List<Persona>) ((AlumnoDAO)alumnoDao).buscarAlumnosPorNombreCarrera(nombreCarrera));
		});
		
		if (alumnosPorCarrera.isEmpty())
			throw new NotFoundException("No existen alumnos asociados a esa carrera");
		
		return new ResponseEntity<List<Persona>>(alumnosPorCarrera, HttpStatus.OK);
	}
}
