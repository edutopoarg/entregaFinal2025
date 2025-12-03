package com.huerta.plantas.service;

import com.huerta.plantas.model.Cliente;
import com.huerta.plantas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  // Obtener todos los clientes
  public List<Cliente> obtenerTodos() {
    return clienteRepository.findAll();
  }

  // Obtener cliente por ID
  public Optional<Cliente> obtenerPorId(Long id) {
    return clienteRepository.findById(id);
  }

  // Agregar nuevo cliente
  public Cliente agregarCliente(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  // Actualizar cliente existente
  public Cliente actualizarCliente(Long id, Cliente clienteDetalles) {
    Optional<Cliente> clienteOpt = clienteRepository.findById(id);
    if (clienteOpt.isPresent()) {
      Cliente cliente = clienteOpt.get();
      cliente.setNombre(clienteDetalles.getNombre());
      cliente.setApellido(clienteDetalles.getApellido());
      cliente.setLugarResidencia(clienteDetalles.getLugarResidencia());
      return clienteRepository.save(cliente);
    }
    return null; // Retorna null si no se encuentra el cliente
  }

  // Eliminar cliente por ID
  public boolean eliminarCliente(Long id) {
    if (clienteRepository.existsById(id)) {
      clienteRepository.deleteById(id);
      return true;
    }
    return false;
  }
  // Agregar múltiples clientes
  public List<Cliente> agregarVarios(List<Cliente> clientes) {
    return clienteRepository.saveAll(clientes);
  }
}
