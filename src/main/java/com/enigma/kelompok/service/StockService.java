package com.enigma.kelompok.service;

import com.enigma.kelompok.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockService {

    Page<Stock> getAll(String name, Pageable pageable);

    Stock getOne(Integer id);
    Stock create(Stock req);
    Stock update(Integer id, Stock req);
    void delete(Integer id);
}