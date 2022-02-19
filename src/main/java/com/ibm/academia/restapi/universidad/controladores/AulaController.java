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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.excepciones.NotFoundException;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/restapi")
public class AulaController {

	private final static Logger logger = LoggerFactory.getLogger(AulaController.class);

	@Autowired
	private AulaDAO aulaDao;

	/**
	 * Endpoint para consultar todas las aulas
	 * 
	 * @return Retorna una lista de aulas
	 * @exception NotFoundException En caso de que falle buscando las aulas o no
	 *                              existan
	 * @author CJGL - 18-02-2022
	 */
	@ApiOperation(value = "Consultar todas las aulas")
	@ApiResponses({ @ApiResponse(code = 200, message = "Endpoint ejecutado satisfactoriamente"),
			@ApiResponse(code = 404, message = "No hay elementos en la bd") })
	@GetMapping("/aulas/lista")
	public ResponseEntity<?> listarTodas() {
		List<Aula> aulas = (List<Aula>) aulaDao.buscarTodos();

		if (aulas.isEmpty())
			throw new NotFoundException("No existen aulas en la base de datos");

		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
	}

	/**
	 * Enpoint para consultar una aula por id
	 * 
	 * @param aulaId
	 * @return Retorna un objeto de tipo aula
	 * @exception NotFoundException En caso de que falle buscando el aula o no
	 *                              exista
	 * @author CJGL - 18-02-2022
	 */
	@GetMapping("/aula/aulaId/{aulaId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long aulaId) {
		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);

		if (!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id: %d no existe", aulaId));

		return new ResponseEntity<Aula>(oAula.get(), HttpStatus.OK);
	}

	/**
	 * Endpoint para guardar una aula
	 * 
	 * @param carrera
	 * @param result
	 * @return Retorna el aula Guardada
	 * @author CJGL - 18-02-2022
	 */
	@PostMapping("/aula")
	public ResponseEntity<?> guardar(@Valid @RequestBody Aula aula, BindingResult result) {

		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}

		Aula aulaGuardada = aulaDao.guardar(aula);
		return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
	}

	/**
	 * EndPoint para actualizar aulas
	 * 
	 * @param aulaId
	 * @param aula
	 * @param result
	 * @return Retorna el aula actualizada
	 * @author CJGL - 18-02-2022
	 */
	@PutMapping("/aula/actualizar/aulaId/{aulaId}")
	public ResponseEntity<?> actualizar(@PathVariable Long aulaId, @Valid @RequestBody Aula aula,
			BindingResult result) {
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}

		Aula aulaActualizada = null;

		try {
			aulaActualizada = aulaDao.actualizar(aulaId, aula);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}

		return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para eliminar un aula por id
	 * 
	 * @param aulaId
	 * @return Solo retorna el estatus
	 * @exception NotFoundException En caso de que falle buscando el aula o no
	 *                              exista
	 * @author CJGL - 18-02-2022
	 */
	@DeleteMapping("/aula/eliminar/aulaId/{aulaId}")
	public ResponseEntity<?> eliminar(@PathVariable Long aulaId) {

		Optional<Aula> oAula = aulaDao.buscarPorId(aulaId);

		if (!oAula.isPresent())
			throw new NotFoundException(String.format("El aula con id: %d no existe", aulaId));

		aulaDao.eliminarPorId(aulaId);
		return new ResponseEntity<>("El aula con id: " + aulaId + " fué eliminada", HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Endpoint para buscar aulas por el tipo de pizarron
	 * @param tipoPizarron
	 * @return Retorna una lista de aulas
	 * @exception NotFoundException En caso de que falle buscando las aulas o no
	 *                              existan
	 * @author CJGL - 18-02-2022
	 */
	@GetMapping("/aulas/tipoPizarron/{tipoPizarron}")
	public ResponseEntity<?> buscarAulasPorTipoPizarron(@PathVariable TipoPizarron tipoPizarron){
		List<Aula> aulas = (List<Aula>) aulaDao.findAulasByTipoPizarron(tipoPizarron);
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas con ese tipo de pizarron");
			
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
	}
	
	/**
	 * Endpoint para buscar aulas por el nombre de pabellon asociado
	 * @param nombrePabellon
	 * @return Retorna una lista de aulas
	 * @exception NotFoundException En caso de que falle buscando las aulas o no
	 *                              existan
	 * @author CJGL - 18-02-2022
	 */
	@GetMapping("/aulas/nombrePabellon/{nombrePabellon}")
	public ResponseEntity<?> buscarAulasPorNombrePabellon(@PathVariable String nombrePabellon){
		List<Aula> aulas = (List<Aula>) aulaDao.findAulasByPabellonNombre(nombrePabellon);
		if(aulas.isEmpty())
			throw new NotFoundException("No existen aulas asociadas a ese pabellon");
			
		return new ResponseEntity<List<Aula>>(aulas, HttpStatus.OK);
	}
	
	/**
	 * Enpoint para buscar un aula por su número
	 * @param numeroAula
	 * @return Retorna el aula
	 * @exception NotFoundException En caso de que falle buscando el aula o no
	 *                              exista
	 * @author CJGL - 18-02-2022                          
	 */
	@GetMapping("/aula/numeroAula/{numeroAula}")
	public ResponseEntity<?> buscarAulaPorNumeroAula(@PathVariable Integer numeroAula){
		Optional<Aula> oAula = aulaDao.findAulaByNumeroAula(numeroAula);
		
		if(!oAula.isPresent())
			throw new NotFoundException("No existe aula con ese numero");
		
		return new ResponseEntity<Aula>(oAula.get(),HttpStatus.OK);
	}
	
	/**
	 * Endpoint para asociar un pabellon a una aula
	 * @param aulaId
	 * @param pabellonId
	 * @return Retorna el aula con su pabellon
	 * @author CJGL - 18-02-2022
	 */
	@PutMapping("/aula/asociar-pabellon")
	public ResponseEntity<?> asociarPabellon(@RequestParam Long aulaId, Long pabellonId){
		Aula aula = aulaDao.asociarPabellon(aulaId, pabellonId);
		return new ResponseEntity<Aula>(aula, HttpStatus.OK);
	}

}
