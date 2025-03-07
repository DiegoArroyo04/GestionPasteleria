package com.servidor.pasteleria.repo;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servidor.pasteleria.entity.PedidoEntity;




@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {

	// JpaRepository ya incluye métodos para CRUD:
	// save() - Crear/Actualizar
	// findById() - Leer por ID
	// findAll() - Leer todos
	// deleteById() - Eliminar por ID
	
	@Query("SELECT p FROM PedidoEntity p WHERE p.estado = 'ENTREGADO' AND p.fecha >= :fechaInicio")
    List<PedidoEntity> encontrarPedidosEntregadosUltimaSemana(@Param("fechaInicio") Timestamp fechaInicio);


}
