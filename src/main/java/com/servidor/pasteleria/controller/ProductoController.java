package com.servidor.pasteleria.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.servidor.pasteleria.model.IngredienteDTO;
import com.servidor.pasteleria.model.ProductoDTO;
import com.servidor.pasteleria.services.IngredienteService;
import com.servidor.pasteleria.services.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	ProductoService productoService;

	@GetMapping("/gestion")
	public String gestionProductos() {
		return "productos/gestionProductos";
	}

	@GetMapping("/registrarNuevoProducto")
	public String registrarNuevoProducto(Model model) {

		List<IngredienteDTO> ingredientesExistentes = ingredienteService.obtenerTodosLosIngredientes();
		model.addAttribute("ingredientesExistentes", ingredientesExistentes);

		ProductoDTO producto = new ProductoDTO();
		model.addAttribute("producto", producto);

		return "productos/nuevoProducto";
	}

	@PostMapping("/guardar")
	public String guardarProductoNuevo(@ModelAttribute ProductoDTO producto) {

		productoService.guardarProducto(producto);

		return "redirect:/productos/mostrarProductos";

	}

	@GetMapping("/mostrarProductos")
	public String mostrarProductos(Model model) {

		List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();

		model.addAttribute("productos", productos);
		model.addAttribute("alerta",productoService.comprobarStockProductos(productos));
		
		return "productos/todosLosProductos";
	}

	@GetMapping("/fabricar")
	public String fabricarProducto(Model model) {

		List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();

		model.addAttribute("productos", productos);

		return "productos/fabricar";

	}
	
	@PostMapping("/guardarFabricado")
	public String guardarFabricado(@RequestParam Long idProducto,Model model) {

		String resultado=productoService.fabricarProducto(idProducto);
		model.addAttribute("resultado",resultado);
		
		List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();
		model.addAttribute("productos", productos);

		return "productos/fabricar";

	}
	
	@GetMapping("/editar")
	public String editarProducto(Model model) {

		List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();

		model.addAttribute("productos", productos);

		return "productos/editar";

	}
	
	
	@PostMapping("/buscarProductoAEditar")
	public String buscarProductoAEditar(@RequestParam Long idProducto,Model model) {

		ProductoDTO producto=productoService.encontrarProductoPorId(idProducto);

		model.addAttribute("producto",producto);

		return "productos/editarUnProducto";

	}
	
	@PostMapping("/actualizarProducto")
	public String actualizarProducto(@ModelAttribute ProductoDTO producto) {

		productoService.actualizarProducto(producto);

		return "redirect:/productos/mostrarProductos";

	}
	
	
	@GetMapping("/eliminar")
	public String eliminarProducto(Model model) {

		List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();

		model.addAttribute("productos", productos);

		return "productos/eliminar";

	}
	
	@PostMapping("/eliminarProducto")
	public String buscarProductoAEliminar(@RequestParam Long idProducto) {

		productoService.eliminarProductoPorId(idProducto);

		return "redirect:/productos/mostrarProductos";

	}
	

}
