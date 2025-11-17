package com.lenguajeIV.javaLap.app.reservas.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.lenguajeIV.javaLap.app.reservas.entity.Huesped;
import com.lenguajeIV.javaLap.app.reservas.service.HuespedService;

@RestController
@RequestMapping("/huespedes")
public class HuespedController {

    @Autowired
    private HuespedService huespedService;

    //Endpoint para "listar todos"
    //URL: GET http://localhost:8080/huespedes/listar
    @GetMapping("/listar")
    public List<Huesped> listar() {
        return huespedService.findAll();
    }

    //Endpoint para "buscar por código"
    //URL: GET http://localhost:8080/huespedes/buscar/1
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Huesped> buscarPorId(@PathVariable Integer id) {
        Huesped huesped = huespedService.findById(id);
        if (huesped == null) {
            // Si no se encuentra, devolvemos un 404 Not Found
            return ResponseEntity.notFound().build();
        }
        // Si se encuentra, devolvemos un 200 OK con el huésped
        return ResponseEntity.ok(huesped);
    }

    //Endpoint para "guardar" (Create)
    //URL: POST http://localhost:8080/huespedes/guardar
    @PostMapping("/guardar")
    public ResponseEntity<Huesped> guardar(@Valid @RequestBody Huesped huesped) {
        Huesped huespedGuardado = huespedService.save(huesped);
        // Devolvemos 201 Created con el huésped recién creado (con su ID)
        return ResponseEntity.status(HttpStatus.CREATED).body(huespedGuardado);
    }

    //Endpoint para "actualizar"
    //URL: PUT http://localhost:8080/huespedes/actualizar/1
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Huesped> actualizar(@PathVariable Integer id, @Valid @RequestBody Huesped huesped) {
        Huesped huespedActualizado = huespedService.update(id, huesped);
        if (huespedActualizado == null) {
            // Si no se encuentra, devolvemos 404 Not Found
            return ResponseEntity.notFound().build();
        }
        // Devolvemos 200 OK con el huésped actualizado
        return ResponseEntity.ok(huespedActualizado);
    }

    //Endpoint para "eliminado"
    //URL: DELETE http://localhost:8080/huespedes/eliminar/1
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Huesped huesped = huespedService.findById(id);
        if (huesped == null) {
            // Si no existe, 404 Not Found
            return ResponseEntity.notFound().build();
        }
        
        huespedService.delete(id);
        // Devolvemos 204 No Content (eliminación exitosa)
        return ResponseEntity.noContent().build();
    }
    
    //Endpoint para buscar por nombre
    //URL: GET http://localhost:8080/huespedes/buscar/nombre?filtro=Juan
    @GetMapping("/buscar/nombre")
    public List<Huesped> buscarPorNombre(@RequestParam("filtro") String filtro) {
        return huespedService.findByNombre(filtro);
    }

    //Endpoint para buscar por apellido
    //URL: GET http://localhost:8080/huespedes/buscar/apellido?filtro=Perez
    @GetMapping("/buscar/apellido")
    public List<Huesped> buscarPorApellido(@RequestParam("filtro") String filtro) {
        return huespedService.findByApellido(filtro);
    }
    
    //Endpoint para buscar por nacionalidad
    //URL: GET http://localhost:8080/huespedes/buscar/nacionalidad?filtro=Paraguaya
    @GetMapping("/buscar/nacionalidad")
    public List<Huesped> buscarPorNacionalidad(@RequestParam("filtro") String filtro) {
        return huespedService.findByNacionalidad(filtro);
    }
    
    @GetMapping("/buscar/jpql")
    public ResponseEntity<Page<Huesped>> buscarPorFiltroJPQL(
            @RequestParam(name = "filtro", required = false) String filtro,
            Pageable pageable) {
        
        Page<Huesped> huespedes = huespedService.searchByFilter(filtro, pageable);
        return ResponseEntity.ok(huespedes);
    }
    
    @GetMapping("/buscar/nativo")
    public ResponseEntity<Page<Huesped>> buscarPorFiltroNativo(
            @RequestParam(name = "filtro", required = false) String filtro,
            Pageable pageable) {
        
        Page<Huesped> huespedes = huespedService.searchByFilterNative(filtro, pageable);
        return ResponseEntity.ok(huespedes);
}
}
