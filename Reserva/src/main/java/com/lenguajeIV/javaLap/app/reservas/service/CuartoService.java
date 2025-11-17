package com.lenguajeIV.javaLap.app.reservas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.lenguajeIV.javaLap.app.reservas.entity.Cuarto;
import com.lenguajeIV.javaLap.app.reservas.exceptions.ResourceNotFoundException;
import com.lenguajeIV.javaLap.app.reservas.repository.CuartoRepository;

@Service
public class CuartoService {

    @Autowired
    private CuartoRepository cuartoRepository;


    public List<Cuarto> findAll() {
        return cuartoRepository.findAll();
    }


    public Cuarto findById(Integer id) {
        return cuartoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuarto no encontrado con ID: " + id));
    }


    public Cuarto save(Cuarto cuarto) {
        return cuartoRepository.save(cuarto);
    }


    public Cuarto update(Integer id, Cuarto cuartoNuevosDatos) {
        
        Cuarto cuartoExistente = findById(id); 

        if (cuartoNuevosDatos.getNumeroCuarto() != null) {
            cuartoExistente.setNumeroCuarto(cuartoNuevosDatos.getNumeroCuarto());
        }
        if (cuartoNuevosDatos.getTipoCuarto() != null) {
            cuartoExistente.setTipoCuarto(cuartoNuevosDatos.getTipoCuarto());
        }
        if (cuartoNuevosDatos.getPrecioDiario() != null) {
            cuartoExistente.setPrecioDiario(cuartoNuevosDatos.getPrecioDiario());
        }
        if (cuartoNuevosDatos.getCantidadCamas() != null) {
            cuartoExistente.setCantidadCamas(cuartoNuevosDatos.getCantidadCamas());
        }
        if (cuartoNuevosDatos.getCapacidadMaxima() != null) {
            cuartoExistente.setCapacidadMaxima(cuartoNuevosDatos.getCapacidadMaxima());
        }
        if (cuartoNuevosDatos.getEstado() != null) {
            cuartoExistente.setEstado(cuartoNuevosDatos.getEstado());
        }
        if (cuartoNuevosDatos.getPiso() != null) {
            cuartoExistente.setPiso(cuartoNuevosDatos.getPiso());
        }
        if (cuartoNuevosDatos.getVista() != null) {
            cuartoExistente.setVista(cuartoNuevosDatos.getVista());
        }

        return cuartoRepository.save(cuartoExistente);
    }


    public void delete(Integer id) {
        Cuarto cuarto = findById(id); 

        cuartoRepository.delete(cuarto);
    }


    public List<Cuarto> findByTipoCuarto(String tipoCuarto) {
        return cuartoRepository.findByTipoCuartoIgnoreCase(tipoCuarto);
    }

    public List<Cuarto> findByEstado(String estado) {
        return cuartoRepository.findByEstadoIgnoreCase(estado);
    }
    
    public List<Cuarto> findByVista(String vista) {
        return cuartoRepository.findByVistaIgnoreCase(vista);
    }


    public Page<Cuarto> searchByFilter(String filtro, Pageable pageable) {
        return cuartoRepository.searchByFilter(filtro, pageable);
    }
    
    public Page<Cuarto> searchByFilterNative(String filtro, Pageable pageable) {
        return cuartoRepository.searchByFilterNative(filtro, pageable);
    }
}

