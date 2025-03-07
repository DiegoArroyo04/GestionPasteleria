package com.servidor.pasteleria.services;

import java.math.BigDecimal;
import java.util.List;

import com.servidor.pasteleria.entity.ClienteEntity;
import com.servidor.pasteleria.entity.PedidoEntity;
import com.servidor.pasteleria.model.ProductoDTO;

import jakarta.servlet.http.HttpSession;

public interface PedidoService{
		void registrarPedido(ClienteEntity cliente,List<ProductoDTO> carrito,String observaciones,BigDecimal precioTotal,HttpSession session);
		List<PedidoEntity> obtenerTodosLosPedidos();
		PedidoEntity obtenerPedidoPorId(Long id);
		String entregarPedido(PedidoEntity pedido);
		List<ProductoDTO> buscarProductosMasVendidosSemanales();
		List<ProductoDTO> ordenarListaPorMasVendidos(List<ProductoDTO> productos);
		List<ProductoDTO> buscarProductosMasVendidosMensuales();
	    
}
