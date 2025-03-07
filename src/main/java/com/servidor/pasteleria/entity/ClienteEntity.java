package com.servidor.pasteleria.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CLIENTE")
public class ClienteEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CLIENTE", unique = true, nullable = false)
	private Long idCliente;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "APELLIDOS", nullable = false)
	private String apellidos;
	
	@Column(name = "DIRECCION", nullable = false)
	private String direccion;
	
	@Column(name = "TELEFONO", nullable = false)
	private Integer telefono;
	
	@Column(name = "EMAIL", nullable = false,unique=true)
	private String email;
	
	@Column(name = "USUARIO", nullable = false,unique=true)
	private String usuario;
	
	@Column(name = "CONTRASENIA", nullable = false)
	private String contrasenia;
	
	@OneToMany(mappedBy = "cliente")
	private Set<PedidoEntity> pedidos = new HashSet<>();
	
	

	public ClienteEntity() {
	
	}



	public Long getIdCliente() {
		return idCliente;
	}



	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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


	public Set<PedidoEntity> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<PedidoEntity> pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
