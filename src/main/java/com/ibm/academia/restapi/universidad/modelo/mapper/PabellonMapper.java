package com.ibm.academia.restapi.universidad.modelo.mapper;

import com.ibm.academia.restapi.universidad.modelo.dto.PabellonDTO;
import com.ibm.academia.restapi.universidad.modelo.entidades.Pabellon;

public class PabellonMapper {
	
	public static PabellonDTO mapPabellon(Pabellon pabellon) {
		PabellonDTO pabellonDto = new PabellonDTO();
		pabellonDto.setPabellonId(pabellon.getId());
		pabellonDto.setNombre(pabellon.getNombre());
		pabellonDto.setMetrosCuadrados(pabellon.getMetrosCuadrados());
		pabellonDto.setFechaCreacion(pabellon.getFechaCreacion());
		return pabellonDto;
	}

}
