package com.enigma.kelompok.controller;

import com.enigma.kelompok.model.Portfolio;
import com.enigma.kelompok.service.PortDetailService;
import com.enigma.kelompok.service.PortfolioService;
import com.enigma.kelompok.utils.dto.PortDetailDTO;
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
@RequestMapping("/portofolio")
@RequiredArgsConstructor
public class PortofolioController {
    private final PortfolioService portofolioService;
    private final PortDetailService portDetail;

    @PostMapping("/buy")
    public ResponseEntity<?> buy (@RequestBody PortDetailDTO req){
        return Res.renderJson(
                portDetail.create(req),"Success buy", HttpStatus.CREATED
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody PortDetailDTO req){
        return Res.renderJson(
                portofolioService.create(req.getPortfolio_id(),req),"Create",HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public Portfolio getOne(@PathVariable Integer id){
        return portofolioService.getOne(id);
    }

    public ResponseEntity<?> getAll(@RequestParam (required = false)Integer totalAmount,
                                    @PageableDefault(page = 0,size = 10)Pageable pageable){
        Page<Portfolio> res = portofolioService.getAll(pageable);
        PageWrapper<Portfolio> result = new PageWrapper<>(res);
        return Res.renderJson(
                result,"found",HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public Portfolio update(@PathVariable Integer id,@RequestBody PortDetailDTO req){
        return portofolioService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        portofolioService.delete(id);
    }
}
