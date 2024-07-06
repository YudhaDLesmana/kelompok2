package com.enigma.kelompok.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enigma.kelompok.model.Portofolio;

@Repository

public interface PortofolioRepository extends JpaRepository<Portofolio, Integer> {


}
