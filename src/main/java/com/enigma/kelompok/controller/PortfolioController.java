package com.enigma.kelompok.controller;

import com.enigma.kelompok.model.PortDetail;
import com.enigma.kelompok.model.Portfolio;
import com.enigma.kelompok.service.PortDetailService;
import com.enigma.kelompok.service.PortfolioService;
import com.enigma.kelompok.utils.dto.PortfolioRequestDTO;
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
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController {
    private final PortfolioService portfolioService;

    @PostMapping("/{id}")
    public Portfolio create(
            @PathVariable Integer id,
            @RequestBody PortfolioRequestDTO portfolio
    ) {
        return portfolioService.create(id, portfolio);
    }

    @GetMapping
    ResponseEntity<?> getAll(
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        Page<Portfolio> res = portfolioService.getAll(pageable);
        PageWrapper<Portfolio> result = new PageWrapper<>(res);
        return Res.renderJson(
                result,
                "FOUND",
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public Portfolio getOne(@PathVariable Integer id) {
        return portfolioService.getOne(id);
    }
}
