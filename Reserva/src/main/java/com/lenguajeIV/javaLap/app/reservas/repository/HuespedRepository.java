package com.lenguajeIV.javaLap.app.reservas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lenguajeIV.javaLap.app.reservas.entity.Huesped;
import java.util.List;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Integer> {
    
	List<Huesped> findByNombreIgnoreCase(String nombre);


    List<Huesped> findByApellidoIgnoreCase(String apellido);
    

    List<Huesped> findByNacionalidadIgnoreCase(String nacionalidad);
    
    @Query("SELECT h FROM Huesped h WHERE " +
            "(:filtro IS NULL OR :filtro = '') OR " +
            "(LOWER(h.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(h.apellido) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(h.documento) LIKE LOWER(CONCAT('%', :filtro, '%')))")
     Page<Huesped> searchByFilter(@Param("filtro") String filtro, Pageable pageable);
    
    @Query(value = "SELECT * FROM huesped h WHERE " +
            "(:filtro IS NULL OR :filtro = '') OR " +
            "(LOWER(h.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(h.apellido) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(h.documento) LIKE LOWER(CONCAT('%', :filtro, '%')))",
    countQuery = "SELECT count(*) FROM huesped h WHERE " +
                 "(:filtro IS NULL OR :filtro = '') OR " +
                 "(LOWER(h.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
                 "LOWER(h.apellido) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
                 "LOWER(h.documento) LIKE LOWER(CONCAT('%', :filtro, '%')))",
    nativeQuery = true)
Page<Huesped> searchByFilterNative(@Param("filtro") String filtro, Pageable pageable);

}