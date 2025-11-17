package com.lenguajeIV.javaLap.app.reservas.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.lenguajeIV.javaLap.app.reservas.entity.Reserva;
import com.lenguajeIV.javaLap.app.reservas.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping("/listar")
    public List<Reserva> listar() {
        return reservaService.findAll();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(reservaService.findById(id));
    }


    @PostMapping("/guardar")
    public ResponseEntity<Reserva> guardar(@Valid @RequestBody Reserva reserva) {
        Reserva reservaGuardada = reservaService.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaGuardada);
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Reserva> actualizar(@PathVariable Integer id, @Valid @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.update(id, reserva));
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    

    @GetMapping("/buscar/situacion")
    public List<Reserva> buscarPorSituacion(@RequestParam("filtro") Integer situacion) {
        return reservaService.findBySituacion(situacion);
    }

    @GetMapping("/buscar/huesped")
    public List<Reserva> buscarPorIdHuesped(@RequestParam("id") Integer idHuesped) {
        return reservaService.findByIdHuesped(idHuesped);
    }
    
    @GetMapping("/buscar/fechas")
    public List<Reserva> buscarPorFechas(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return reservaService.findByFechas(inicio, fin);
    }

    @GetMapping("/buscar/jpql")
    public ResponseEntity<Page<Reserva>> buscarPorFiltroJPQL(
            @RequestParam(name = "situacion", required = false) Integer situacion,
            @RequestParam(name = "filtro", required = false) String filtro,
            Pageable pageable) {
        
        Page<Reserva> reservas = reservaService.searchByFilter(situacion, filtro, pageable);
        return ResponseEntity.ok(reservas);
    }
    
    @GetMapping("/buscar/nativo")
    public ResponseEntity<Page<Reserva>> buscarPorFiltroNativo(
            @RequestParam(name = "situacion", required = false) Integer situacion,
            @RequestParam(name = "filtro", required = false) String filtro,
            Pageable pageable) {
        
        Page<Reserva> reservas = reservaService.searchByFilterNative(situacion, filtro, pageable);
        return ResponseEntity.ok(reservas);
    }
}