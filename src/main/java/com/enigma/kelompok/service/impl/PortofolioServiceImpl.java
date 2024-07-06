package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.Portofolio;
import com.enigma.kelompok.model.User;
import com.enigma.kelompok.repository.PortofolioRepository;
import com.enigma.kelompok.service.PortofolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortofolioServiceImpl implements PortofolioService {
    private final PortofolioRepository portofolioRepository;

    @Override
    public Page<Portofolio> getAll(Integer totalAmount,Pageable pageable) {
        return portofolioRepository.findAll(pageable);
    }

    @Override
    public Portofolio getOne(Integer id) {
        return portofolioRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public Portofolio update(Integer id, Portofolio request) {
        Portofolio portUpdate = this.getOne(id);
        portUpdate.setUser(request.getUser());
        portofolioRepository.save(portUpdate);
        return portUpdate;
    }

    @Override
    public Portofolio create(Portofolio request) {
        return portofolioRepository.save(request);
    }

    @Override
    public void delete(Integer id) {
        portofolioRepository.deleteById(id);
    }
}
