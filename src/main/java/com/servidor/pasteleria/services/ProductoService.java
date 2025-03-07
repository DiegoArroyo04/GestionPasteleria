package com.servidor.pasteleria.services;

import java.util.List;

import com.servidor.pasteleria.model.ProductoDTO;

public interface ProductoService {
	void guardarProducto(ProductoDTO producto);
	List <ProductoDTO> obtenerTodosLosProductos();
	String fabricarProducto(Long idProducto);
	ProductoDTO encontrarProductoPorId(Long idProducto);
	void actualizarProducto(ProductoDTO producto);
	void eliminarProductoPorId(Long idProducto);
	String comprobarStockProductos(List<ProductoDTO> productos);
}
