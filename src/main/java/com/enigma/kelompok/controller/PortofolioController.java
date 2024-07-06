package com.enigma.kelompok.controller;

import com.enigma.kelompok.model.Portofolio;
import com.enigma.kelompok.service.PortDetailService;
import com.enigma.kelompok.service.PortofolioService;
import com.enigma.kelompok.utils.DTO.PortDetailDTO;
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
    private final PortofolioService portofolioService;
    private final PortDetailService portDetail;

    @PostMapping("/buy")
    public ResponseEntity<?> buy (@RequestBody PortDetailDTO req){
        return Res.renderJson(
                portDetail.create(req),"Success buy", HttpStatus.CREATED
        );
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Portofolio req){
        return Res.renderJson(
                portofolioService.create(req),"Create",HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public Portofolio getOne(@PathVariable Integer id){
        return portofolioService.getOne(id);
    }

    public ResponseEntity<?> getAll(@RequestParam (required = false)Integer totalAmount,
                                    @PageableDefault(page = 0,size = 10)Pageable pageable){
        Page<Portofolio> res = portofolioService.getAll(totalAmount,pageable);
        PageWrapper<Portofolio> result = new PageWrapper<>(res);
        return Res.renderJson(
                result,"found",HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public Portofolio update(@PathVariable Integer id,@RequestBody Portofolio req){
        return portofolioService.update(id, req);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        portofolioService.delete(id);
    }
}
