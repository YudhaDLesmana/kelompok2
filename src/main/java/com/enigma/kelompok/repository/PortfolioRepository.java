package com.enigma.kelompok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enigma.kelompok.model.Portfolio;

@Repository

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {

}
