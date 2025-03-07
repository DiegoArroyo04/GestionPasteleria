package com.servidor.pasteleria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.servidor.pasteleria.entity.ClienteEntity;
import com.servidor.pasteleria.entity.EmpleadoEntity;
import com.servidor.pasteleria.services.ClienteService;
import com.servidor.pasteleria.services.EmpleadoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	EmpleadoService empleadoService;

	@Autowired
	ClienteService clienteService;

	@GetMapping("/")
	public String login() {
		return "login";
	}

	@GetMapping("/comprobarLogin")
	public String comprobarlogin(@RequestParam String usuario, @RequestParam String contrasenia, Model model,
			HttpSession session) {

		EmpleadoEntity empleado = empleadoService.buscarPorUsuario(usuario);

		// SI ENCONTRAMOS EL EMPLEADO COMPARAMOS SU CONTRASEÑA Y LO GUARDAMOS EN SESION
		if (empleado != null) {

			if (empleado.getContrasenia().equals(contrasenia)) {
				session.setAttribute("empleado", empleado);
				return "redirect:/administradorPanel";
			} else {
				model.addAttribute("mensajeError", "Contraseña incorrecta.");
				return "login";
			}
		}

		else {
			// SI NO ES EMPLEADO BUSCAR EN LA TABLA CLIENTES
			ClienteEntity cliente = clienteService.buscarPorUsuario(usuario);

			if (cliente == null) {
				model.addAttribute("mensajeError",
						"No se ha encontrado ningún usuario registrado con ese nombre de usuario.");

			} else {
				// SI EL CLIENTE EXISTE COMPROBAR CONTRASEÑA
				if (cliente.getContrasenia().equals(contrasenia)) {
					session.setAttribute("cliente", cliente);
					return "redirect:/clientes/clientesHome";
				} else {
					model.addAttribute("mensajeError", "Contraseña incorrecta.");

				}

			}

		}

		return "login";
	}

	@GetMapping("/cerrarSesion")
	public String logout(HttpSession session) {
		session.invalidate(); // Elimina los datos de la sesión
		return "redirect:/"; // Redirige al login
	}
	
	
}
