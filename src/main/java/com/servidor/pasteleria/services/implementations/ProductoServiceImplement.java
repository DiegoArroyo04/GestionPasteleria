package com.servidor.pasteleria.services.implementations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servidor.pasteleria.entity.ProductoEntity;
import com.servidor.pasteleria.entity.ProductoIngredienteEntity;
import com.servidor.pasteleria.model.ProductoDTO;
import com.servidor.pasteleria.repo.IngredienteRepository;
import com.servidor.pasteleria.repo.ProductoIngredienteRepository;
import com.servidor.pasteleria.repo.ProductoRepository;
import com.servidor.pasteleria.services.IngredienteService;
import com.servidor.pasteleria.services.ProductoService;

@Service
public class ProductoServiceImplement implements ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	IngredienteRepository ingredienteRepository;

	@Autowired
	ProductoIngredienteRepository productoIngredienteRepository;

	@Override
	public void guardarProducto(ProductoDTO producto) {

		try {
			// Convertimos el archivo de imagen a un array de bytes
			byte[] imagenBytes = producto.getImagenProducto().getBytes();

			// CONVERTIR EN ENTITY PARA GUARDAR

			ProductoEntity productoEntity = new ProductoEntity();
			productoEntity.setNombre(producto.getNombre());
			productoEntity.setDescripcion(producto.getDescripcion());
			productoEntity.setPrecio(producto.getPrecio());
			productoEntity.setStock(producto.getStock());
			productoEntity.setCategoria(producto.getCategoria());
			productoEntity.setImagenProducto(imagenBytes);

			productoRepository.save(productoEntity);

			// RECORREMOS LA LISTA DE INGREDIENTES DE LOS PRODUCTOS Y HACEMOS EL MAPEO
			for (int i = 0; i < producto.getIngredientes().size(); i++) {

				ProductoIngredienteEntity productoIngredienteEntity = new ProductoIngredienteEntity();
				productoIngredienteEntity.setProducto(productoEntity);
				productoIngredienteEntity.setIngrediente(
						ingredienteRepository.buscarPorNombre(producto.getIngredientes().get(i).getNombre()));
				productoIngredienteEntity.setCantidadIngredientes(producto.getIngredientes().get(i).getCantidad());
				productoIngredienteRepository.save(productoIngredienteEntity);

			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	@Override
	public List<ProductoDTO> obtenerTodosLosProductos() {

		// RECUPERAMOS TODOS LOS PRODUCTOS REGISTRADOS EN LA TABLA INGREDIENTE
		List<ProductoEntity> productos = productoRepository.findAll();

		// CONVERSION EN DTO
		List<ProductoDTO> productosDTO = new ArrayList<ProductoDTO>();
		for (ProductoEntity producto : productos) {

			// Verifica si el ingrediente tiene imagen y la convierte en formato base 64
			if (producto.getImagenProducto() != null) {
				String base64Image = Base64.getEncoder().encodeToString(producto.getImagenProducto());

				ProductoDTO productoDTO = new ProductoDTO(producto.getNombre(), producto.getDescripcion(),
						producto.getPrecio(), producto.getStock(), producto.getCategoria(), base64Image,
						producto.getIngredientes(), producto.getIdProducto());
				productosDTO.add(productoDTO);

			} else {
				ProductoDTO productoDTO = new ProductoDTO(producto.getNombre(), producto.getDescripcion(),
						producto.getPrecio(), producto.getStock(), producto.getCategoria(), "",
						producto.getIngredientes(), producto.getIdProducto());
				productosDTO.add(productoDTO);
			}

		}

		return productosDTO;
	}

	@Override
	public String fabricarProducto(Long idProducto) {

		Optional<ProductoEntity> productoOptional = productoRepository.findById(idProducto);

		ProductoEntity productoEntity = productoOptional.orElse(null);
		boolean puedeFabricarse = true;
		String mensaje = "";

		// COMPROBACION DE QUE SE PUEDA FABRICAR
		for (ProductoIngredienteEntity productoIngrediente : productoEntity.getIngredientes()) {

			if (productoIngrediente.getCantidadIngredientes() > productoIngrediente.getIngrediente().getStock()) {
				puedeFabricarse = false;
				mensaje = "El producto " + productoIngrediente.getProducto().getNombre()
						+ " no tiene suficiente cantidad de " + productoIngrediente.getIngrediente().getNombre()
						+ " para ser creado";
			}

		}

		// SI SE PUEDE FABRICAR SE FABRICA
		if (puedeFabricarse == true) {

			// RESTAR TODOS LOS INGREDIENTES
			for (ProductoIngredienteEntity productoIngrediente : productoEntity.getIngredientes()) {

				productoIngrediente.getIngrediente().setStock(productoIngrediente.getIngrediente().getStock()
						- productoIngrediente.getCantidadIngredientes());

				ingredienteRepository.save(productoIngrediente.getIngrediente());

			}

			productoEntity.setStock(productoEntity.getStock() + 1);
			productoRepository.save(productoEntity);

			mensaje = "El producto " + productoEntity.getNombre() + " ha sido creado con exito.";
		}

		return mensaje;

	}

	@Override
	public ProductoDTO encontrarProductoPorId(Long idProducto) {

		Optional<ProductoEntity> productoOptional = productoRepository.findById(idProducto);
		ProductoEntity productoEntity = productoOptional.orElse(null);
		
		String base64Image = Base64.getEncoder().encodeToString(productoEntity.getImagenProducto());

		// CONVERTIR A DTO
		ProductoDTO productoDTO = new ProductoDTO(productoEntity.getNombre(), productoEntity.getDescripcion(),
				productoEntity.getPrecio(), productoEntity.getStock(), productoEntity.getCategoria(), base64Image,
				productoEntity.getIdProducto());
		
		return productoDTO;
	}

	@Override
	public void actualizarProducto(ProductoDTO producto) {
		
		
		try {
			// Convertimos el archivo de imagen a un array de bytes
			byte[] imagenBytes = producto.getImagenProducto().getBytes();

			// CONVERTIR EN ENTITY PARA GUARDAR

			Optional<ProductoEntity> productoOptional = productoRepository.findById(producto.getId());
			ProductoEntity productoEntity = productoOptional.orElse(null);
			
			productoEntity.setNombre(producto.getNombre());
			productoEntity.setDescripcion(producto.getDescripcion());
			productoEntity.setPrecio(producto.getPrecio());
			productoEntity.setStock(producto.getStock());
			productoEntity.setCategoria(producto.getCategoria());
			productoEntity.setImagenProducto(imagenBytes);

			productoRepository.save(productoEntity);

		} catch (IOException e) {
			e.printStackTrace();

		}

		
	}

	@Override
	@Transactional
	public void eliminarProductoPorId(Long idProducto) {

		Optional<ProductoEntity> productoOptional = productoRepository.findById(idProducto);
		ProductoEntity productoEntity = productoOptional.orElse(null);
		
		// ELIMINAR TODAS LAS RELACIONES DE LA TABLA PRODUCTO INGREDIENTE
	    productoIngredienteRepository.deleteAll(productoEntity.getIngredientes());
	    
	    //ELIMINAR EL PRODUCTO
		productoRepository.delete(productoEntity);
		
		
	}

	@Override
	public String comprobarStockProductos(List<ProductoDTO> productos) {
		
		String resultado="";
		//ALERTA DE STOCK
		for(ProductoDTO producto:productos) {
			if(producto.getStock()<2) {
				
				//CONCATENACION DE RESULTADOS POR SI HAY MAS DE UN PRODUCTO SIN STOCK
				if(resultado.equals("")) {
					resultado=producto.getNombre()+" esta por debajo de 2 unidades";
				}else {
					resultado=resultado+" , "+producto.getNombre()+" esta por debajo de 2 unidades";
				}
			}
		}
		return resultado;
	}

}
