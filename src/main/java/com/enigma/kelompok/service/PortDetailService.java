package com.enigma.kelompok.service;

import com.enigma.kelompok.utils.DTO.PortDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.enigma.kelompok.model.PortDetail;

public interface PortDetailService {
  Page<PortDetail> getAll(Pageable pageable);

  PortDetail getOne(Integer id);

  PortDetail create(PortDetailDTO request);

  void delete(Integer id);
}
