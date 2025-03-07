package com.servidor.pasteleria.services;

import java.util.List;

import com.servidor.pasteleria.entity.IngredienteEntity;
import com.servidor.pasteleria.model.IngredienteDTO;

public interface IngredienteService {

	void guardarIngrediente(IngredienteDTO ingrediente);
	List<IngredienteDTO> obtenerTodosLosIngredientes(); 
	void comprarIngrediente(String nombreIngrediente,Integer cantidad);

	
	
}
