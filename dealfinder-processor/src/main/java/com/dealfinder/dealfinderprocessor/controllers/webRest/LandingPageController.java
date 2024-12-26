package com.dealfinder.dealfinderprocessor.controllers.webRest;

import com.dealfinder.dealfindercommon.repository.EstablishmentRepository;
import com.dealfinder.dealfindercommon.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LandingPageController {

    @Autowired
    EstablishmentRepository establishmentRepository;

    @Autowired
    SaleRepository saleRepository;


    @GetMapping("/api/installCount")
    //TODO тут должна быть интеграция с плеймаркетом, чтобы получать количество загрузок
    public String getInstallCount() {
        return String.valueOf(0);
    }

    @GetMapping("/api/cities")
    public String getCitiesCount() {
        return String.valueOf(1);
    }

    @GetMapping("/api/partnersCount")
    public String getPartnersCount() {
        return String.valueOf(establishmentRepository.count());
    }

    @GetMapping("/api/salesCount")
    public String getSalesCount() {
        return String.valueOf(saleRepository.count());
    }
}

