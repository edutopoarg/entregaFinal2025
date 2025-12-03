package com.huerta.plantas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "cliente_id", nullable = false)
  @NotNull(message = "El cliente es obligatorio")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "planta_id", nullable = false)
  @NotNull(message = "La planta es obligatoria")
  private Planta planta;

  @NotNull(message = "La cantidad es obligatoria")
  @Min(value = 1, message = "La cantidad debe ser al menos 1")
  private Integer cantidad;

  @NotNull(message = "La fecha es obligatoria")
  private LocalDate fecha;

  public Pedido() {}

  public Pedido(Cliente cliente, Planta planta, Integer cantidad, LocalDate fecha) {
    this.cliente = cliente;
    this.planta = planta;
    this.cantidad = cantidad;
    this.fecha = fecha;
  }

  public Long getId() {
    return id;
  }

  public Cliente getCliente() {
    return cliente;
  }
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Planta getPlanta() {
    return planta;
  }
  public void setPlanta(Planta planta) {
    this.planta = planta;
  }

  public Integer getCantidad() {
    return cantidad;
  }
  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public LocalDate getFecha() {
    return fecha;
  }
  public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
  }
}