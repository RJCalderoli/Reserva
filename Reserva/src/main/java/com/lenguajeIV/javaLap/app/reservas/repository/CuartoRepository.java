package com.lenguajeIV.javaLap.app.reservas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.lenguajeIV.javaLap.app.reservas.entity.Cuarto;
import java.util.List;

@Repository
public interface CuartoRepository extends JpaRepository<Cuarto, Integer> {
    
	List<Cuarto> findByTipoCuartoIgnoreCase(String tipoCuarto);


    List<Cuarto> findByEstadoIgnoreCase(String estado);
    

    List<Cuarto> findByVistaIgnoreCase(String vista);
    
    @Query("SELECT c FROM Cuarto c WHERE " +
            "(:filtro IS NULL OR :filtro = '') OR " +
            "(LOWER(c.numeroCuarto) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(c.tipoCuarto) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(c.vista) LIKE LOWER(CONCAT('%', :filtro, '%')))")
     Page<Cuarto> searchByFilter(@Param("filtro") String filtro, Pageable pageable);
    
    @Query(value = "SELECT * FROM cuarto c WHERE " +
            "(:filtro IS NULL OR :filtro = '') OR " +
            "(LOWER(c.numero_cuarto) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(c.tipo_cuarto) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
            "LOWER(c.vista) LIKE LOWER(CONCAT('%', :filtro, '%')))",
    countQuery = "SELECT count(*) FROM cuarto c WHERE " +
                 "(:filtro IS NULL OR :filtro = '') OR " +
                 "(LOWER(c.numero_cuarto) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
                 "LOWER(c.tipo_cuarto) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
                 "LOWER(c.vista) LIKE LOWER(CONCAT('%', :filtro, '%')))",
    nativeQuery = true)
Page<Cuarto> searchByFilterNative(@Param("filtro") String filtro, Pageable pageable);
    
}
