package com.enigma.kelompok.repository;

import com.enigma.kelompok.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer>,
        JpaSpecificationExecutor<Stock> {
}