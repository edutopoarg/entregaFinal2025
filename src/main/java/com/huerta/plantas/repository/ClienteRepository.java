package com.huerta.plantas.repository;

import com.huerta.plantas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
  // JpaRepository ya provee los métodos básicos:
  // findAll(), findById(), save(), deleteById(), etc.
}