package com.huerta.plantas.controller;

import com.huerta.plantas.model.Pedido;
import com.huerta.plantas.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  // GET: Obtener todos los pedidos
  @GetMapping
  public List<Pedido> getTodos() {
    return pedidoService.obtenerTodos();
  }

  // GET: Obtener pedido por ID
  @GetMapping("/{id}")
  public ResponseEntity<Pedido> getPorId(@PathVariable Long id) {
    Optional<Pedido> pedido = pedidoService.obtenerPorId(id);
    return pedido.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // GET: Obtener pedidos de un cliente
  @GetMapping("/cliente/{clienteId}")
  public List<Pedido> getPorCliente(@PathVariable Long clienteId) {
    return pedidoService.obtenerPorCliente(clienteId);
  }

  // GET: Obtener pedidos de una planta
  @GetMapping("/planta/{plantaId}")
  public List<Pedido> getPorPlanta(@PathVariable Long plantaId) {
    return pedidoService.obtenerPorPlanta(plantaId);
  }

  // POST: Agregar pedido nuevo
  @PostMapping
  public ResponseEntity<Pedido> crearPedido(@Valid @RequestBody Pedido pedido) {
    Pedido nuevoPedido = pedidoService.agregarPedido(pedido);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
  }

  // POST: Agregar múltiples pedidos de una vez
  @PostMapping("/batch")
  public ResponseEntity<List<Pedido>> crearVariosPedidos(@Valid @RequestBody List<Pedido> pedidos) {
    List<Pedido> pedidosCreados = pedidoService.agregarVarios(pedidos);
    return ResponseEntity.status(HttpStatus.CREATED).body(pedidosCreados);
  }

  // PUT: Actualizar pedido
  @PutMapping("/{id}")
  public ResponseEntity<Pedido> actualizarPedido(@PathVariable Long id, @Valid @RequestBody Pedido pedidoDetalles) {
    Pedido pedidoActualizado = pedidoService.actualizarPedido(id, pedidoDetalles);
    if (pedidoActualizado != null) {
      return ResponseEntity.ok(pedidoActualizado);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // DELETE: Eliminar pedido por ID
  @DeleteMapping("/{id}")
  public ResponseEntity<String> eliminarPedido(@PathVariable Long id) {
    boolean eliminado = pedidoService.eliminarPedido(id);
    if (eliminado) {
      return ResponseEntity.ok("Pedido eliminado correctamente");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body("El pedido con ID " + id + " no existe");
    }
  }
}