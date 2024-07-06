package com.enigma.kelompok.service;

import com.enigma.kelompok.utils.dto.PortfolioRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.enigma.kelompok.model.Portfolio;

public interface PortfolioService {
  Page<Portfolio> getAll(Pageable pageable);

  Portfolio getOne(Integer id);

  Portfolio create(PortfolioRequestDTO request);

  void delete(Integer id);
}
