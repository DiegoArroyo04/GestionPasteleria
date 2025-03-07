package com.servidor.pasteleria.services.implementations;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servidor.pasteleria.entity.ClienteEntity;
import com.servidor.pasteleria.entity.PedidoEntity;
import com.servidor.pasteleria.entity.ProductoEntity;
import com.servidor.pasteleria.model.ProductoDTO;
import com.servidor.pasteleria.repo.ClienteRepository;
import com.servidor.pasteleria.repo.PedidoRepository;
import com.servidor.pasteleria.repo.ProductoRepository;
import com.servidor.pasteleria.services.PedidoService;
import com.servidor.pasteleria.services.ProductoService;

import jakarta.servlet.http.HttpSession;

@Service
public class PedidoServiceImplement implements PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ProductoService productoService;

	@Autowired
	ProductoRepository productoRepository;

	@Override
	public void registrarPedido(ClienteEntity cliente, List<ProductoDTO> carrito, String observaciones,
			BigDecimal precioTotal, HttpSession session) {

		PedidoEntity pedido = new PedidoEntity();
		String productos = "";
		pedido.setCliente(cliente);

		// CONCATENACION PRODUCTOS
		for (ProductoDTO producto : carrito) {
			if (productos.equals("")) {
				productos = producto.getNombre() + " X" + producto.getCantidadProducto();
			} else {
				productos = productos + " , " + producto.getNombre() + " X" + producto.getCantidadProducto();
			}

		}
		pedido.setProductos(productos);
		pedido.setObservaciones(observaciones);
		pedido.setPrecio(precioTotal);
		pedido.setEstado("Pendiente");

		pedidoRepository.save(pedido);

	}

	@Override
	public List<PedidoEntity> obtenerTodosLosPedidos() {

		return pedidoRepository.findAll();
	}

	@Override
	public PedidoEntity obtenerPedidoPorId(Long id) {
		return pedidoRepository.findById(id).orElse(null);
	}

	@Override
	public String entregarPedido(PedidoEntity pedido) {

		List<ProductoDTO> productosLista = new ArrayList<ProductoDTO>();

		// ALMACENAR EN CADA POSICION DEL ARRAY LOS PRODUCTOS SEPARADOS POR LA COMA
		String[] productosArray = pedido.getProductos().split(" , ");

		// RECORRO LOS PRODUCTOS YA SEPARADOS
		for (String productoStr : productosArray) {

			// ARRAY PARA SEPARAR EL NOMBRE DE LA CANTIDAD
			String[] partes = productoStr.split(" X");

			// ELIMINAMOS LOS ESPACIOS EN BLANCO Y AÑADIMOS A LA LISTA
			ProductoDTO productoDTO = new ProductoDTO();
			productoDTO.setNombre(partes[0].trim());
			productoDTO.setCantidadProducto(Integer.valueOf(partes[1].trim()));

			// Añadir el ProductoDTO a la lista
			productosLista.add(productoDTO);

		}

		List<ProductoDTO> todosLosProductos = productoService.obtenerTodosLosProductos();

		String resultado = "";
		boolean productosRestados = true;

		// RECORRER PRODUCTOS DEL PEDIDO
		for (ProductoDTO productoPedido : productosLista) {

			// RECORRER PRODUCTOS BBDD
			for (ProductoDTO productoSistema : todosLosProductos) {

				if (productoPedido.getNombre().equals(productoSistema.getNombre())) {
					// SI LOS NOMBRES COINCIDEN BUSCAMOS EL ENTITY PARA ACTUALIZAR SU STOCK
					ProductoEntity productoEntity = productoRepository.findById(productoSistema.getId()).orElse(null);

					// COMPROBACION DEL STOCK
					if (productoPedido.getCantidadProducto() > productoEntity.getStock()) {
						// CONCATENAR ERRORES
						if (resultado.equals("")) {
							resultado = productoEntity.getNombre()
									+ " no tiene suficiente Stock como para entregar el pedido.";
						} else {
							resultado = resultado + " , " + productoEntity.getNombre()
									+ " no tiene suficiente Stock como para entregar el pedido.";
						}

						productosRestados = false;

					} else {
						productoEntity.setStock(productoEntity.getStock() - productoPedido.getCantidadProducto());
						productoRepository.save(productoEntity);

					}

				}
			}
		}

		// SI LOS PRODUCTOS SE HAN RESTADO CORRECTAMENTE DEL STOCK ENTONCES ACTUALIZO EL
		// PEDIDO
		if (productosRestados == true) {
			pedido.setEstado("Entregado");
			pedidoRepository.save(pedido);
		}

		return resultado;

	}

	@Override
	public List<ProductoDTO> buscarProductosMasVendidosSemanales() {

		// ENCONTRAR PEDIDOS ENTREGADOS HACE UNA SEMANA
		Timestamp fechaHaceUnaSemana = Timestamp.from(Instant.now().minus(7, ChronoUnit.DAYS));
		List<PedidoEntity> pedidosSemanales = pedidoRepository
				.encontrarPedidosEntregadosUltimaSemana(fechaHaceUnaSemana);
		List<ProductoDTO> productosLista = new ArrayList<ProductoDTO>();
		List<ProductoDTO> todosLosProductos = productoService.obtenerTodosLosProductos();

		// DESCONCANTENAR PARA BUSCAR LOS PRODUCTOS MAS VENDIDOS
		for (PedidoEntity pedido : pedidosSemanales) {

			List<ProductoDTO> productosPedido = new ArrayList<ProductoDTO>();

			// ALMACENAR EN CADA POSICION DEL ARRAY LOS PRODUCTOS SEPARADOS POR LA COMA
			String[] productosArray = pedido.getProductos().split(" , ");

			// RECORRO LOS PRODUCTOS YA SEPARADOS
			for (String productoStr : productosArray) {

				// ARRAY PARA SEPARAR EL NOMBRE DE LA CANTIDAD
				String[] partes = productoStr.split(" X");

				// ELIMINAMOS LOS ESPACIOS EN BLANCO Y AÑADIMOS A LA LISTA
				ProductoDTO productoDTO = new ProductoDTO();
				productoDTO.setNombre(partes[0].trim());
				productoDTO.setCantidadProducto(Integer.valueOf(partes[1].trim()));

				// Añadir el ProductoDTO a la lista
				productosPedido.add(productoDTO);

			}

			// RECORRER PRODUCTOS DEL PEDIDO
			for (ProductoDTO productoPedido : productosPedido) {

				// RECORRER PRODUCTOS BBDD
				for (ProductoDTO productoSistema : todosLosProductos) {

					if (productoPedido.getNombre().equals(productoSistema.getNombre())) {
						// SI LOS NOMBRES COINCIDEN COMPROBAMOS QUE NO EXISTA EL PRODUCTO EN LA LISTA DE
						// PRODUCTOS QUE DEVOLVEMOS Y SI EXISTE SUMAMOS CANTIDAD
						boolean encontrado = false;
						for (ProductoDTO producto : productosLista) {
							if (producto.getNombre().equals(productoPedido.getNombre())) {
								producto.setCantidadProducto(
										producto.getCantidadProducto() + productoPedido.getCantidadProducto());
								encontrado = true;
								break;
							}
						}

						// Si no está en la lista, lo agregamos
						if (!encontrado) {

							ProductoDTO productoNuevo = new ProductoDTO();
							productoNuevo.setId(productoSistema.getId());
							productoNuevo.setNombre(productoSistema.getNombre());
							productoNuevo.setDescripcion(productoSistema.getDescripcion());
							productoNuevo.setPrecio(productoSistema.getPrecio());
							productoNuevo.setCantidadProducto(productoPedido.getCantidadProducto());
							productoNuevo.setStock(productoSistema.getStock());
							productoNuevo.setCategoria(productoSistema.getCategoria());

							productosLista.add(productoNuevo);

						}

					}
				}
			}

		}
		// ORDENAR POR MAS VENDIDOS ANTES DE ENVIAR
		productosLista = ordenarListaPorMasVendidos(productosLista);

		return productosLista;

	}

	@Override
	public List<ProductoDTO> ordenarListaPorMasVendidos(List<ProductoDTO> productos) {

		// ORDENA LA LISTA DE MAYOR A MENOR POR SU CANTIDAD VENDIDA
		productos.sort(Comparator.comparingInt(ProductoDTO::getCantidadProducto).reversed());

		return productos;
	}

	@Override
	public List<ProductoDTO> buscarProductosMasVendidosMensuales() {
		// ENCONTRAR PEDIDOS ENTREGADOS HACE UN MES 
		Timestamp fechaHaceUnMes = Timestamp.valueOf(ZonedDateTime.now(ZoneId.systemDefault()).minusMonths(1).toLocalDateTime());

		List<PedidoEntity> pedidosSemanales = pedidoRepository
				.encontrarPedidosEntregadosUltimaSemana(fechaHaceUnMes);
		List<ProductoDTO> productosLista = new ArrayList<ProductoDTO>();
		List<ProductoDTO> todosLosProductos = productoService.obtenerTodosLosProductos();
 
		// DESCONCANTENAR PARA BUSCAR LOS PRODUCTOS MAS VENDIDOS
		for (PedidoEntity pedido : pedidosSemanales) {

			List<ProductoDTO> productosPedido = new ArrayList<ProductoDTO>();

			// ALMACENAR EN CADA POSICION DEL ARRAY LOS PRODUCTOS SEPARADOS POR LA COMA
			String[] productosArray = pedido.getProductos().split(" , ");

			// RECORRO LOS PRODUCTOS YA SEPARADOS
			for (String productoStr : productosArray) {

				// ARRAY PARA SEPARAR EL NOMBRE DE LA CANTIDAD
				String[] partes = productoStr.split(" X");

				// ELIMINAMOS LOS ESPACIOS EN BLANCO Y AÑADIMOS A LA LISTA
				ProductoDTO productoDTO = new ProductoDTO();
				productoDTO.setNombre(partes[0].trim());
				productoDTO.setCantidadProducto(Integer.valueOf(partes[1].trim()));

				// Añadir el ProductoDTO a la lista
				productosPedido.add(productoDTO);

			}

			// RECORRER PRODUCTOS DEL PEDIDO
			for (ProductoDTO productoPedido : productosPedido) {

				// RECORRER PRODUCTOS BBDD
				for (ProductoDTO productoSistema : todosLosProductos) {

					if (productoPedido.getNombre().equals(productoSistema.getNombre())) {
						// SI LOS NOMBRES COINCIDEN COMPROBAMOS QUE NO EXISTA EL PRODUCTO EN LA LISTA DE
						// PRODUCTOS QUE DEVOLVEMOS Y SI EXISTE SUMAMOS CANTIDAD
						boolean encontrado = false;
						for (ProductoDTO producto : productosLista) {
							if (producto.getNombre().equals(productoPedido.getNombre())) {
								producto.setCantidadProducto(
										producto.getCantidadProducto() + productoPedido.getCantidadProducto());
								encontrado = true;
								break;
							}
						}

						// Si no está en la lista, lo agregamos
						if (!encontrado) {

							ProductoDTO productoNuevo = new ProductoDTO();
							productoNuevo.setId(productoSistema.getId());
							productoNuevo.setNombre(productoSistema.getNombre());
							productoNuevo.setDescripcion(productoSistema.getDescripcion());
							productoNuevo.setPrecio(productoSistema.getPrecio());
							productoNuevo.setCantidadProducto(productoPedido.getCantidadProducto());
							productoNuevo.setStock(productoSistema.getStock());
							productoNuevo.setCategoria(productoSistema.getCategoria());

							productosLista.add(productoNuevo);

						}

					}
				}
			}

		}
		// ORDENAR POR MAS VENDIDOS ANTES DE ENVIAR
		productosLista = ordenarListaPorMasVendidos(productosLista);

		return productosLista;
	}

}
