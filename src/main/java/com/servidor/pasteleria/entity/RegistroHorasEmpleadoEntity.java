package com.servidor.pasteleria.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "REGISTRO_HORAS_EMPLEADOS")
public class RegistroHorasEmpleadoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_REGISTRO_HORAS_EMPLEADOS", unique = true, nullable = false)
	private Long idRegistroHorasEmpleados;

	@ManyToOne
	@JoinColumn(name = "ID_EMPLEADO")
	private EmpleadoEntity empleado;

	// INSERTABLE FALSE PORQUE LA INSERCCION DE LA FECHA SE HACE DESDE BBDD
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_ENTRADA", nullable = false, insertable = false)
	private Timestamp horaEntrada;

	// ESTA HORA DE SALIDA SERA NULA EN EL MOMENTO QUE SE INSERTE LA HORA DE ENTRADA
	// Y SE LE DARA VALOR CUANDO EL EMPLEADO PULSE EL BOTON DE SALIDA
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HORA_SALIDA")
	private Timestamp horaSalida;
	
	

	public RegistroHorasEmpleadoEntity() {
		
	}

	public Long getIdRegistroHorasEmpleados() {
		return idRegistroHorasEmpleados;
	}

	public void setIdRegistroHorasEmpleados(Long idRegistroHorasEmpleados) {
		this.idRegistroHorasEmpleados = idRegistroHorasEmpleados;
	}

	public EmpleadoEntity getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoEntity empleado) {
		this.empleado = empleado;
	}

	public Timestamp getHoraEntrada() {
		return horaEntrada;
	}

	public void setHoraEntrada(Timestamp horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Timestamp getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Timestamp horaSalida) {
		this.horaSalida = horaSalida;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
