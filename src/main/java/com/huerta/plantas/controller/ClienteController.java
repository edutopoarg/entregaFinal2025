package com.huerta.plantas.controller;

import com.huerta.plantas.model.Cliente;
import com.huerta.plantas.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  // GET: Obtener todos los clientes
  @GetMapping
  public List<Cliente> getTodos() {
    return clienteService.obtenerTodos();
  }

  // GET: Obtener cliente por ID
  @GetMapping("/{id}")
  public ResponseEntity<Cliente> getPorId(@PathVariable Long id) {
    Optional<Cliente> cliente = clienteService.obtenerPorId(id);
    return cliente.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // POST: Agregar cliente nuevo
  @PostMapping
  public ResponseEntity<Cliente> crearCliente(@Valid @RequestBody Cliente cliente) {
    Cliente nuevoCliente = clienteService.agregarCliente(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
  }

  // POST: Agregar múltiples clientes de una vez
  @PostMapping("/batch")
  public ResponseEntity<List<Cliente>> crearVariosClientes(@Valid @RequestBody List<Cliente> clientes) {
    List<Cliente> clientesCreados = clienteService.agregarVarios(clientes);
    return ResponseEntity.status(HttpStatus.CREATED).body(clientesCreados);
  }

  // PUT: Actualizar cliente
  @PutMapping("/{id}")
  public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente clienteDetalles) {
    Cliente clienteActualizado = clienteService.actualizarCliente(id, clienteDetalles);
    if (clienteActualizado != null) {
      return ResponseEntity.ok(clienteActualizado);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // DELETE: Eliminar cliente por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
    boolean eliminado = clienteService.eliminarCliente(id);
    if (eliminado) {
      return ResponseEntity.ok("Cliente eliminado correctamente");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("El cliente con ID " + id + " no existe");
    }
  }
}
