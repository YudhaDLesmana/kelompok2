package com.enigma.kelompok.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.enigma.kelompok.model.PortDetail;

public interface PortDetailService {
  Page<PortDetail> getAll(Pageable pageable);

  PortDetail getOne(Integer id);

  PortDetail create(PortDetail request);

  void delete(Integer id);
}