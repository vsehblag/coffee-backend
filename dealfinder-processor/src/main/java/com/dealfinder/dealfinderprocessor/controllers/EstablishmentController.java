package com.dealfinder.dealfinderprocessor.controllers;

import com.dealfinder.dealfindercommon.dto.establishment.EstablishmentDto;
import com.dealfinder.dealfindercommon.dto.page.EstablishmentPageDto;
import com.dealfinder.dealfindercommon.dto.page.EstablishmentSearchDto;
import com.dealfinder.dealfindercommon.model.Establishment;
import com.dealfinder.dealfindercommon.repository.EstablishmentRepository;
import com.dealfinder.dealfinderprocessor.service.EstablishmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/establishments")
public class EstablishmentController {

    private final SaleController saleController;

    private final EstablishmentRepository establishmentRepository;
    private final EstablishmentService service;

    private Establishment loginedEstablishment;

    public EstablishmentController(SaleController saleController, EstablishmentRepository establishmentRepository, EstablishmentService service) {
        this.establishmentRepository = establishmentRepository;
        this.service = service;
        this.saleController = saleController;

        loginedEstablishment = service.getEstablishmentByPasswordAndLogin("establishment1", "1111");
        saleController.setLoginedEstablishment(loginedEstablishment);
    }


    @GetMapping("/login")
    public EstablishmentPageDto login(@RequestParam String login, @RequestParam String password){
        loginedEstablishment = service.getEstablishmentByPasswordAndLogin(login, password);
        saleController.setLoginedEstablishment(loginedEstablishment);
        return getEstablishmentById(loginedEstablishment.getId());
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewEstablishment(@RequestBody EstablishmentDto establishmentDto){
        service.addNewEstablishment(establishmentDto);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEstablishment(@RequestBody EstablishmentDto establishmentDto, @PathVariable Long id){
        service.updateEstablishmentInfo(establishmentDto, id);
    }

    @GetMapping("/posters/{id}")
    public byte[] getPosterEstablishment(@PathVariable Long id){
        Optional<Establishment> establishment = establishmentRepository.findById(id);
        return establishment.get().getPoster();
    }

    @GetMapping("/{id}")
    public EstablishmentPageDto getEstablishmentById(@PathVariable Long id) {
        return service.getEstablishmentPageById(id);
    }

    @GetMapping("/search")
    public List<EstablishmentSearchDto> establishmentsSearch(@RequestParam Double userLongitude, @RequestParam Double userLatitude){
        return service.getEstablishmentsWithDistanceToUser(userLongitude, userLatitude);
    }

    @PutMapping("/update/poster/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateEstablishmentPost(@RequestBody byte[] poster, @PathVariable Long id){
        service.updateEstablishmentPoster(poster, id);
    }

}
