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
@Table(name = "INGREDIENTE")
public class IngredienteEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_INGREDIENTE", unique = true, nullable = false)
	private Long idIngrediente;
	
	@Column(name = "NOMBRE", nullable = false)
	private String nombre;
	
	@Column(name = "PRECIO", nullable = false, precision = 10, scale = 2)
	private BigDecimal precio;
	
	@Column(name = "STOCK", nullable = false)
	private Integer stock;

	@Column(name = "FABRICANTE", nullable = false)
	private String fabricante;
	
	@Lob
	@Column(name = "IMAGEN_INGREDIENTE", nullable = true)
	private byte[] imagenIngrediente;
	
	
	@OneToMany(mappedBy = "ingrediente")
	private Set<ProductoIngredienteEntity> productos = new HashSet<>();
	 
	
	
	public IngredienteEntity() {
		
	}

	public Long getIdIngrediente() {
		return idIngrediente;
	}

	public void setIdIngrediente(Long idIngrediente) {
		this.idIngrediente = idIngrediente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public byte[] getImagenIngrediente() {
		return imagenIngrediente;
	}

	public void setImagenIngrediente(byte[] imagenIngrediente) {
		this.imagenIngrediente = imagenIngrediente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
}
