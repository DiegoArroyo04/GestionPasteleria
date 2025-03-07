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
import com.servidor.pasteleria.services.IngredienteService;

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {
	
	@Autowired
	IngredienteService ingredienteService;
	
	@GetMapping("/gestion")
	public String gestionIngredientes() {
		return "ingredientes/gestionIngredientes";
	}
	
	@GetMapping("/registrarNuevoIngrediente")
	public String registrarNuevoIngrediente(Model model) {
		
		IngredienteDTO ingrediente=new IngredienteDTO();
		model.addAttribute("ingrediente",ingrediente);
		
		
		return "ingredientes/nuevoIngrediente";
	}
	

	
	@PostMapping("/guardar")
	public String guardarIngredienteNuevo(@ModelAttribute IngredienteDTO ingrediente){
			
			
			ingredienteService.guardarIngrediente(ingrediente);

		 return "redirect:/ingredientes/mostrarIngredientes";
		
	}
	
	@GetMapping("mostrarIngredientes")
	public String mostrarIngrediente(Model model) {
		
		List<IngredienteDTO> ingredientes=ingredienteService.obtenerTodosLosIngredientes();
		model.addAttribute("ingredientes",ingredientes);
		
		
		return "ingredientes/todosLosIngredientes";
	}
	
	
	@GetMapping("comprarIngrediente")
	public String comprarIngrediente(Model model) {
		
		List<IngredienteDTO> ingredientes=ingredienteService.obtenerTodosLosIngredientes();
		model.addAttribute("ingredientes",ingredientes);
		
		
		return "ingredientes/comprarIngrediente";
	}
	
	@PostMapping("/guardarCompra")
	public String guardarCompra(@RequestParam String ingredienteNombre,@RequestParam Integer cantidad) {
		
		ingredienteService.comprarIngrediente(ingredienteNombre, cantidad);
		
		return "redirect:/ingredientes/mostrarIngredientes";
	}
	
	
	
}
