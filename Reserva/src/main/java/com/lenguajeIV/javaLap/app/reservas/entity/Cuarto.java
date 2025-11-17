package com.lenguajeIV.javaLap.app.reservas.entity;

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
@ToString(exclude = "itemsReserva")
@Entity
@Table(name = "cuarto")
public class Cuarto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCuarto; 
    @Column(length = 20)
    private String numeroCuarto; 
    @Column(length = 100)
    private String tipoCuarto; 
    private Double precioDiario; 
    private Integer cantidadCamas; 
    private Integer capacidadMaxima; 
    @Column(length = 50)
    private String estado; 
    private Integer piso;
    @Column(length = 100)
    private String vista;

    // Un Cuarto (habitación) puede estar en muchos items de reserva
    @OneToMany(mappedBy = "cuarto")
    @JsonManagedReference("cuarto-item")
    private Set<ReservaItem> itemsReserva;
}
