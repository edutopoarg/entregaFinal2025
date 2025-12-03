package com.huerta.plantas.controller;

import com.huerta.plantas.model.Cliente;
import com.huerta.plantas.model.Planta;
import com.huerta.plantas.service.PlantaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plantas")
public class PlantaController {

  @Autowired
  private PlantaService plantaService;

  // GET: Obtener todas las plantas
  @GetMapping
  public List<Planta> getTodas() {
    return plantaService.obtenerTodas();
  }

  // GET: Obtener planta por ID
  /*@GetMapping("/{id}")
  public Optional<Planta> getPorId(@PathVariable Long id) {
    return plantaService.obtenerPorId(id);
  }*/
  @GetMapping("/{id}")
  public ResponseEntity<Planta> getPorId(@PathVariable Long id) {
    Optional<Planta> planta = plantaService.obtenerPorId(id);
    return planta.map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  // POST: Agregar planta nueva
  @PostMapping
  public ResponseEntity<Planta> crearPlanta(@Valid @RequestBody Planta planta) {
    // 1. Llama al servicio y guarda el resultado (que es un objeto Planta)
    Planta nuevaPlanta = plantaService.agregarPlanta(planta);

    // 2. Envuelve ese resultado en un ResponseEntity con el estado 201 Created
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPlanta);
  }

  // Agregar en PlantaService.java
  @PostMapping("/batch")
  public ResponseEntity<List<Planta>> crearVariasPlantas(@Valid @RequestBody List<Planta> plantas) {
    List<Planta> plantasCreadas = plantaService.agregarVarias(plantas);
    return ResponseEntity.status(HttpStatus.CREATED).body(plantasCreadas);
  }





  // PUT: Actualizar planta
  /*@PutMapping("/{id}")
    public Planta actualizarPlanta(@PathVariable Long id, @Valid @RequestBody Planta planta) {
    return plantaService.actualizarPlanta(id, planta);
  }*/
    @PutMapping("/{id}")
    public ResponseEntity<Planta> actualizarPlanta(@PathVariable Long id, @Valid @RequestBody Planta plantaDetalles) {

      // Llama al servicio, que intentará actualizar.
      // El servicio DEBE devolver la planta actualizada o 'null' si no se encuentra el ID.
      Planta plantaActualizada = plantaService.actualizarPlanta(id, plantaDetalles);
      if (plantaActualizada != null) {
        // Si se encontró y actualizó, devuelve 200 OK con el objeto
        return ResponseEntity.ok(plantaActualizada);
      } else {
        // Si la planta con ese ID no existe, devuelve 404 Not Found
        return ResponseEntity.notFound().build();
      }
    }


  // DELETE: Eliminar planta por ID
  @DeleteMapping("/{id}")
  public String eliminarPlanta(@PathVariable Long id) {
    boolean eliminado = plantaService.eliminarPlanta(id);
    if (eliminado) {
      return "Planta eliminada correctamente";
    } else {
      return "La planta con ID " + id + " no existe";
    }
  }
}

