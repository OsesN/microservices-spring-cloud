package com.nachus.salesservice.service;

import com.nachus.salesservice.model.Sale;
import com.nachus.salesservice.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService implements ISaleService {

    @Autowired
    private ISaleRepository saleRepository;

    @Override
    public Sale getSaleById(Long id_sale) {
        return saleRepository.findById(id_sale).orElse(null);
    }

    @Override
    public void createSale(Sale sale) {
        sale.setDate(LocalDateTime.now());
        saleRepository.save(sale);
    }

    @Override
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

}
