package com.servidor.pasteleria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.servidor.pasteleria.entity.EmpleadoEntity;
import com.servidor.pasteleria.model.EmpleadoDTO;
import com.servidor.pasteleria.services.EmpleadoService;
import com.servidor.pasteleria.services.PedidoService;
import com.servidor.pasteleria.services.ProductoService;
import com.servidor.pasteleria.services.RegistroHorasEmpleadoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdministradorController {

	@Autowired
	EmpleadoService empleadoService;

	@Autowired
	RegistroHorasEmpleadoService registroHorasEmpleadoService;

	@Autowired
	PedidoService pedidoService;

	@Autowired
	ProductoService productoService;

	@GetMapping("/administradorPanel")
	public String panelAdministracion(HttpSession session, Model model) {
		EmpleadoEntity empleado = (EmpleadoEntity) session.getAttribute("empleado");

		if (empleado == null) {
			return "redirect:/"; // Redirige al login si la sesión expiró
		}

		model.addAttribute("empleado", empleado);

		// COMPROBACION DE QUE SE ESTE TRABAJANDO
		boolean jornadaActiva = empleadoService.estaTrabajando(empleado);
		model.addAttribute("jornadaActiva", jornadaActiva);

		return "administrador/administrador";
	}

	@GetMapping("/enDesarrollo")
	public String enDesarrollo() {
		return "enDesarrollo";
	}

	@GetMapping("/gestionEmpleados")
	public String gestionEmpleados() {
		return "administrador/gestionEmpleados";
	}

	@GetMapping("/listaEmpleados")
	public String todosLosEmpleados(Model model) {

		model.addAttribute("empleados", empleadoService.obtenerTodosLosEmpleados());

		return "administrador/todosLosEmpleados";
	}

	@GetMapping("/registrarEmpleado")
	public String registrarEmpleado(Model model) {

		EmpleadoDTO empleado = new EmpleadoDTO();

		model.addAttribute("empleado", empleado);

		return "administrador/registrarEmpleado";
	}

	@PostMapping("/guardarEmpleado")
	public String guardarEmpleado(@ModelAttribute EmpleadoDTO empleado, Model model) {

		EmpleadoEntity empleadoEntity = empleadoService.buscarPorUsuario(empleado.getUsuario());

		if (empleadoEntity == null) {

			empleadoService.guardarEmpleado(empleado);

			return "redirect:/listaEmpleados";
		} else {

			EmpleadoDTO empleadoDTO = new EmpleadoDTO();

			model.addAttribute("empleado", empleadoDTO);

			// MODELO PARA MOSTRAR ERROR SI YA ESTA REGISTRADO
			model.addAttribute("resultado", "Usuario Ya Registrado.Por Favor Introduce Un Usuario Distinto.");

			return "administrador/registrarEmpleado";
		}

	}

	@GetMapping("registroHorasEmpleados")
	public String registroHorasEmpledos(Model model) {

		model.addAttribute("registros", registroHorasEmpleadoService.obtenerTodosLosRegistros());
		return "/registroHoras/registroHorasEmpleados";
	}

	@GetMapping("/historialPedidos")
	public String historialPedidos(Model model) {

		model.addAttribute("alerta",
				productoService.comprobarStockProductos(productoService.obtenerTodosLosProductos()));

		model.addAttribute("pedidos", pedidoService.obtenerTodosLosPedidos());
		return "/administrador/historialPedidos";
	}

}
