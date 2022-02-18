package com.ibm.academia.restapi.universidad.modelo.mapper;

import com.ibm.academia.restapi.universidad.modelo.dto.CarreraDTO;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;

public class CarreraMapper {

	public static CarreraDTO mapCarrera(Carrera carrera) {
		CarreraDTO carreraDto = new CarreraDTO();
		carreraDto.setCarreraId(carrera.getId());
		carreraDto.setNombre(carrera.getNombre());
		carreraDto.setCantidadAnios(carrera.getCantidadAnios());
		carreraDto.setCantidadMaterias(carrera.getCantidadMaterias());
		carreraDto.setFechaCreacion(carrera.getFechaCreacion());
		return carreraDto;
	}
}
