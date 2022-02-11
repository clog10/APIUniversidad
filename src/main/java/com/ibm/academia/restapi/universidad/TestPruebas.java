package com.ibm.academia.restapi.universidad;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;
import com.ibm.academia.restapi.universidad.servicios.AulaDAO;
import com.ibm.academia.restapi.universidad.servicios.CarreraDAO;
import com.ibm.academia.restapi.universidad.servicios.EmpleadoDAO;
import com.ibm.academia.restapi.universidad.servicios.PabellonDAO;
import com.ibm.academia.restapi.universidad.servicios.PersonaDAO;
import com.ibm.academia.restapi.universidad.servicios.ProfesorDAO;

@Component
public class TestPruebas implements CommandLineRunner {

	@Autowired
	@Qualifier("profesorDAOImpl")
	private PersonaDAO personaDao;

	@Autowired
	private CarreraDAO carreraDao;

	@Autowired
	private PabellonDAO pabellonDao;
	
	@Autowired
	private EmpleadoDAO empleadoDao;
	
	@Autowired
	private AulaDAO aulaDao;

	@Override
	public void run(String... args) throws Exception {

		/*
		  Direccion direccionProfesorUno = new Direccion("Abasolo", "125", "68050",
		  "11", "2", "Oaxaca"); 
		  Persona profesorUno = new Profesor(null, "David",
		  "Segura", "55225478", "Clog10", direccionProfesorUno, new
		  BigDecimal(200000));
		  
		  Direccion direccionProfesorDos = new Direccion("Mina", "125", "68050", "11",
		  "2", "Oaxaca"); 
		  Persona profesorDos = new Profesor(null, "Juan", "Ordoñez",
		  "85475222", "Clog10", direccionProfesorDos, new BigDecimal(190000));
		  
		  personaDao.guardar(profesorUno); personaDao.guardar(profesorDos);
		 */

		/*
		  Carrera carrera = null; Optional<Carrera> oCarrera =
		  carreraDao.buscarPorId(4L); carrera = oCarrera.get();
		  
		  Set<Carrera> carreras = new HashSet<Carrera>(); carreras.add(carrera);
		  
		  Iterable<Persona> profesores = personaDao.buscarTodos();
		  
		  Set<Profesor> profes = new HashSet<Profesor>();
		  
		  profesores.forEach(profesor -> { 
		  	if(profesor instanceof Profesor) { 
		  		Profesor profe = (Profesor) profesor; 
		  		profes.add(profe); 
		  		profe.setCarreras(carreras);
		  		personaDao.guardar(profe); 
		  	} 
		  });
		  
		  carrera.setProfesores(profes);
		  
		  Carrera carreraProfesores = carreraDao.guardar(carrera);
		  
		  System.out.println(carreraProfesores.toString());
		 */

		/*
		  Carrera carrera = null; Optional<Carrera> oCarrera =
		  carreraDao.buscarPorId(4L); carrera = oCarrera.get();
		  System.out.println(carrera.toString());
		  
		  List<Persona> profesores = (List<Persona>)
		  ((ProfesorDAO)personaDao).findProfesoresByCarrera(carrera.getNombre());
		  
		  profesores.forEach(System.out::println);
		 */

		/*
		 Direccion direccionPabellon = new Direccion("Arteaga", "125", "68050", "11",
		 "2", "Oaxaca"); 
		 Pabellon pabellon = new Pabellon(null, 1000.00, "Sistemas",
		 direccionPabellon, "Clog10");
		 
		 Pabellon pabellonGuardado = pabellonDao.guardar(pabellon);
		 
		  System.out.println(pabellonGuardado.toString());
		 */

		/*Optional<Pabellon> pabellon = pabellonDao.buscarPorId(1L);

		Direccion direccionEmpleadoUno = new Direccion("Etla", "250", "68050", "2", "4", "Oaxaca");
		Persona empleadoUno = new Empleado(null, "Edgar", "Loaeza", "5844455", "Clog10", direccionEmpleadoUno,
				new BigDecimal(80000), TipoEmpleado.ADMINISTRATIVO);
		empleadoDao.guardar(empleadoUno);
		
		Direccion direccionEmpleadoDos = new Direccion("Carranza", "250", "68050", "8", "9", "Oaxaca");
		Persona empleadoDos = new Empleado(null, "Maria", "Loaeza", "85471255", "Clog10", direccionEmpleadoDos,
				new BigDecimal(80000), TipoEmpleado.MANTENIMIENTO);
		empleadoDao.guardar(empleadoDos);*/
		
		/*Iterable<Persona> empleados = empleadoDao.buscarTodos();
		empleados.forEach(empleado -> {
			if(empleado instanceof Empleado) {
				Empleado e = (Empleado) empleado;
				e.setPabellon(pabellon.get());
				empleadoDao.guardar(e);
			}
		});*/
		
		/*Iterable<Persona> empleadosAdministrativos = empleadoDao.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
		
		empleadosAdministrativos.forEach(System.out::println);*/
		
		/*Optional<Persona> profesor = personaDao.buscarPorId(4L);
		Iterable<Carrera> carrerasPorProfesor = carreraDao.buscarCarrerasPorProfesorNombreYApellido(profesor.get().getNombre(), profesor.get().getApellido());
		carrerasPorProfesor.forEach(System.out::println);*/
		
		/*Aula aulaUno = new Aula(null, 1, "10x20", 25, TipoPizarron.PIZARRON_BLANCO , "Clog10");
		Aula aulaDos = new Aula(null, 2, "10x20", 30, TipoPizarron.PIZARRON_BLANCO, "Clog10");
		Aula aulaTres = new Aula(null, 3, "10x20", 26, TipoPizarron.PIZARRON_TIZA, "Clog10");
		Aula aulaCuatro = new Aula(null, 4, "10x20", 25, TipoPizarron.PIZARRON_TIZA, "Clog10");
		
		aulaDao.guardar(aulaUno);
		aulaDao.guardar(aulaDos);
		aulaDao.guardar(aulaTres);
		aulaDao.guardar(aulaCuatro);*/
		
		/*Optional<Pabellon> pabellon = pabellonDao.buscarPorId(1L);
		Pabellon pabellonAulas = pabellon.get();
		
		Set<Aula> aulasPabellon = new HashSet<Aula>();
		
		Iterable<Aula> aulas = aulaDao.buscarTodos();
		aulas.forEach(aula->{
			aulasPabellon.add(aula);
			aula.setPabellon(pabellonAulas);
			aulaDao.guardar(aula);
		});
		
		pabellonAulas.setAulas(aulasPabellon);
		pabellonDao.guardar(pabellonAulas);*/
		
		/*Iterable<Aula> aulasPizarronBlanco = aulaDao.findAulasByTipoPizarron(TipoPizarron.PIZARRON_BLANCO);
		aulasPizarronBlanco.forEach(System.out::println);
		
		Iterable<Aula> aulasPizarronTiza = aulaDao.findAulasByTipoPizarron(TipoPizarron.PIZARRON_TIZA);
		aulasPizarronTiza.forEach(System.out::println);*/
		
		/*Optional<Pabellon> pabellon = pabellonDao.buscarPorId(1L);
		
		Iterable<Aula> aulasPabellon = aulaDao.findAulasByPabellonNombre(pabellon.get().getNombre());
		aulasPabellon.forEach(System.out::println);*/
		
		/*Optional<Aula> aulaPorNumero = aulaDao.findAulaByNumeroAula(1);
		System.out.println(aulaPorNumero.get().toString());*/
		
		/*Iterable<Pabellon> pabellonesLocalidad = pabellonDao.findPabellonesByDireccionLocalidad("Oaxaca");
		pabellonesLocalidad.forEach(System.out::println);*/
		
		/*Optional<Pabellon> pabellonPorNombre = pabellonDao.findPabellonByNombre("Sistemas");
		System.out.println(pabellonPorNombre.get().toString());*/
	}

}
