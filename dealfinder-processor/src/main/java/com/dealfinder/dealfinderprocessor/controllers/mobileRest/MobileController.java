package com.dealfinder.dealfinderprocessor.controllers.mobileRest;

import com.dealfinder.dealfindercommon.dto.DealFinderUserDto;
import com.dealfinder.dealfindercommon.dto.page.EstablishmentPageDto;
import com.dealfinder.dealfindercommon.dto.page.EstablishmentSearchDto;
import com.dealfinder.dealfindercommon.dto.page.SalePageDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleProgressDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleShortDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleStoryDto;
import com.dealfinder.dealfinderprocessor.service.EstablishmentService;
import com.dealfinder.dealfinderprocessor.service.SaleService;
import com.dealfinder.dealfinderprocessor.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/android/pages")
public class MobileController {

    private final SaleService saleService;
    private final EstablishmentService establishmentService;
    private final UserService userService;

    private DealFinderUserDto user;

    public MobileController(SaleService saleService, EstablishmentService establishmentService, UserService userService) {
        this.saleService = saleService;
        this.establishmentService = establishmentService;
        this.userService = userService;
        user = userService.getOrRegisterUserByPasswordAndLogin("1", "rodionov");
    }

    @GetMapping("/login")
    public DealFinderUserDto login(@RequestParam String login, @RequestParam String password){
        user = userService.getOrRegisterUserByPasswordAndLogin(password, login);
        return user;
    }


    // страница поиска акций
    @GetMapping("/sales/search")
    public List<SaleShortDto> salesSearch(@RequestParam Double userLongitude, @RequestParam Double userLatitude){
        return saleService.getSalesWithDistanceToUser(userLongitude, userLatitude);
    }

    // страница поиска заведений
    @GetMapping("/establishments/search")
    public List<EstablishmentSearchDto> establishmentsSearch(@RequestParam Double userLongitude, @RequestParam Double userLatitude){
        return establishmentService.getEstablishmentsWithDistanceToUser(userLongitude, userLatitude);
    }

    // получить страницу заведения
    //TODO добавить расстояние
    @GetMapping("/establishment/{id}")
    public EstablishmentPageDto establishment(@PathVariable Long id, @RequestParam @Validated() Double userLongitude, @RequestParam Double userLatitude){
        return establishmentService.getEstablishmentPageById(id);
    }

    // страница акции
    @GetMapping("/sale/{id}")
    public SalePageDto sale(@PathVariable Long id, @RequestParam Double userLongitude, @RequestParam Double userLatitude){
        return saleService.getSalePageBySaleId(user, id, userLongitude, userLatitude);
    }

    // мои акции
    @GetMapping("/sales/progress")
    public List<SaleProgressDto> getSalesProgress(@RequestParam Double userLongitude, @RequestParam Double userLatitude){
        return saleService.getSalesProgress(userLongitude, userLatitude, user);
    }

    // история акций
    @GetMapping("/sales/history")
    public List<SaleStoryDto> salesHistory(@RequestParam Double userLongitude, @RequestParam Double userLatitude){
        return saleService.getSalesStory(userLongitude, userLatitude, user);
    }

    //не put потому что вызывается после сканирования QR
    @GetMapping("sale/progress/{saleId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateSaleConditions(@PathVariable Long saleId){
        saleService.updateSaleConditions(saleId, user);
    }
}
