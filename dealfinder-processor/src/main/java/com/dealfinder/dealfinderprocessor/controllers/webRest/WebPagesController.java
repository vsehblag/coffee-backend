package com.dealfinder.dealfinderprocessor.controllers.webRest;

import com.dealfinder.dealfindercommon.dto.page.EstablishmentPageDto;
import com.dealfinder.dealfinderprocessor.service.EstablishmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class WebPagesController {

    private final EstablishmentService establishmentService;

    public WebPagesController(EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    //общая информация
    @GetMapping("/{id}")
    public EstablishmentPageDto getMainPage(@PathVariable(name = "id") Long establishmentId){
        return establishmentService.getEstablishmentPageById(establishmentId);
    }

}
