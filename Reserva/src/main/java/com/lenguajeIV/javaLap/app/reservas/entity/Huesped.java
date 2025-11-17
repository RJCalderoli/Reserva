package com.lenguajeIV.javaLap.app.reservas.entity;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

@Getter
@Setter
@ToString(exclude = {"reservasResponsable", "itemsAsignados"})
@Entity
@Table(name = "huesped")
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHuesped; 
    @Column(length = 100, nullable = false)
    private String nombre;
    @Column(length = 100)
    private String apellido;
    @Column(length = 50, unique = true)
    private String documento;
    @Column(length = 150, unique = true)
    private String email;
    @Column(length = 250)
    private String direccion;
    private LocalDate fechaNacimiento; // Corresponde a fecha_nacimiento
    @Column(length = 100)
    private String nacionalidad;
    @Column(columnDefinition = "TEXT")
    private String observacion;

    // --- Relaciones ---

    // Un Huésped puede ser el "responsable" de muchas Reservas
    @OneToMany(mappedBy = "huespedResponsable")
    @JsonManagedReference("huesped-reserva")
    private Set<Reserva> reservasResponsable;

    // Un Huésped puede estar asignado a muchos items de reserva (habitaciones)
    @OneToMany(mappedBy = "huesped")
    @JsonManagedReference("huesped-item")
    private Set<ReservaItem> itemsAsignados;
}