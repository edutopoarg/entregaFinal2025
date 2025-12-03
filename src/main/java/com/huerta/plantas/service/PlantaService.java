package com.huerta.plantas.service;

import com.huerta.plantas.model.Planta;
import com.huerta.plantas.repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantaService {

  @Autowired
  private PlantaRepository plantaRepository;

  // Obtener todas las plantas
  public List<Planta> obtenerTodas() {
    return plantaRepository.findAll();
  }

  // Obtener planta por ID
  public Optional<Planta> obtenerPorId(Long id) {
    return plantaRepository.findById(id);
  }

  // Agregar nueva planta
  public Planta agregarPlanta(Planta planta) {
    return plantaRepository.save(planta);
  }

  // Agregar múltiples plantas (BATCH)
  public List<Planta> agregarVarias(List<Planta> plantas) {
    return plantaRepository.saveAll(plantas);
  }

  // Actualizar planta existente
  public Planta actualizarPlanta(Long id, Planta plantaDetalles) {
    Optional<Planta> plantaOpt = plantaRepository.findById(id);
    if (plantaOpt.isPresent()) {
      Planta planta = plantaOpt.get();
      planta.setNombre(plantaDetalles.getNombre());
      planta.setPrecio(plantaDetalles.getPrecio());
      return plantaRepository.save(planta);
    }
    return null;
  }

  // Eliminar planta por ID
  public boolean eliminarPlanta(Long id) {
    if (plantaRepository.existsById(id)) {
      plantaRepository.deleteById(id);
      return true;
    }
    return false;
  }
}


