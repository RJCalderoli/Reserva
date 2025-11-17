package com.lenguajeIV.javaLap.app.reservas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.lenguajeIV.javaLap.app.reservas.entity.Huesped;
import com.lenguajeIV.javaLap.app.reservas.exceptions.ResourceNotFoundException;
import com.lenguajeIV.javaLap.app.reservas.repository.HuespedRepository;

@Service
public class HuespedService {

    @Autowired
    private HuespedRepository huespedRepository;

    public List<Huesped> findAll() {
        return huespedRepository.findAll();
    }


    public Huesped findById(Integer id) {
        return huespedRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Huésped no encontrado con ID: " + id));
    }

    public Huesped save(Huesped huesped) {
        return huespedRepository.save(huesped);
    }

    public Huesped update(Integer id, Huesped huespedNuevosDatos) {
        
        Huesped huespedExistente = findById(id); 

        if (huespedNuevosDatos.getNombre() != null) {
            huespedExistente.setNombre(huespedNuevosDatos.getNombre());
        }
        if (huespedNuevosDatos.getApellido() != null) {
            huespedExistente.setApellido(huespedNuevosDatos.getApellido());
        }
        if (huespedNuevosDatos.getDocumento() != null) {
            huespedExistente.setDocumento(huespedNuevosDatos.getDocumento());
        }
        if (huespedNuevosDatos.getEmail() != null) {
            huespedExistente.setEmail(huespedNuevosDatos.getEmail());
        }
        if (huespedNuevosDatos.getDireccion() != null) {
            huespedExistente.setDireccion(huespedNuevosDatos.getDireccion());
        }
        if (huespedNuevosDatos.getFechaNacimiento() != null) {
            huespedExistente.setFechaNacimiento(huespedNuevosDatos.getFechaNacimiento());
        }
        if (huespedNuevosDatos.getNacionalidad() != null) {
            huespedExistente.setNacionalidad(huespedNuevosDatos.getNacionalidad());
        }
        if (huespedNuevosDatos.getObservacion() != null) {
            huespedExistente.setObservacion(huespedNuevosDatos.getObservacion());
        }

        return huespedRepository.save(huespedExistente);
    }

    public void delete(Integer id) {
        Huesped huesped = findById(id); 

        huespedRepository.delete(huesped);
    }
    
    public List<Huesped> findByNombre(String nombre) {
        return huespedRepository.findByNombreIgnoreCase(nombre);
    }

    public List<Huesped> findByApellido(String apellido) {
        return huespedRepository.findByApellidoIgnoreCase(apellido);
    }
    
    public List<Huesped> findByNacionalidad(String nacionalidad) {
        return huespedRepository.findByNacionalidadIgnoreCase(nacionalidad);
    }
    
    
    public Page<Huesped> searchByFilter(String filtro, Pageable pageable) {
        return huespedRepository.searchByFilter(filtro, pageable);
    }
    
    public Page<Huesped> searchByFilterNative(String filtro, Pageable pageable) {
        return huespedRepository.searchByFilterNative(filtro, pageable);
    }
}