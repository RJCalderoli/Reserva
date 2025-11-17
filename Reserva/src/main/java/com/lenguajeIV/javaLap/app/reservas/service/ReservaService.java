package com.lenguajeIV.javaLap.app.reservas.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenguajeIV.javaLap.app.reservas.entity.Reserva;
import com.lenguajeIV.javaLap.app.reservas.entity.ReservaItem;
import com.lenguajeIV.javaLap.app.reservas.exceptions.ResourceNotFoundException;
import com.lenguajeIV.javaLap.app.reservas.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;
    

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }


    public Reserva findById(Integer id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + id));
    }

    @Transactional
    public Reserva save(Reserva reserva) {
        
        if (reserva.getReservasItems() != null) {
            for (ReservaItem item : reserva.getReservasItems()) {
                item.setReserva(reserva);
            }
        }
        
        return reservaRepository.save(reserva);
    }

    @Transactional
    public Reserva update(Integer id, Reserva reservaNuevosDatos) {
        
        Reserva reservaExistente = findById(id); 

        if (reservaNuevosDatos.getIdUsuario() != null) {
            reservaExistente.setIdUsuario(reservaNuevosDatos.getIdUsuario());
        }
        if (reservaNuevosDatos.getFechaReserva() != null) {
            reservaExistente.setFechaReserva(reservaNuevosDatos.getFechaReserva());
        }
        if (reservaNuevosDatos.getFechaInicio() != null) {
            reservaExistente.setFechaInicio(reservaNuevosDatos.getFechaInicio());
        }
        if (reservaNuevosDatos.getFechaFin() != null) {
            reservaExistente.setFechaFin(reservaNuevosDatos.getFechaFin());
        }
        if (reservaNuevosDatos.getSituacion() != null) {
            reservaExistente.setSituacion(reservaNuevosDatos.getSituacion());
        }
        if (reservaNuevosDatos.getSubtotal() != null) {
            reservaExistente.setSubtotal(reservaNuevosDatos.getSubtotal());
        }
        if (reservaNuevosDatos.getDescuento() != null) {
            reservaExistente.setDescuento(reservaNuevosDatos.getDescuento());
        }
        if (reservaNuevosDatos.getTotal() != null) {
            reservaExistente.setTotal(reservaNuevosDatos.getTotal());
        }

        if (reservaNuevosDatos.getHuespedResponsable() != null) {
            reservaExistente.setHuespedResponsable(reservaNuevosDatos.getHuespedResponsable());
        }

        if (reservaNuevosDatos.getReservasItems() != null) {
            reservaExistente.getReservasItems().clear();
            
            for (ReservaItem itemNuevo : reservaNuevosDatos.getReservasItems()) {
                itemNuevo.setReserva(reservaExistente);
                reservaExistente.getReservasItems().add(itemNuevo);
            }
        }
        
        return reservaRepository.save(reservaExistente);
    }

    @Transactional
    public void delete(Integer id) {
        Reserva reserva = findById(id); 

        reservaRepository.delete(reserva);
    }

    public List<Reserva> findBySituacion(Integer situacion) {
        return reservaRepository.findBySituacion(situacion);
    }

    public List<Reserva> findByIdHuesped(Integer idHuesped) {
        return reservaRepository.findByHuespedResponsable_IdHuesped(idHuesped);
    }
    
    public List<Reserva> findByFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return reservaRepository.findByFechaInicioBetween(fechaInicio, fechaFin);
    }

    public Page<Reserva> searchByFilter(Integer situacion, String filtro, Pageable pageable) {
        return reservaRepository.searchByFilter(situacion, filtro, pageable);
    }
    
    public Page<Reserva> searchByFilterNative(Integer situacion, String filtro, Pageable pageable) {
        return reservaRepository.searchByFilterNative(situacion, filtro, pageable);
    }
}
