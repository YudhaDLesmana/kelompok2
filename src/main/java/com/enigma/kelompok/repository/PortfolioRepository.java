package com.enigma.kelompok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.enigma.kelompok.model.Portfolio;

import java.util.Optional;

@Repository

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    @Query(value = "SELECT * FROM portofolios WHERE user_id = ?1", nativeQuery = true)
    Optional<Portfolio> findByUserId(Integer id);
}
