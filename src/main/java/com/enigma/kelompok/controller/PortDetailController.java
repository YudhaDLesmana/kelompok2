package com.enigma.kelompok.controller;

import com.enigma.kelompok.model.PortDetail;
import com.enigma.kelompok.service.PortDetailService;
import com.enigma.kelompok.utils.response.PageWrapper;
import com.enigma.kelompok.utils.response.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/port_detail")
@RequiredArgsConstructor
public class PortDetailController {
    private final PortDetailService portDetailService;

    @GetMapping
    ResponseEntity<?> getAllPortDetail(
            @PageableDefault(page = 0, size = 10) Pageable pageable
    ) {
        Page<PortDetail> res = portDetailService.getAll(pageable);
        PageWrapper<PortDetail> result = new PageWrapper<>(res);
        return Res.renderJson(
                result,
                "FOUND",
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public PortDetail getOne(@PathVariable Integer id) {
        return portDetailService.getOne(id);
    }
}
