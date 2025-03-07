package com.servidor.pasteleria.services.implementations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servidor.pasteleria.entity.IngredienteEntity;
import com.servidor.pasteleria.model.IngredienteDTO;
import com.servidor.pasteleria.repo.IngredienteRepository;
import com.servidor.pasteleria.services.IngredienteService;

@Service
public class IngredienteServiceImplement implements IngredienteService {

	@Autowired
	IngredienteRepository ingredienteRepository;

	@Override
	public void guardarIngrediente(IngredienteDTO ingrediente) {

		try {
			// Convertimos el archivo de imagen a un array de bytes
			byte[] imagenBytes = ingrediente.getImagenIngrediente().getBytes();

			// CONVERTIR EN ENTITY PARA GUARDAR

			IngredienteEntity ingredienteEntity = new IngredienteEntity();

			ingredienteEntity.setNombre(ingrediente.getNombre());
			ingredienteEntity.setPrecio(ingrediente.getPrecio());
			ingredienteEntity.setStock(ingrediente.getStock());
			ingredienteEntity.setFabricante(ingrediente.getFabricante());
			ingredienteEntity.setImagenIngrediente(imagenBytes);

			ingredienteRepository.save(ingredienteEntity);

		} catch (IOException e) {
			e.printStackTrace(); 

		}

	}

	@Override
	public List<IngredienteDTO> obtenerTodosLosIngredientes() {
		
		//RECUPERAMOS TODOS LOS INGREDIENTES REGISTRADOS EN LA TABLA INGREDIENTE
		List<IngredienteEntity> ingredientes=ingredienteRepository.findAll();
		
		
		//CONVERSION EN DTO
		List<IngredienteDTO> ingredientesDTO=new ArrayList<IngredienteDTO>();
		   for (IngredienteEntity ingrediente : ingredientes) {
			   
		        // Verifica si el ingrediente tiene imagen y la convierte en formato base 64
		        if (ingrediente.getImagenIngrediente() != null) {
		            String base64Image = Base64.getEncoder().encodeToString(ingrediente.getImagenIngrediente());
		            
		            IngredienteDTO ingredienteDTO=new IngredienteDTO(ingrediente.getNombre(),ingrediente.getPrecio(),ingrediente.getStock(),ingrediente.getFabricante(),base64Image);
		            ingredientesDTO.add(ingredienteDTO);
		           
		        }else {
		        	 IngredienteDTO ingredienteDTO=new IngredienteDTO(ingrediente.getNombre(),ingrediente.getPrecio(),ingrediente.getStock(),ingrediente.getFabricante(),"");
		        	 ingredientesDTO.add(ingredienteDTO);
		        }
		        
		    }
		
		return ingredientesDTO;
	}

	@Override
	public void comprarIngrediente(String nombreIngrediente, Integer cantidad) {
		
		IngredienteEntity ingredienteEntity=ingredienteRepository.buscarPorNombre(nombreIngrediente);
		
		ingredienteEntity.setStock(ingredienteEntity.getStock()+cantidad);
		
		ingredienteRepository.save(ingredienteEntity);
		
	}



}
