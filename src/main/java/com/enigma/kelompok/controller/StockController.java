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
    public ResponseEntity<?> create(@RequestBody Stock request) {
        return Res.renderJson(
                stockService.create(request),
                "Created",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String name,
            @PageableDefault(page = 0, size = 10)Pageable pageable
            ) {
        Page<Stock> res = stockService.getAll(name, pageable);
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
                "Found a user",
                HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public Stock update(@PathVariable Integer id, Stock request){
        return stockService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        stockService.delete(id);
    }

}
