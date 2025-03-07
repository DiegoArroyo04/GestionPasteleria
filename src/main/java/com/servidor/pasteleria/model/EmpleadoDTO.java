package com.servidor.pasteleria.model;

import java.sql.Timestamp;



public class EmpleadoDTO {
	private Long id;
	private String nombre;
	private String apellidos;
	private Integer telefono;
	private Timestamp fechaContratacion;
	private String rol;
	private String usuario;
	private String contrasenia;
	
	

	public EmpleadoDTO() {

	}
	
	public EmpleadoDTO(Long id, String nombre, String apellidos, Integer telefono, Timestamp fechaContratacion,
			String rol, String usuario, String contrasenia) {
	
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.fechaContratacion = fechaContratacion;
		this.rol = rol;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		
	}
	
	
	
	public EmpleadoDTO(String nombre, String apellidos, Integer telefono, String rol, String usuario, String contrasenia) {
	
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.rol = rol;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
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
	
	
}
