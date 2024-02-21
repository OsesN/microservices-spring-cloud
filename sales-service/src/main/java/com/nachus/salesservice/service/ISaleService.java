package com.nachus.salesservice.service;

import com.nachus.salesservice.model.Sale;

import java.util.List;

public interface ISaleService {
    Sale getSaleById(Long id_sale);
    void createSale(Sale sale);

    List<Sale> getAllSales();
}
