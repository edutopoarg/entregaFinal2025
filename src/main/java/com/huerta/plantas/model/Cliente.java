package com.huerta.plantas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "El nombre es obligatorio")
  @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
  private String nombre;

  @NotBlank(message = "El apellido es obligatorio")
  @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
  private String apellido;

  @NotBlank(message = "El lugar de residencia es obligatorio")
  @Size(min = 3, max = 100, message = "El lugar de residencia debe tener entre 3 y 100 caracteres")
  private String lugarResidencia;

  public Cliente() {}

  public Cliente(String nombre, String apellido, String lugarResidencia) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.lugarResidencia = lugarResidencia;
  }

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }
  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getLugarResidencia() {
    return lugarResidencia;
  }
  public void setLugarResidencia(String lugarResidencia) {
    this.lugarResidencia = lugarResidencia;
  }
}