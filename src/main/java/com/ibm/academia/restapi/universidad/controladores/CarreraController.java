package com.ibm.academia.restapi.universidad.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.dto.CarreraDTO;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.mapper.CarreraMapper;
import com.ibm.academia.restapi.universidad.servicios.CarreraDAO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/restapi")
public class CarreraController {
	
	private final static Logger logger = LoggerFactory.getLogger(CarreraController.class);

	@Autowired
	private CarreraDAO carreraDao;

	/**
	 * Endpoint para consultar todas las carreras
	 * 
	 * @return Retorna una lista de carreras
	 *  @exception NotFoundException En caso de que falle buscando las carreras o no
	 *                              existan
	 * @author CJGL - 14-02-2022
	 */
	@ApiOperation(value = "Consultar todas las carreras")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
		@ApiResponse(code = 404, message = "No hay elementos en la bd")
	})
	@GetMapping("/carreras/lista")
	public ResponseEntity<?> listarTodas() {
		List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();
		
		if(carreras.isEmpty())
			throw new NotFoundException("No existen carreras en la base de datos");
		
		return new ResponseEntity<List<Carrera>>(carreras, HttpStatus.OK);
	}

	/**
	 * Enpoint para consultar una carrera por id
	 * 
	 * @param carreraId
	 * @return Retorna un objeto de tipo carrera
	 * @exception NotFoundException En caso de que falle buscando la carrera o no
	 *                              exista
	 * @author CJGL - 14-02-2022
	 */
	@GetMapping("/carrera/carreraId/{carreraId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long carreraId) {
		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);

		if (!oCarrera.isPresent())
			throw new NotFoundException(String.format("La carrera con id: %d no existe", carreraId));

		return new ResponseEntity<Carrera>(oCarrera.get(), HttpStatus.OK);
	}

	/**
	 * Endpoint para guardar una carrera
	 * 
	 * @param carrera
	 * @param result
	 * @return Retorna la carrera Guardada
	 * @author CJGL - 15-02-2022
	 */
	@PostMapping("/carrera")
	public ResponseEntity<?> guardar(@Valid @RequestBody Carrera carrera, BindingResult result) {

		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}

		Carrera carreraGuardada = carreraDao.guardar(carrera);
		return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
	}

	/**
	 * Endpoint para eliminar una carrera por id
	 * 
	 * @param carreraId
	 * @return Solo retorna el estatus
	 * @exception NotFoundException En caso de que falle buscando la carrera o no
	 *                              exista
	 * @author CJGL - 15-02-2022
	 */
	@DeleteMapping("/carrera/eliminar/carreraId/{carreraId}")
	public ResponseEntity<?> eliminar(@PathVariable Long carreraId) {

		Optional<Carrera> oCarrera = carreraDao.buscarPorId(carreraId);

		if (!oCarrera.isPresent())
			throw new NotFoundException(String.format("La carrera con id: %d no existe", carreraId));

		carreraDao.eliminarPorId(carreraId);
		return new ResponseEntity<>("La carrera con id: " + carreraId + " fué eliminada", HttpStatus.NO_CONTENT);
	}

	/**
	 * EndPoint para actualizar carreras
	 * 
	 * @param carreraId
	 * @param carrera
	 * @param result
	 * @return Retorna la carrera actualizada
	 * @author CJGL - 15-02-2022
	 */
	@PutMapping("/carrera/actualizar/carreraId/{carreraId}")
	public ResponseEntity<?> actualizar(@PathVariable Long carreraId, @Valid @RequestBody Carrera carrera,
			BindingResult result) {
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}

		Carrera carreraActualizada = null;

		try {
			carreraActualizada = carreraDao.actualizar(carreraId, carrera);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}

		return new ResponseEntity<Carrera>(carreraActualizada, HttpStatus.OK);
	}

	/**
	 * Endpoint para consultar todas las carreras mediante dto
	 * @return Retorna un objeto de tipo CarreraDTO
	 * @exception NotFoundException En caso de que no existan carreras
	 * @author CJGL - 16-02-2022
	 */
	@GetMapping("/carreras/lista/dto")
	public ResponseEntity<?> consultarTodasCarreras() {
		List<Carrera> carreras = (List<Carrera>) carreraDao.buscarTodos();

		if (carreras.isEmpty())
			throw new NotFoundException("No existen carreras en la base de datos");

		List<CarreraDTO> listaCarreras = carreras.stream().map(CarreraMapper::mapCarrera).collect(Collectors.toList());
		return new ResponseEntity<List<CarreraDTO>>(listaCarreras, HttpStatus.OK);
	}
}
