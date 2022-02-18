package com.ibm.academia.restapi.universidad.modelo.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PabellonDTO implements Serializable{

	private Long pabellonId;
	private String nombre;
	private Double metrosCuadrados;
	private Date fechaCreacion;
	
	private static final long serialVersionUID = 7952995310310514940L;

	
}
