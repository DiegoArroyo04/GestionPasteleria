package com.servidor.pasteleria.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.servidor.pasteleria.model.ProductoDTO;
import com.servidor.pasteleria.services.PedidoService;
import com.servidor.pasteleria.services.ProductoService;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	ProductoService productoService;
	
	@GetMapping("gestion")
	public String gestion() {
		return "pedidos/gestion";
	}

	
	@PostMapping("/entregar")
	public String entregarPedido(@RequestParam Long idPedido,Model model) {

		
		model.addAttribute("errorStock",pedidoService.entregarPedido(pedidoService.obtenerPedidoPorId(idPedido)));
		model.addAttribute("alerta",
				productoService.comprobarStockProductos(productoService.obtenerTodosLosProductos()));
		model.addAttribute("pedidos", pedidoService.obtenerTodosLosPedidos());
		
		
		return "administrador/historialPedidos";
	}
	
	@GetMapping("/estadisticas")
	public String estadisticas() {
		return "pedidos/estadisticasVentas";

	}
	
	@GetMapping("/estadisticasSemanales")
	public String estadisticasSemanales(Model model) {
	
		List<ProductoDTO> productosSemanales=pedidoService.buscarProductosMasVendidosSemanales();
		
		model.addAttribute("productos",productosSemanales);
		
		
		return "pedidos/estadisticas";
	}
	
	@GetMapping("/estadisticasMensuales")
	public String estadisticasMensuales(Model model) {
	
		List<ProductoDTO> productosMensuales=pedidoService.buscarProductosMasVendidosMensuales();
		
		model.addAttribute("productos",productosMensuales);
		
		
		return "pedidos/estadisticas";
	}
	
	
	
	
	
}
