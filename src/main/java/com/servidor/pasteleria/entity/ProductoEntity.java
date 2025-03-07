package com.servidor.pasteleria.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
public class ProductoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUCTO", unique = true, nullable = false)
	private Long idProducto;

	@Column(name = "NOMBRE", nullable = false)
	private String nombre;

	@Column(name = "DESCRIPCION", nullable = false)
	private String descripcion;

	@Column(name = "PRECIO", nullable = false, precision = 10, scale = 2)
	private BigDecimal precio;

	@Column(name = "STOCK", nullable = false)
	private Integer stock;

	@Column(name = "CATEGORIA", nullable = false)
	private String categoria;

	@Lob
	@Column(name = "IMAGEN_PRODUCTO", nullable = true)
	private byte[] imagenProducto;

	@OneToMany(mappedBy = "producto")
	private Set<ProductoIngredienteEntity> ingredientes = new HashSet<>();
	 
	public ProductoEntity() {

	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

	public Set<ProductoIngredienteEntity> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Set<ProductoIngredienteEntity> ingredientes) {
		this.ingredientes = ingredientes;
	}

	public byte[] getImagenProducto() {
		return imagenProducto;
	}

	public void setImagenProducto(byte[] imagenProducto) {
		this.imagenProducto = imagenProducto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
