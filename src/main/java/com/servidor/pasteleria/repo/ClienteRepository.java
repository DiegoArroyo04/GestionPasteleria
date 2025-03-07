package com.servidor.pasteleria.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servidor.pasteleria.entity.ClienteEntity;
import com.servidor.pasteleria.entity.EmpleadoEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    // JpaRepository ya incluye métodos para CRUD:
    // save() - Crear/Actualizar
    // findById() - Leer por ID
    // findAll() - Leer todos
    // deleteById() - Eliminar por ID
	
	// Consulta JPQL con parámetro nombrado
	@Query("SELECT cli FROM ClienteEntity cli WHERE cli.usuario = :usuario")
	ClienteEntity buscarPorUsuario(@Param("usuario") String usuario);
	
	
}




