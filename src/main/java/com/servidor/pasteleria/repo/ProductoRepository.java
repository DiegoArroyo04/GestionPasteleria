package com.servidor.pasteleria.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.servidor.pasteleria.entity.ProductoEntity;



@Repository
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
	 // JpaRepository ya incluye métodos para CRUD:
    // save() - Crear/Actualizar
    // findById() - Leer por ID
    // findAll() - Leer todos
    // deleteById() - Eliminar por ID
}
