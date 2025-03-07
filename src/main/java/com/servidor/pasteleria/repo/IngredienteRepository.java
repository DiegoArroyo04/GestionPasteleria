package com.servidor.pasteleria.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.servidor.pasteleria.entity.IngredienteEntity;

@Repository
public interface IngredienteRepository extends JpaRepository<IngredienteEntity, Long> {

	// JpaRepository ya incluye métodos para CRUD:
	// save() - Crear/Actualizar
	// findById() - Leer por ID
	// findAll() - Leer todos
	// deleteById() - Eliminar por ID

	// BUSCAR POR NOMBRE DE INGREDIENTE
	@Query("SELECT i FROM IngredienteEntity i WHERE i.nombre = :nombre")
	IngredienteEntity buscarPorNombre(@Param("nombre") String nombre);

}

