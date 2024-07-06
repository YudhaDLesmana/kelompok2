package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.PortDetail;
import com.enigma.kelompok.repository.PortDetailRepository;
import com.enigma.kelompok.service.PortDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PortDetailServiceImpl implements PortDetailService {
    private final PortDetailRepository portDetailRepository;

    @Override
    public PortDetail create(PortDetail request) {
        return portDetailRepository.save(request);
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Page<PortDetail> getAll(Pageable pageable) {
        return portDetailRepository.findAll(pageable);
    }


    @Override
    public PortDetail getOne(Integer id) {
        return portDetailRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "PortDetail not found"));
    }
}
