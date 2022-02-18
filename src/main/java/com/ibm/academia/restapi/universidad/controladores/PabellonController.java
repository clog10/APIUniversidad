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
import com.ibm.academia.restapi.universidad.modelo.dto.PabellonDTO;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.modelo.mapper.PabellonMapper;
import com.ibm.academia.restapi.universidad.servicios.PabellonDAO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/restapi")
public class PabellonController {

	private final static Logger logger = LoggerFactory.getLogger(CarreraController.class);

	@Autowired
	private PabellonDAO pabellonDao;

	/**
	 * Endpoint para consultar todos los pabellones
	 * 
	 * @return Retorna una lista de pabellones
	 * @author CJGL - 18-02-2022
	 */
	@ApiOperation(value = "Consultar todas las carreras")
	@ApiResponses({ @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
			@ApiResponse(code = 404, message = "No hay elementos en la bd") })
	@GetMapping("/pabellones/lista")
	public ResponseEntity<?> listarTodos() {
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();
		if (pabellones.isEmpty())
			throw new NotFoundException("No existen pabellones");
		return new ResponseEntity<List<Pabellon>>(pabellones, HttpStatus.OK);
	}

	/**
	 * Enpoint para consultar un pabellon por id
	 * 
	 * @param pabellonId
	 * @return Retorna un objeto de tipo pabellon
	 * @exception NotFoundException En caso de que falle buscando el pabellon o no
	 *                              exista
	 * @author CJGL - 18-02-2022
	 */
	@ApiOperation(value = "Consultar un pabellon por id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
			@ApiResponse(code = 404, message = "No hay elementos en la bd") })
	@GetMapping("/pabellon/pabellonId/{pabellonId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long pabellonId) {
		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);

		if (!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con id: %d no existe", pabellonId));

		return new ResponseEntity<Pabellon>(oPabellon.get(), HttpStatus.OK);
	}

	/**
	 * Endpoint para guardar un pabellon
	 * 
	 * @param pabellon
	 * @param result
	 * @return Retorna el pabellon guardado
	 * @author CJGL - 18-02-2022
	 */
	@ApiOperation(value = "Guardar Pabellon")
	@ApiResponses({ @ApiResponse(code = 201, message = "Endpoint ejecutado satisfactoriamente") })
	@PostMapping("/pabellon")
	public ResponseEntity<?> guardar(@Valid @RequestBody Pabellon pabellon, BindingResult result) {

		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}

		Pabellon pabellonGuardado = pabellonDao.guardar(pabellon);
		return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
	}

	/**
	 * Endpoint para eliminar un pabellon por id
	 * 
	 * @param pabellonId
	 * @return Solo retorna el estatus
	 * @exception NotFoundException En caso de que falle buscando el pabellon o no
	 *                              exista
	 * @author CJGL - 17-02-2022
	 */
	@ApiOperation(value = "Eliminar Pabellon")
	@ApiResponses({ @ApiResponse(code = 204, message = "Endpoint ejecutado satisfactoriamente") })
	@DeleteMapping("/pabellon/eliminar/pabellonId/{pabellonId}")
	public ResponseEntity<?> eliminar(@PathVariable Long pabellonId) {

		Optional<Pabellon> oPabellon = pabellonDao.buscarPorId(pabellonId);

		if (!oPabellon.isPresent())
			throw new NotFoundException(String.format("El pabellon con id: %d no existe", pabellonId));

		pabellonDao.eliminarPorId(oPabellon.get().getId());
		return new ResponseEntity<>("El pabellon con id: " + pabellonId + " fué eliminada", HttpStatus.NO_CONTENT);
	}

	/**
	 * EndPoint para actualizar pabellones
	 * 
	 * @param pabellonId
	 * @param pabellon
	 * @param result
	 * @return Retorna el pabellon actualizado
	 * @author CJGL - 15-02-2022
	 */
	@ApiOperation(value = "Actualizar Pabellon")
	@ApiResponses({ @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente") })
	@PutMapping("/pabellon/actualizar/pabellonId/{pabellonId}")
	public ResponseEntity<?> actualizar(@PathVariable Long pabellonId, @Valid @RequestBody Pabellon pabellon,
			BindingResult result) {
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}

		Pabellon pabellonActualizado = null;

		try {
			pabellonActualizado = pabellonDao.actualizar(pabellonId, pabellon);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}
		return new ResponseEntity<Pabellon>(pabellonActualizado, HttpStatus.OK);
	}

	/**
	 * Endpoint para consultar todos los pabellones mediante dto
	 * 
	 * @return Retorna un objeto de tipo PabellonDTO
	 * @exception NotFoundException En caso de que no existan pabellones
	 * @author CJGL - 18-02-2022
	 */
	@GetMapping("/pabellones/lista/dto")
	public ResponseEntity<?> consultarTodasPabellones() {
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDao.buscarTodos();

		if (pabellones.isEmpty())
			throw new NotFoundException("No existen pabellones en la base de datos");

		List<PabellonDTO> listaPabellones = pabellones.stream().map(PabellonMapper::mapPabellon)
				.collect(Collectors.toList());
		return new ResponseEntity<List<PabellonDTO>>(listaPabellones, HttpStatus.OK);
	}

}
