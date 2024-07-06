
package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.Stock;
import com.enigma.kelompok.repository.StockRepository;
import com.enigma.kelompok.service.StockService;
import com.enigma.kelompok.utils.specification.StockSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;


    @Override
    public Page<Stock> getAll(String name, Pageable pageable) {
        Specification<Stock> specification = StockSpecification.getStockSpecification(name);
        return stockRepository.findAll(specification, pageable);
    }

    @Override
    public Stock getOne(Integer id) {
        return stockRepository.findById(id).orElseThrow(() -> new RuntimeException("Stock not found"));
    }

    @Override
    public Stock create(Stock req) {
        return stockRepository.save(req);
    }

    @Override
    public Stock update(Integer id, Stock req) {
        Stock stock = this.getOne(id);
        stock.setName(req.getName());
        stockRepository.save(stock);
        return stock;
    }

    @Override
    public void delete(Integer id) {
        stockRepository.deleteById(id);

    }
}
