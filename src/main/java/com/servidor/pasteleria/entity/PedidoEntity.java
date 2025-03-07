package com.servidor.pasteleria.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "PEDIDO")
public class PedidoEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_PEDIDO", unique = true, nullable = false)
	private Long idPedido;

	// INSERTABLE FALSE PORQUE LA INSERCCION DE LA FECHA SE HACE DESDE BBDD
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FECHA", nullable = false, insertable = false)
	private Timestamp fecha;

	@Column(name = "PRODUCTOS", nullable = false)
	private String productos;

	@Column(name = "PRECIO", nullable = false, precision = 10, scale = 2)
	private BigDecimal precio;

	@Column(name = "OBSERVACIONES", nullable = true)
	private String observaciones;

	@Column(name = "ESTADO", nullable = true)
	private String estado;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE")
	private ClienteEntity cliente;

	public PedidoEntity() {

	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getProductos() {
		return productos;
	}

	public void setProductos(String productos) {
		this.productos = productos;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
