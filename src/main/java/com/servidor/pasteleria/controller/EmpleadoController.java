package com.servidor.pasteleria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.servidor.pasteleria.entity.EmpleadoEntity;
import com.servidor.pasteleria.entity.RegistroHorasEmpleadoEntity;
import com.servidor.pasteleria.services.EmpleadoService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/empleados")
public class EmpleadoController {
	
	@Autowired
	EmpleadoService empleadoService;
	
	@PostMapping("/jornada")
	public String gestionarJornada(@RequestParam("accion") String accion,HttpSession session,Model model) {
	    	
		EmpleadoEntity empleado = (EmpleadoEntity) session.getAttribute("empleado");
		
		 if (empleado == null) {
		        return "redirect:/"; // Redirige al login si la sesión expiró
		    }
		 
		 if(accion.equals("comenzar")) {
			empleadoService.comenzarJornada(empleado);
		    
			
		 }else if(accion.equals("terminar")){
			 empleadoService.finalizarJornada(empleado);
		
			 
		 }
	
	    return "redirect:/administradorPanel"; // Vuelve a cargar la página
	}
	

}
