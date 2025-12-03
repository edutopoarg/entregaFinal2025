package com.huerta.plantas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;



@Entity
public class Planta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "El nombre es obligatorio")
  private String nombre;

  @NotNull(message = "El precio es obligatorio")
  @Min(value = 1, message = "El precio debe ser mayor que cero")
  private Double precio;

  public Planta() {}

  public Planta(String nombre, Double precio) {
    this.nombre = nombre;
    this.precio = precio;
  }

  public Long getId() { return id; }

  public String getNombre()
  { return nombre; }
  public void setNombre(String nombre)
  { this.nombre = nombre; }

  public Double getPrecio() { return precio; }
  public void setPrecio(Double precio) { this.precio = precio; }
}
