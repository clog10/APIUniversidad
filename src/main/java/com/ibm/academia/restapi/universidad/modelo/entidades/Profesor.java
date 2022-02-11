package com.ibm.academia.restapi.universidad.modelo.entidades;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
//@Table(name = "profesores", schema = "universidad")
@Table(name = "profesores")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Profesor extends Persona {
	@Column(name = "sueldo")
	private BigDecimal sueldo;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	// @JoinTable(name = "profesor_carrera", schema = "universidad", joinColumns =
	// @JoinColumn(name = "profesor_id"), inverseJoinColumns = @JoinColumn(name =
	// "carrera_id"))
	@JoinTable(name = "profesor_carrera", joinColumns = @JoinColumn(name = "profesor_id"), inverseJoinColumns = @JoinColumn(name = "carrera_id"))
	private Set<Carrera> carreras;

	public Profesor(Long id, String nombre, String apellido, String dni, String usuarioCreacion, Direccion direccion,
			BigDecimal sueldo) {
		super(id, nombre, apellido, dni, usuarioCreacion, direccion);
		this.sueldo = sueldo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("Profesor [sueldo=");
		builder.append(sueldo);
		builder.append("]");
		return builder.toString();
	}

	private static final long serialVersionUID = 951996504952495470L;
}