package com.huerta.plantas.repository;

import com.huerta.plantas.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

  // Métodos personalizados útiles
  List<Pedido> findByClienteId(Long clienteId);
  List<Pedido> findByPlantaId(Long plantaId);
}