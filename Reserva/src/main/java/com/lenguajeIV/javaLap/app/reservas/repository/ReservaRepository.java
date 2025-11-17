package com.lenguajeIV.javaLap.app.reservas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lenguajeIV.javaLap.app.reservas.entity.Reserva;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
	List<Reserva> findBySituacion(Integer situacion);

    List<Reserva> findByHuespedResponsable_IdHuesped(Integer idHuesped);
    
    List<Reserva> findByFechaInicioBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
    @Query("SELECT r FROM Reserva r JOIN r.huespedResponsable h WHERE " +
            "(:situacion IS NULL OR r.situacion = :situacion) AND " +
            "(:filtro IS NULL OR :filtro = '' OR " +
            "LOWER(h.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(h.apellido) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(h.documento) LIKE LOWER(CONCAT('%', :filtro, '%')))")
     Page<Reserva> searchByFilter(
             @Param("situacion") Integer situacion,
             @Param("filtro") String filtro,
             Pageable pageable);
    
    @Query(value = "SELECT r.* FROM reserva r " +
            "JOIN huesped h ON r.id_huesped_responsable = h.id_huesped " +
            "WHERE " +
            "(:situacion IS NULL OR r.situacion = :situacion) AND " +
            "(:filtro IS NULL OR :filtro = '' OR " +
            "LOWER(h.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(h.apellido) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(h.documento) LIKE LOWER(CONCAT('%', :filtro, '%')))",
    countQuery = "SELECT count(*) FROM reserva r " +
                 "JOIN huesped h ON r.id_huesped_responsable = h.id_huesped " +
                 "WHERE " +
                 "(:situacion IS NULL OR r.situacion = :situacion) AND " +
                 "(:filtro IS NULL OR :filtro = '' OR " +
                 "LOWER(h.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
                 "LOWER(h.apellido) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
                 "LOWER(h.documento) LIKE LOWER(CONCAT('%', :filtro, '%')))",
    nativeQuery = true)
Page<Reserva> searchByFilterNative(
     @Param("situacion") Integer situacion,
     @Param("filtro") String filtro,
     Pageable pageable);
}

