package com.dealfinder.dealfinderprocessor.controllers;


import com.dealfinder.dealfindercommon.dto.page.SalePageDto;
import com.dealfinder.dealfindercommon.dto.page.SaleInfoDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleCreationDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleShortDto;
import com.dealfinder.dealfindercommon.model.Establishment;
import com.dealfinder.dealfindercommon.model.Sale;
import com.dealfinder.dealfindercommon.repository.SaleRepository;
import com.dealfinder.dealfinderprocessor.service.SaleService;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleRepository saleRepository;
    private final SaleService service;

    @Setter
    private Establishment loginedEstablishment;

    public SaleController(SaleRepository saleRepository, SaleService service) {
        this.saleRepository = saleRepository;
        this.service = service;
    }

    @GetMapping("/all")
    public List<Sale> getAllSale() {
        return saleRepository.findAll();
    }

    @GetMapping("/{id}")
    public SaleInfoDto getSaleById(@PathVariable Long id) {
        return service.getSaleInfo(id);
    }

    @GetMapping("/establishment")
    public List<SaleShortDto> getSalesByEstablishmentId(){
        return service.getSalesByEstablishmentId(loginedEstablishment.getId());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewSale(@RequestBody SaleCreationDto saleDto){
        service.addNewSale(saleDto, loginedEstablishment);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSale(@RequestBody SalePageDto saleDto, @PathVariable Long id){
        service.updateSaleInfo(saleDto, id);
    }
}
