package com.nachus.salesservice.controller;

import com.nachus.salesservice.DTO.SaleDTO;
import com.nachus.salesservice.model.Sale;
import com.nachus.salesservice.repository.ICartAPI;
import com.nachus.salesservice.service.ISaleService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @Autowired
    private ICartAPI cartAPI;

    @GetMapping
    @CircuitBreaker(name = "carts-service",fallbackMethod = "cartDtoFallbackMethod")
    @Retry(name = "carts-service")
    public ResponseEntity<?> getAllSales(){

         List<SaleDTO> saleDTOList=saleService.getAllSales().stream()
                 .map(sale -> SaleDTO.builder()
                         .id_sale(sale.getId_sale())
                         .cartDTO(cartAPI.getCartDTOById(sale.getCart_id()))
                         .date(sale.getDate())
                         .build()).toList();

         return ResponseEntity.ok(saleDTOList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getSaleById(@PathVariable Long id){
        Sale sale=saleService.getSaleById(id);
        SaleDTO saleDTO= SaleDTO.builder()
                .id_sale(sale.getId_sale())
                .cartDTO(cartAPI.getCartDTOById(sale.getCart_id()))
                .date(sale.getDate())
                .build();

        return ResponseEntity.ok(saleDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSale(@RequestBody Sale sale){
        saleService.createSale(sale);
        return ResponseEntity.ok("Sale created");
    }

    private List<SaleDTO> cartDtoFallbackMethod(Throwable throwable){
        List<SaleDTO> saleDTOList=new ArrayList<>();
        saleDTOList.add(SaleDTO.builder()
                .cartDTO(null)
                .date(null)
                .id_sale(99999L)
                .build());
        return saleDTOList;
    }
    private void createException(){
        throw new IllegalArgumentException();
    }
}
