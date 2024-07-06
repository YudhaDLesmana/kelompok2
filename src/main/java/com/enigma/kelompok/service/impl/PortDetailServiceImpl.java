package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.PortDetail;
import com.enigma.kelompok.model.Portofolio;
import com.enigma.kelompok.model.Stock;
import com.enigma.kelompok.model.User;
import com.enigma.kelompok.repository.PortDetailRepository;
import com.enigma.kelompok.service.PortDetailService;
import com.enigma.kelompok.service.PortofolioService;
import com.enigma.kelompok.service.StockService;
import com.enigma.kelompok.service.UserService;
import com.enigma.kelompok.utils.DTO.PortDetailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortDetailServiceImpl implements PortDetailService {
    private final PortDetailRepository portDetailRepository;
    private final PortofolioService portofolioService;
    private final StockService stockService;
    private final UserService userService;

    @Override
    public Page<PortDetail> getAll(Pageable pageable) {
        return portDetailRepository.findAll(pageable);
    }

    @Override
    public PortDetail getOne(Integer id) {
        return portDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public PortDetail create(PortDetailDTO request) {
        Portofolio port = portofolioService.getOne(request.getPortofolio_id());
        Stock stock = stockService.getOne(request.getStock_id());
        User user = userService.getOne(request.getUser_id());

        PortDetail portDetails = new PortDetail();
        portDetails.setPortofolio(port);
        portDetails.setStock(stock);
        portDetails.setQuantity_lot(portDetails.getQuantity_lot());
        portDetails.setPrice(stock.getPrice());
        user.setBalance(user.getBalance()- stock.getPrice());
        port.setTotal_amount(user.getBalance() - stock.getPrice());
        if (user.getBalance() < 0) {
            throw new RuntimeException("Oops balance tidak cukup");
        }
        portofolioService.update(port.getId(),port);

        return portDetailRepository.save(portDetails);


    }

    @Override
    public void delete(Integer id) {
        portDetailRepository.deleteById(id);
    }
}
