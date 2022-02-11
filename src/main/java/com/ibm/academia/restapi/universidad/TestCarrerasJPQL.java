package com.ibm.academia.restapi.universidad;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.servicios.CarreraDAO;

@Component
public class TestCarrerasJPQL implements CommandLineRunner 
{
	@Autowired
	private CarreraDAO carreraDao;
	
	@Override
	public void run(String... args) throws Exception {
		/*List<Carrera> carreras = new ArrayList<Carrera>();
		carreras.add(new Carrera(null, "Ingenieria en Sistemas", 60, 5, "Clog10"));
		carreras.add(new Carrera(null, "Ingenieria Idustrial", 45, 5, "Clog10"));
		carreras.add(new Carrera(null, "Ingenieria en Electronica", 55, 5, "Clog10"));
		carreras.add(new Carrera(null, "Ingenieria Química", 60, 5, "Clog10"));
		carreras.add(new Carrera(null, "Ingenieria Alimentos", 40, 5, "Clog10"));
		
		Iterable<Carrera> carrerasIterable = carreras;
		
		Iterable<Carrera> carrerasGuardadas = carreraDao.guardarVarios(carrerasIterable);
		
		carrerasGuardadas.forEach(System.out::println);*/
		
		/*List<Carrera> carreras = (List<Carrera>) carreraDao.findCarrerasByNombreContains("Sistemas");
		carreras.forEach(System.out::println);*/
		
		/*List<Carrera> carrerasIgnoreCase = (List<Carrera>) carreraDao.findCarrerasByNombreContainsIgnoreCase("Sistemas");
		carrerasIgnoreCase.forEach(System.out::println);*/
		
		/*List<Carrera> carrerasAfter = (List<Carrera>) carreraDao.findCarrerasByCantidadAniosAfter(4);
		carrerasAfter.forEach(System.out::println);*/
	}
}
