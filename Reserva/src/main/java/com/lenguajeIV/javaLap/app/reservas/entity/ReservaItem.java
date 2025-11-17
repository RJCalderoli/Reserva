package com.lenguajeIV.javaLap.app.reservas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
@Table(name = "reserva_item")
public class ReservaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItem; // Corresponde a id_item

    private Double precioNoche; // Corresponde a precio_noche
    private Double descuento;
    private Double subtotal;

    // --- Relaciones ---

    // Muchos Items pertenecen a UNA Reserva
    @ManyToOne
    @JoinColumn(name = "id_reserva", nullable = false)
    private Reserva reserva;

    // Muchos Items apuntan a UN Cuarto (habitación)
    @ManyToOne
    @JoinColumn(name = "id_cuarto", nullable = false)
    private Cuarto cuarto;

    // Muchos Items apuntan a UN Huesped (el que se queda en esa habitación)
    @ManyToOne
    @JoinColumn(name = "id_huesped", nullable = false)
    private Huesped huesped;
}