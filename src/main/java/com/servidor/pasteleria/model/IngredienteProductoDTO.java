package com.servidor.pasteleria.model;

public class IngredienteProductoDTO {

	private String nombre;
	private Integer cantidad;

	public IngredienteProductoDTO() {

	}

	public IngredienteProductoDTO(String nombre, Integer cantidad) {

		this.nombre = nombre;
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "IngredienteProductoDTO [nombre=" + nombre + ", cantidad=" + cantidad + "]";
	}
	
	

}
