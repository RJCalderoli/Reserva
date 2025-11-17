package com.lenguajeIV.javaLap.app.reservas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.lenguajeIV.javaLap.app.reservas.entity.Cuarto;
import com.lenguajeIV.javaLap.app.reservas.service.CuartoService;

@RestController
@RequestMapping("/cuartos")
public class CuartoController {

    @Autowired
    private CuartoService cuartoService;

    @GetMapping("/listar")
    public List<Cuarto> listar() {
        return cuartoService.findAll();
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<Cuarto> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(cuartoService.findById(id));
    }


    @PostMapping("/guardar")
    public ResponseEntity<Cuarto> guardar(@Valid @RequestBody Cuarto cuarto) {
        Cuarto cuartoGuardado = cuartoService.save(cuarto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cuartoGuardado);
    }


    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Cuarto> actualizar(@PathVariable Integer id, @Valid @RequestBody Cuarto cuarto) {
        // El service (con su lógica de "fusión") se encarga de todo.
        return ResponseEntity.ok(cuartoService.update(id, cuarto));
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        cuartoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/buscar/tipo")
    public List<Cuarto> buscarPorTipoCuarto(@RequestParam("filtro") String filtro) {
        return cuartoService.findByTipoCuarto(filtro);
    }

    @GetMapping("/buscar/estado")
    public List<Cuarto> buscarPorEstado(@RequestParam("filtro") String filtro) {
        return cuartoService.findByEstado(filtro);
    }
    
    @GetMapping("/buscar/vista")
    public List<Cuarto> buscarPorVista(@RequestParam("filtro") String filtro) {
        return cuartoService.findByVista(filtro);
    }


    @GetMapping("/buscar/jpql")
    public ResponseEntity<Page<Cuarto>> buscarPorFiltroJPQL(
            @RequestParam(name = "filtro", required = false) String filtro,
            Pageable pageable) {
        
        Page<Cuarto> cuartos = cuartoService.searchByFilter(filtro, pageable);
        return ResponseEntity.ok(cuartos);
    }
    
    @GetMapping("/buscar/nativo")
    public ResponseEntity<Page<Cuarto>> buscarPorFiltroNativo(
            @RequestParam(name = "filtro", required = false) String filtro,
            Pageable pageable) {
        
        Page<Cuarto> cuartos = cuartoService.searchByFilterNative(filtro, pageable);
        return ResponseEntity.ok(cuartos);
    }
}