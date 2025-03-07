package com.servidor.pasteleria.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.servidor.pasteleria.entity.ProductoIngredienteEntity;

public class ProductoDTO {
	private String nombre;
	private String descripcion;
	private BigDecimal precio;
	private Integer stock;
	private String categoria;
	private MultipartFile imagenProducto;
	private String base64Imagen;
	private List<IngredienteProductoDTO> ingredientes = new ArrayList<>();
	private Set<ProductoIngredienteEntity> ingredientesSet = new HashSet<>();
	private Long id;
	private Integer cantidadProducto;
	
	
	public ProductoDTO() {

	}

	


	public ProductoDTO(String nombre, String descripcion, BigDecimal precio, Integer stock, String categoria,
			String base64Imagen, Set<ProductoIngredienteEntity> ingredientesSet, Long id) {

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.categoria = categoria;
		this.base64Imagen = base64Imagen;
		this.ingredientesSet = ingredientesSet;
		this.id = id;
	}




	public ProductoDTO(String nombre, String descripcion, BigDecimal precio, Integer stock, String categoria,
			String base64Imagen, Long id) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.categoria = categoria;
		this.base64Imagen = base64Imagen;
		this.id = id;
	}




	public ProductoDTO(String nombre, String descripcion, BigDecimal precio, Integer stock, String categoria,
			MultipartFile imagenProducto, List<IngredienteProductoDTO> ingredientes) {

		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.categoria = categoria;
		this.imagenProducto = imagenProducto;
		this.ingredientes = ingredientes;
	}
	
	

	public ProductoDTO(String nombre, String descripcion, BigDecimal precio, Integer stock, String categoria,
			MultipartFile imagenProducto, Long id) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.categoria = categoria;
		this.imagenProducto = imagenProducto;
		this.id = id;
	}



	public ProductoDTO(Long id, Integer cantidadProducto) {
		this.id = id;
		this.cantidadProducto = cantidadProducto;
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

	public MultipartFile getImagenProducto() {
		return imagenProducto;
	}

	public void setImagenProducto(MultipartFile imagenProducto) {
		this.imagenProducto = imagenProducto;
	}

	public String getBase64Imagen() {
		return base64Imagen;
	}

	public void setBase64Imagen(String base64Imagen) {
		this.base64Imagen = base64Imagen;
	}

	public List<IngredienteProductoDTO> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<IngredienteProductoDTO> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	
	public Set<ProductoIngredienteEntity> getIngredientesSet() {
		return ingredientesSet;
	}



	public void setIngredientesSet(Set<ProductoIngredienteEntity> ingredientesSet) {
		this.ingredientesSet = ingredientesSet;
	}

	
	
	
	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public Integer getCantidadProducto() {
		return cantidadProducto;
	}




	public void setCantidadProducto(Integer cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}




	@Override
	public String toString() {
		return "ProductoDTO [nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", stock="
				+ stock + ", categoria=" + categoria + ", id=" + id
				+ ", cantidadProducto=" + cantidadProducto + "]";
	}













}
