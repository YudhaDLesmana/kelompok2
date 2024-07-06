package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.PortDetail;
import com.enigma.kelompok.model.Portfolio;
import com.enigma.kelompok.model.Stock;
import com.enigma.kelompok.model.User;
import com.enigma.kelompok.repository.PortfolioRepository;
import com.enigma.kelompok.service.PortDetailService;
import com.enigma.kelompok.service.PortfolioService;
import com.enigma.kelompok.service.StockService;
import com.enigma.kelompok.service.UserService;
import com.enigma.kelompok.utils.dto.PortfolioRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final UserService userService;
    private final StockService stockService;
    private final PortDetailService portDetailService;

    @Override
    public Portfolio create(PortfolioRequestDTO request) {
        User user = userService.getOne(request.getUser_id());
        Integer userBalance = user.getBalance();

        Stock stock = stockService.getOne(request.getStock_id());
        Integer totalAmount = stock.getPrice() * request.getQuantity_lot();

        if (userBalance >= totalAmount){
            Portfolio portfolio = new Portfolio();
            portfolio.setUser(user);
            portfolio.setTotal_amount(totalAmount);
            portfolioRepository.save(portfolio);

            PortDetail portDetail = new PortDetail();
            portDetail.setPortfolio(portfolio);
            portDetail.setStock(stock);
            portDetail.setPrice(stock.getPrice());
            portDetail.setQuantity_lot(request.getQuantity_lot());
            portDetailService.create(portDetail);

            user.setBalance(userBalance - totalAmount);
            userService.update(user.getId(), user);
            return portfolio;
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    @Override
    public Page<Portfolio> getAll(Pageable pageable) {
        return portfolioRepository.findAll(pageable);
    }

    @Override
    public Portfolio getOne(Integer id) {
        return portfolioRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Portofolio not found"));
    }

    @Override
    public Portfolio update(Integer id, PortfolioRequestDTO request) {
        Portfolio updatePort = this.getOne(id);

        return null;
    }
}
