package com.servidor.pasteleria.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.servidor.pasteleria.entity.EmpleadoEntity;

@Repository
public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
    // JpaRepository ya incluye métodos para CRUD:
    // save() - Crear/Actualizar
    // findById() - Leer por ID
    // findAll() - Leer todos
    // deleteById() - Eliminar por ID
	
	// Consulta JPQL con parámetro nombrado
	@Query("SELECT em FROM EmpleadoEntity em WHERE em.usuario = :usuario")
	EmpleadoEntity buscarPorUsuario(@Param("usuario") String usuario);


    
}
