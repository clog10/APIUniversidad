package com.ibm.academia.restapi.universidad.datos;

import java.math.BigDecimal;

import com.ibm.academia.restapi.universidad.enumeradores.TipoEmpleado;
import com.ibm.academia.restapi.universidad.enumeradores.TipoPizarron;
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Aula;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Direccion;
import com.ibm.academia.restapi.universidad.modelo.entidades.Empleado;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;
import com.ibm.academia.restapi.universidad.modelo.entidades.Profesor;

public class DatosDummy {

	public static Persona alumno01() {
		return new Alumno(null, "Carlos", "Loaeza", "957522478", "Clog10",
				new Direccion("Av. Tecnológico", "205", "68050", "4", "4", "Oaxaca"));
	}

	public static Persona alumno02() {
		return new Alumno(null, "Valeria", "Zarate", "5714186", "Clog10",
				new Direccion("Reforma", "587", "68000", "4", "1", "Oaxaca"));
	}

	public static Persona alumno03() {
		return new Alumno(null, "Juanito", "Pérez", "687512", "Clog10",
				new Direccion("Constitucion", "86", "68000", "9", "7", "Oaxaca"));
	}

	public static Aula aula01() {
		return new Aula(null, 1, "10x20", 25, TipoPizarron.PIZARRON_BLANCO, "Clog10");
	}

	public static Aula aula02() {
		return new Aula(null, 2, "10x25", 30, TipoPizarron.PIZARRON_TIZA, "Clog10");
	}

	public static Aula aula03() {
		return new Aula(null, 3, "10x20", 25, TipoPizarron.PIZARRON_BLANCO, "Clog10");
	}

	public static Aula aula04() {
		return new Aula(null, 4, "10x25", 30, TipoPizarron.PIZARRON_TIZA, "Clog10");
	}

	public static Carrera carrera01() {
		return new Carrera(null, "Ingenieria en Sistemas Computacionales", 60, 5, "Clog10");
	}

	public static Carrera carrera02() {
		return new Carrera(null, "Licenciatura en Sistemas", 55, 4, "Clog10");
	}

	public static Carrera carrera03() {
		return new Carrera(null, "Ingenieria Quimica", 40, 5, "Clog10");
	}

	public static Carrera carrera04() {
		return new Carrera(null, "Ingenieria en Electronica", 50, 4, "Clog10");
	}

	public static Persona empleado01() {
		return new Empleado(null, "Edgar", "Loaeza", "587456", "Clog10",
				new Direccion("Benito Juarez", "5", "68000", "5", "1", "Oaxaca"), new BigDecimal("80000.00"),
				TipoEmpleado.ADMINISTRATIVO);
	}

	public static Persona empleado02() {
		return new Empleado(null, "Maria", "Loaeza", "5875521", "Clog10",
				new Direccion("Porfirio Diaz", "8", "68000", "7", "8", "Oaxaca"), new BigDecimal("50000.00"),
				TipoEmpleado.MANTENIMIENTO);
	}

	public static Pabellon pabellon01() {
		return new Pabellon(null, 1000.00, "Ingenierias",
				new Direccion("Victor Ahuja", "150", "68000", "1", "1", "Oaxaca"), "Clog10");
	}
	
	public static Pabellon pabellon02() {
		return new Pabellon(null, 1000.00, "Licenciaturas",
				new Direccion("Victor Ahuja", "150", "68000", "1", "2", "Oaxaca"), "Clog10");
	}

	public static Persona profesor01() {
		return new Profesor(null, "David", "Segura", "8745599", "Clog10",
				new Direccion("Ciega", "6", "95720", "1", "1", "Colombia"), new BigDecimal("100000.00"));
	}

	public static Persona profesor02() {
		return new Profesor(null, "Arturo", "Segura", "36872279", "Clog10",
				new Direccion("Arteaga", "4", "68900", "1", "1", "Tlalixtac"), new BigDecimal("90000.00"));
	}

}
