package com.enigma.kelompok.controller;

import com.enigma.kelompok.model.Stock;
import com.enigma.kelompok.service.StockService;
import com.enigma.kelompok.utils.response.PageWrapper;
import com.enigma.kelompok.utils.response.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Stock stock) {
        return Res.renderJson(
                stockService.create(stock),
                "CREATED",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer price,
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        Page<Stock> res = stockService.getAll(name, price, pageable);
        PageWrapper<Stock> result = new PageWrapper<>(res);
        return Res.renderJson(
                result,
                "FOUND",
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return Res.renderJson(
                stockService.getOne(id),
                "FOUND A STOCK",
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Stock stock) {
        return Res.renderJson(
                stockService.update(id, stock),
                "STOCK IS UPDATED",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        stockService.delete(id);
    }
}
