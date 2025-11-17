package com.lenguajeIV.javaLap.app.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lenguajeIV.javaLap.app.reservas.entity.ReservaItem;

@Repository
public interface ReservaItemRepository extends JpaRepository<ReservaItem, Integer> {

}