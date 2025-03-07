package com.servidor.pasteleria.model;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class IngredienteDTO {
	private String nombre;
	private BigDecimal precio;
	private Integer stock;
	private String fabricante;
	private MultipartFile  imagenIngrediente;
	private String base64Imagen;

	public IngredienteDTO() {

	}


	public IngredienteDTO(String nombre, BigDecimal precio, Integer stock, String fabricante, String base64Imagen) {
		
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.fabricante = fabricante;
		this.base64Imagen = base64Imagen;
	}


	public IngredienteDTO(String nombre, BigDecimal precio, Integer stock, String fabricante,
			MultipartFile imagenIngrediente) {

		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.fabricante = fabricante;
		this.imagenIngrediente = imagenIngrediente;
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

	public MultipartFile getImagenIngrediente() {
		return imagenIngrediente;
	}

	public void setImagenIngrediente(MultipartFile imagenIngrediente) {
		this.imagenIngrediente = imagenIngrediente;
	}

	public String getBase64Imagen() {
		return base64Imagen;
	}



	public void setBase64Imagen(String base64Imagen) {
		this.base64Imagen = base64Imagen;
	}



}
