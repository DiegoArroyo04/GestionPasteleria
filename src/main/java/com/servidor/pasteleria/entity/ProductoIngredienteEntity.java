package com.servidor.pasteleria.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODUCTO_INGREDIENTE")
public class ProductoIngredienteEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PRODUCTO_INGREDIENTE", unique = true, nullable = false)
	private Long idProductoIngrediente;
		

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO")
    private ProductoEntity producto;

   
    @ManyToOne
    @JoinColumn(name = "ID_INGREDIENTE")
    private IngredienteEntity ingrediente;

    @Column(name = "CANTIDAD_INGREDIENTES", nullable = false)
    private int cantidadIngredientes;
    
    
    

	public ProductoIngredienteEntity() {
	
	}

	public ProductoEntity getProducto() {
		return producto;
	}

	public void setProducto(ProductoEntity producto) {
		this.producto = producto;
	}

	public IngredienteEntity getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(IngredienteEntity ingrediente) {
		this.ingrediente = ingrediente;
	}

	public int getCantidadIngredientes() {
		return cantidadIngredientes;
	}

	public void setCantidadIngredientes(int cantidadIngredientes) {
		this.cantidadIngredientes = cantidadIngredientes;
	}

	
	
	public Long getIdProductoIngrediente() {
		return idProductoIngrediente;
	}

	public void setIdProductoIngrediente(Long idProductoIngrediente) {
		this.idProductoIngrediente = idProductoIngrediente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
	
}
