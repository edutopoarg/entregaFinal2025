package com.huerta.plantas.service;

import com.huerta.plantas.model.Pedido;
import com.huerta.plantas.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository pedidoRepository;

  // Obtener todos los pedidos
  public List<Pedido> obtenerTodos() {
    return pedidoRepository.findAll();
  }

  // Obtener pedido por ID
  public Optional<Pedido> obtenerPorId(Long id) {
    return pedidoRepository.findById(id);
  }

  // Obtener pedidos de un cliente específico
  public List<Pedido> obtenerPorCliente(Long clienteId) {
    return pedidoRepository.findByClienteId(clienteId);
  }

  // Obtener pedidos de una planta específica
  public List<Pedido> obtenerPorPlanta(Long plantaId) {
    return pedidoRepository.findByPlantaId(plantaId);
  }

  // Agregar nuevo pedido
  public Pedido agregarPedido(Pedido pedido) {
    return pedidoRepository.save(pedido);
  }

  // Actualizar pedido existente
  public Pedido actualizarPedido(Long id, Pedido pedidoDetalles) {
    Optional<Pedido> pedidoOpt = pedidoRepository.findById(id);
    if (pedidoOpt.isPresent()) {
      Pedido pedido = pedidoOpt.get();
      pedido.setCliente(pedidoDetalles.getCliente());
      pedido.setPlanta(pedidoDetalles.getPlanta());
      pedido.setCantidad(pedidoDetalles.getCantidad());
      pedido.setFecha(pedidoDetalles.getFecha());
      return pedidoRepository.save(pedido);
    }
    return null;
  }

  // Eliminar pedido por ID
  public boolean eliminarPedido(Long id) {
    if (pedidoRepository.existsById(id)) {
      pedidoRepository.deleteById(id);
      return true;
    }
    return false;
  }
  // Agregar múltiples pedidos
  public List<Pedido> agregarVarios(List<Pedido> pedidos) {
    return pedidoRepository.saveAll(pedidos);
  }
}
