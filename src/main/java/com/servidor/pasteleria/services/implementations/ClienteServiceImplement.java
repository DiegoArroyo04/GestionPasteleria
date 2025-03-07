package com.servidor.pasteleria.services.implementations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servidor.pasteleria.entity.ClienteEntity;
import com.servidor.pasteleria.model.ClienteDTO;
import com.servidor.pasteleria.model.ProductoDTO;
import com.servidor.pasteleria.repo.ClienteRepository;
import com.servidor.pasteleria.services.ClienteService;
import com.servidor.pasteleria.services.ProductoService;

import jakarta.servlet.http.HttpSession;

@Service
public class ClienteServiceImplement implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ProductoService productoService;

	@Override
	public ClienteEntity buscarPorUsuario(String usuario) {
		ClienteEntity clienteEntity = null;
		clienteEntity = clienteRepository.buscarPorUsuario(usuario);

		return clienteEntity;
	}

	@Override
	public String guardarCliente(ClienteDTO cliente) {
		

		//COMPROBACION DE QUE EL USERNAME ESTE DISPONIBLE
		
		List<ClienteEntity> clientes=clienteRepository.findAll();
		boolean usuarioDuplicado=false;
		
		for(ClienteEntity c: clientes) {
			if(c.getUsuario().equals(cliente.getUsuario())) {
				usuarioDuplicado=true;
			}
		}
		
		if(usuarioDuplicado==false) {
			// CONVERTIR A ENTITY PARA GUARDAR
			ClienteEntity clienteEntity = new ClienteEntity();
			clienteEntity.setNombre(cliente.getNombre());
			clienteEntity.setApellidos(cliente.getApellidos());
			clienteEntity.setDireccion(cliente.getDireccion());
			clienteEntity.setTelefono(cliente.getTelefono());
			clienteEntity.setEmail(cliente.getEmail());
			clienteEntity.setUsuario(cliente.getUsuario());
			clienteEntity.setContrasenia(cliente.getContrasenia());
			clienteRepository.save(clienteEntity);
			
			return "";
		}else {
			return "No ha sido posible registrarse debido a que ese nombre de usuario ya esta registrado.Intentelo de nuevo";
		}
		

	}

	@Override
	public void aniadirAlCarrito(ProductoDTO producto, HttpSession session) {
		List<ProductoDTO> carrito = obtenerCarrito(session);

		// CREAR CARRITO SI NO EXISTE
		if (carrito == null) {
			carrito = new ArrayList<>();
		}

		// VERIFICAR QUE EL PRODUCTO EXISTA Y EN CUYO CASO SUMAR CANTIDAD
		boolean productoExistente = false;
		for (ProductoDTO p : carrito) {
			if (p.getId().equals(producto.getId())) {
				p.setCantidadProducto(p.getCantidadProducto() + producto.getCantidadProducto());
				productoExistente = true;
				break;
			}
		}

		// SI EL PRODUCTO NO EXISTE SE AÑADE AL CARRITO
		if (!productoExistente) {
			carrito.add(producto);
		}

		// ACTUALIZAR EL CARITO EN LA SESION
		session.setAttribute("carrito", carrito);

	}

	@Override
	public Integer obtenerTotalProductosCarrito(HttpSession session) {
		List<ProductoDTO> carrito = obtenerCarrito(session);
		// TOTAL DE CANTIDAD PRODUCTOS PARA MOSTRAR INFO EN CARRITO
		Integer totalProductosCarrito = 0;
		for (int i = 0; i < carrito.size(); i++) {
			totalProductosCarrito += carrito.get(i).getCantidadProducto();
		}

		// ACTUALIZAR TOTAL DE PRODUCTOS DEL CARRITO EN LA SESION
		session.setAttribute("totalProductosCarrito", totalProductosCarrito);

		return totalProductosCarrito;
	}

	@Override
	public List<ProductoDTO> obtenerCarrito(HttpSession session) {
	    List<ProductoDTO> carrito = (List<ProductoDTO>) session.getAttribute("carrito");
	    if (carrito == null) {
	        carrito = new ArrayList<>(); 
	        session.setAttribute("carrito", carrito);
	    }
	    return carrito;
	}

	@Override
	public List<ProductoDTO> productosCarrito(List<ProductoDTO> carrito) {
		
		if (carrito == null || carrito.isEmpty()) {
	        return new ArrayList<>();
	    }
		
		//AÑADIR LA CANTIDAD AL CARRITO
		List<ProductoDTO> productosCarrito = new ArrayList<>();
		for(int i=0; i<carrito.size(); i++) {
			ProductoDTO producto=productoService.encontrarProductoPorId(carrito.get(i).getId());
			producto.setCantidadProducto(carrito.get(i).getCantidadProducto());
			
			productosCarrito.add(producto);
			 
		}
		
		return productosCarrito;
	}

	@Override
	public BigDecimal obtenerPrecioTotalCarrito(List<ProductoDTO> carrito) {
		BigDecimal precio=BigDecimal.ZERO;
		   for (ProductoDTO producto : carrito) {
			   //MULTIPLICAR EL PRECIO DEL PRODUCTO POR LA CANTIDAD Y POSTERIORMENTE SE LO SUMAMOS AL TOTAL
		        BigDecimal precioProducto = producto.getPrecio().multiply(BigDecimal.valueOf(producto.getCantidadProducto())); 
		        precio = precio.add(precioProducto); 
		    }
		return precio;
	}

	@Override
	public List<ProductoDTO> eliminarProductoCarrito(List<ProductoDTO> carrito, Long id) {
		for(int i=0; i<carrito.size(); i++) {
			if(carrito.get(i).getId().equals(id)) {
				carrito.remove(i);
				break;
			}
		}
		return carrito;
	}
	
	
	
}
