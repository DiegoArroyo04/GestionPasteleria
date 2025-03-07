package com.servidor.pasteleria.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "EMPLEADO")
public class EmpleadoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPLEADO", unique = true, nullable = false)
	private Long idEmpleado;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "APELLIDOS", nullable = false)
	private String apellidos;

	@Column(name = "TELEFONO", nullable = false)
	private int telefono;

	// INSERTABLE FALSE PORQUE LA INSERCCION DE LA FECHA SE HACE DESDE BBDD
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA_CONTRATACION", nullable = false, insertable = false)
	private Timestamp fechaContratacion;

	@Column(name = "ROL", nullable = false)
	private String rol;

	@Column(name = "USUARIO", nullable = false, unique = true)
	private String usuario;

	@Column(name = "CONTRASENIA", nullable = false)
	private String contrasenia;

	// EVITA INICIALIZACION PEREZOSA
	@OneToMany(mappedBy = "empleado", fetch = FetchType.EAGER)
	private Set<RegistroHorasEmpleadoEntity> registrosHorasEmpleado = new HashSet<>();

	public EmpleadoEntity() {

	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public Timestamp getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Timestamp fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public Set<RegistroHorasEmpleadoEntity> getRegistrosHorasEmpleado() {
		return registrosHorasEmpleado;
	}

	public void setRegistrosHorasEmpleado(Set<RegistroHorasEmpleadoEntity> registrosHorasEmpleado) {
		this.registrosHorasEmpleado = registrosHorasEmpleado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
