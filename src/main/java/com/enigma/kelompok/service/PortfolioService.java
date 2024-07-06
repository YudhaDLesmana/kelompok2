package com.enigma.kelompok.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.enigma.kelompok.model.Portofolio;

public interface PortofolioService {
  Page<Portofolio> getAll(String name, Pageable pageable);

  Portofolio getOne(Integer id);

  Portofolio create(Portofolio request);

  void delete(Integer id);
}
