
package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.Stock;
import com.enigma.kelompok.repository.StockRepository;
import com.enigma.kelompok.service.StockService;
import com.enigma.kelompok.utils.specification.StockSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    @Override
    public Stock create(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Page<Stock> getAll(String name, Integer price, Pageable pageable) {
        Specification<Stock> specification = StockSpecification.getStockSpecification(name, price);
        return stockRepository.findAll(specification, pageable);
    }

    @Override
    public Stock getOne(Integer id) {
        return stockRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));
    }

    @Override
    public Stock update(Integer id, Stock stock) {
        Stock stockUpdated = this.getOne(id);
        stockUpdated.setName(stock.getName());
        stockUpdated.setCode(stock.getCode());
        stockUpdated.setPrice(stock.getPrice());
        stockRepository.save(stockUpdated);
        return stockUpdated;
    }

    @Override
    public void delete(Integer id) {
        stockRepository.deleteById(id);
    }
}
