package com.servidor.pasteleria.services;

import java.math.BigDecimal;
import java.util.List;

import com.servidor.pasteleria.entity.ClienteEntity;
import com.servidor.pasteleria.model.ClienteDTO;
import com.servidor.pasteleria.model.ProductoDTO;

import jakarta.servlet.http.HttpSession;

public interface ClienteService {
	ClienteEntity buscarPorUsuario (String usuario);
	String guardarCliente(ClienteDTO cliente);
	void aniadirAlCarrito(ProductoDTO producto,HttpSession session);
	Integer obtenerTotalProductosCarrito(HttpSession session);
	List<ProductoDTO> obtenerCarrito(HttpSession session);
	List<ProductoDTO> productosCarrito(List<ProductoDTO> carrito);
	BigDecimal obtenerPrecioTotalCarrito(List<ProductoDTO> carrito);
	List<ProductoDTO> eliminarProductoCarrito(List<ProductoDTO>carrito,Long id);
	
}
