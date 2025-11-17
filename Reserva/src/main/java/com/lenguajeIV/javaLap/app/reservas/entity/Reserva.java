package com.lenguajeIV.javaLap.app.reservas.entity;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;

@Getter
@Setter
@ToString(exclude = {"reservasItems", "huespedResponsable"})
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReserva; 
    private Integer idUsuario;
    private LocalDate fechaReserva; 
    private LocalDate fechaInicio; 
    private LocalDate fechaFin; 
    private Integer situacion; 
    private Double subtotal;
    private Double descuento;
    private Double total;
    private Integer cantidadAdultos;
    private Integer cantidadNinos;
    private Integer cantidadNoches;

    // Muchas Reservas pertenecen a un Huesped (el responsable)
    @ManyToOne
    @JoinColumn(name = "id_huesped_responsable", nullable = false)
    @JsonBackReference("huesped-reserva")
    private Huesped huespedResponsable;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("reserva-item")
    private Set<ReservaItem> reservasItems;
    
}
