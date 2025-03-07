package com.servidor.pasteleria.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.servidor.pasteleria.entity.ClienteEntity;
import com.servidor.pasteleria.model.ClienteDTO;
import com.servidor.pasteleria.model.ProductoDTO;
import com.servidor.pasteleria.services.ClienteService;
import com.servidor.pasteleria.services.PedidoService;
import com.servidor.pasteleria.services.ProductoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	ProductoService productoService;

	@Autowired
	PedidoService pedidoService;

	@GetMapping("/registrar")
	public String registroClientes(Model model) {
		ClienteDTO cliente = new ClienteDTO();
		model.addAttribute("cliente", cliente);
		return "clientes/registro";
	}

	@PostMapping("/guardarCliente")
	public String guardarCliente(@ModelAttribute ClienteDTO cliente,Model model) {
		
		String resultado=clienteService.guardarCliente(cliente);
		
		//SI EL RESULTADO ES "" SIGNIFICA QUE SE HA REGISTRADO CON EXITO SI NO ENVIA EL ERROR AL MODAL
		if(resultado.equals("")) {
			return "redirect:/";
		}else {
			model.addAttribute("error",resultado);
			ClienteDTO clienteNuevo = new ClienteDTO();
			model.addAttribute("cliente", clienteNuevo);
			return "clientes/registro";
		}
		

	}

	@GetMapping("/clientesHome")
	public String clientesHome(Model model, HttpSession session) {
		model.addAttribute("productos", productoService.obtenerTodosLosProductos());
		model.addAttribute("productoDTO", new ProductoDTO());
		model.addAttribute("totalProductosCarrito", clienteService.obtenerTotalProductosCarrito(session));
		return "clientes/clientes";
	}

	@PostMapping("/aniadirAlCarrito")
	public String aniadirAlCarrito(@ModelAttribute ProductoDTO producto, HttpSession session, Model model) {

		clienteService.aniadirAlCarrito(producto, session);

		// Volver a cargar los productos para mostrar en la vista
		model.addAttribute("productos", productoService.obtenerTodosLosProductos());
		model.addAttribute("carrito", clienteService.obtenerCarrito(session));
		model.addAttribute("totalProductosCarrito", clienteService.obtenerTotalProductosCarrito(session));

		return "clientes/clientes";
	}

	@GetMapping("/carrito")
	public String carrito(HttpSession session, Model model) {
		// Recuperamos la lista de productos del carrito desde la sesión
		List<ProductoDTO> carrito = clienteService.obtenerCarrito(session);

		// AÑADIR AL MODELO EL EMAIL Y NOMBRE CLIENTE PARA ENVIAR CORREO SI TRAMITA
		// PEDIDO
		ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
		String emailCliente = cliente.getEmail();
		String nombreCliente = cliente.getNombre() + " " + cliente.getApellidos();

		if (carrito == null || carrito.size() == 0) {
			model.addAttribute("error", "El carrito esta vacio");
		} else {

			carrito = clienteService.productosCarrito(carrito);

			model.addAttribute("carrito", carrito);
			session.setAttribute("carrito", carrito);
			model.addAttribute("precioTotal", clienteService.obtenerPrecioTotalCarrito(carrito));
			model.addAttribute("emailCliente", emailCliente);
			model.addAttribute("nombreCliente", nombreCliente);
		}

		return "clientes/carrito";
	}

	@GetMapping("/eliminarProductoCarrito")
	public String eliminarProductoCarrito(@RequestParam Long id, HttpSession session, Model model) {
		// Recuperamos la lista de productos del carrito desde la sesión
		List<ProductoDTO> carrito = clienteService.obtenerCarrito(session);

		// AÑADIR AL MODELO EL EMAIL Y NOMBRE CLIENTE PARA ENVIAR CORREO SI TRAMITA
		// PEDIDO
		ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
		String emailCliente = cliente.getEmail();
		String nombreCliente = cliente.getNombre() + " " + cliente.getApellidos();

		carrito = clienteService.eliminarProductoCarrito(carrito, id);

		model.addAttribute("carrito", carrito);
		session.setAttribute("carrito", carrito);
		model.addAttribute("precioTotal", clienteService.obtenerPrecioTotalCarrito(carrito));
		model.addAttribute("emailCliente", emailCliente);
		model.addAttribute("nombreCliente", nombreCliente);

		if (carrito == null || carrito.size() == 0) {
			model.addAttribute("error", "El carrito esta vacio");
		}

		return "clientes/carrito";
	}

	@PostMapping("/tramitarPedido")
	public String tramitarPedido(@RequestParam String observaciones, @RequestParam BigDecimal precioTotal,
			HttpSession session, Model model) {
		ClienteEntity cliente = (ClienteEntity) session.getAttribute("cliente");
		List<ProductoDTO> carrito = clienteService.obtenerCarrito(session);

		pedidoService.registrarPedido(cliente, carrito, observaciones, precioTotal, session);

		model.addAttribute("carrito", carrito);
		model.addAttribute("precioTotal", precioTotal);

		// VACIAR CARRITO
		session.setAttribute("carrito", new ArrayList<ProductoDTO>());

		return "/clientes/resumenPedido";
	}

}
