package com.dealfinder.dealfinderprocessor.service;

import com.dealfinder.dealfindercommon.dto.DealFinderUserDto;
import com.dealfinder.dealfindercommon.dto.page.SaleInfoDto;
import com.dealfinder.dealfindercommon.dto.page.SalePageDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleCreationDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleProgressDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleShortDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleStoryDto;
import com.dealfinder.dealfindercommon.dto.sale.conditions.ManualConditionStatusDto;
import com.dealfinder.dealfindercommon.dto.sale.conditions.ProgressConditionStatusDto;
import com.dealfinder.dealfindercommon.dto.sale.rewards.RewardDto;
import com.dealfinder.dealfindercommon.model.*;
import com.dealfinder.dealfindercommon.repository.*;
import com.dealfinder.dealfinderprocessor.mapper.SaleMapper;
import com.dealfinder.dealfinderprocessor.platform.exception.SaleNotFoundException;
import com.dealfinder.dealfinderprocessor.utils.FileUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class SaleService {

    private final EstablishmentService establishmentService;

    private final SaleRepository saleRepository;
    private final RewardRepository rewardRepository;
    private final ManualConditionRepository manualConditionRepository;
    private final ProgressConditionRepository progressConditionRepository;
    private final UserSaleRepository userSaleRepository;
    private final UserRepository userRepository;
    private final UserProgressConditionRepository userProgressConditionRepository;
    private final UserManualConditionRepository userManualConditionRepository;
    private final SaleMapper saleMapper;

    @Value("${directory.qr}")
    private String qrDirectory;

//    @Value("${posters.sale}")
//    private String salePosterDirectory;

    public SaleService(EstablishmentService establishmentService,
                       SaleRepository saleRepository,
                       RewardRepository rewardRepository,
                       ManualConditionRepository manualConditionRepository,
                       TimeRestrictionRepository timeRestrictionRepository,
                       UserRepository userRepository,
                       SaleMapper saleMapper,
                       ProgressConditionRepository progressConditionRepository, UserSaleRepository userSaleRepository, UserProgressConditionRepository userProgressConditionRepository, UserManualConditionRepository userManualConditionRepository) {
        this.establishmentService = establishmentService;
        this.saleRepository = saleRepository;
        this.rewardRepository = rewardRepository;
        this.manualConditionRepository = manualConditionRepository;
        this.userRepository = userRepository;
        this.saleMapper = saleMapper;
        this.progressConditionRepository = progressConditionRepository;
        this.userSaleRepository = userSaleRepository;
        this.userProgressConditionRepository = userProgressConditionRepository;
        this.userManualConditionRepository = userManualConditionRepository;
    }

    @Transactional
    public void addNewSale(SaleCreationDto saleDto, Establishment establishment) {

        Sale newSale = saleMapper.toCreationEntity(saleDto);
        newSale.setEstablishment(establishment);
        String url = "http://localhost:8080/api/android/pages/sale/progress/" + newSale.getId();
        String filePath = qrDirectory + "/qr_code_" + UUID.randomUUID() + ".png";
        FileUtils.saveQrWithEncodedUrl(url, filePath);
        newSale.setQr(filePath);

        saleRepository.save(newSale);
        saveSaleRewards(saleDto.getRewards(), newSale);
        saveSaleManualConditions(saleDto.getManualConditions(), newSale);
        saveSaleProgressCondition(saleDto.getProgressConditions(), newSale);
    }

    private void saveSaleProgressCondition(Set<ProgressConditionStatusDto> progressConditionDtos, Sale newSale) {
        for (ProgressConditionStatusDto progressCondition : progressConditionDtos) {
            progressConditionRepository.save(ProgressCondition.builder()
                    .neededAmount(progressCondition.getNeededAmount())
                    .productName(progressCondition.getProductName())
                    .sale(newSale)
                    .build()
            );
        }
    }

    private void saveSaleManualConditions(Set<ManualConditionStatusDto> manualConditionDtos, Sale newSale) {
        for (ManualConditionStatusDto manualConditionDto : manualConditionDtos) {
            manualConditionRepository.save(ManualCondition.builder()
                    .conditionText(manualConditionDto.getConditionText())
                    .sale(newSale)
                    .build());
        }
    }

    private void saveSaleRewards(Set<RewardDto> rewards, Sale newSale) {
        for (RewardDto rewardDto : rewards) {
            rewardRepository.save(Reward.builder()
                    .rewardText(rewardDto.getRewardText())
                    .sale(newSale)
                    .build());
        }
    }

    @Transactional
    public void updateSaleInfo(SalePageDto saleDto, Long id) {
        Sale saleToChange = saleRepository.findById(id).get();
        saleMapper.updateSaleByNotNullFieldsOfSaleDto(saleToChange, saleDto);
        saleRepository.save(saleToChange);
    }

    public List<SaleShortDto> getSalesByEstablishmentId(Long establishmentId) {
        Establishment establishment = establishmentService.getById(establishmentId);
        return saleMapper.fromListEntityToListSaleShortDto(establishment.getSales().stream().toList(), null, null);
    }

    public List<SaleShortDto> getSalesWithDistanceToUser(Double userLongitude, Double userLatitude) {
        List<Sale> sales = saleRepository.findAll();
        List<SaleShortDto> saleShortDtos = saleMapper.fromListEntityToListSaleShortDto(sales, userLongitude, userLatitude);
        return saleShortDtos.stream().sorted(Comparator.comparingDouble(SaleShortDto::getDistanceInMeters)).toList();
    }

    @Transactional
    public SaleInfoDto getSaleInfo(Long saleId) {
        Sale sale = saleRepository.findById(saleId).orElseThrow();
        return saleMapper.fromSaleToSaleInfoDto(sale);
    }


    @Transactional
    public SalePageDto getSalePageBySaleId(DealFinderUserDto user, Long saleId, Double userLongitude, Double userLatitude) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException("акция с id " + saleId + " не существует"));

        Set<ManualConditionStatusDto> manualConditions = manualConditionRepository.getManualConditionDtosBySaleIdAndUserId(sale.getId(), user.getId());
        Set<ProgressConditionStatusDto> progressConditions = progressConditionRepository.getProgressConditionsBySaleIdAndUserId(sale.getId(), user.getId());
        Set<RewardDto> rewards = rewardRepository.findAllBySaleId(saleId);

        return saleMapper.fromSaleToSaleDto(sale, userLongitude, userLatitude, manualConditions, progressConditions, rewards);

    }

    @Transactional
    public List<SaleProgressDto> getSalesProgress(Double userLongitude, Double userLatitude, DealFinderUserDto user) {
        List<UserSale> usersSales = userSaleRepository.findByUser_Id(user.getId());

        return usersSales
                .stream()
                .map(userSale -> addSaleProgress(userLongitude, userLatitude, userSale))
                .collect(Collectors.toList());
    }

    private SaleProgressDto addSaleProgress(Double userLongitude, Double userLatitude, UserSale userSale) {
        Sale sale = userSale.getSale();
        long numCompletedManualConditions = getNumCompletedManualConditions(userSale);
        long numCompletedProgressConditions = getNumCompletedProgressConditions(userSale);
        long totalConditions = (userSale.getProgressConditions().size() + userSale.getManualConditions().size());
        return saleMapper.fromSaleToSaleProgressDto(sale, numCompletedManualConditions + numCompletedProgressConditions, totalConditions, userLatitude, userLongitude);
    }

    private long getNumCompletedProgressConditions(UserSale userSale) {
        return userSale.getProgressConditions().stream().filter(UserProgressCondition::getCompleted).count();
    }

    private long getNumCompletedManualConditions(UserSale userSale) {
        return userSale.getManualConditions().stream().filter(UserManualCondition::getCompleted).count();
    }

    @Transactional
    public List<SaleStoryDto> getSalesStory(Double userLongitude, Double userLatitude, DealFinderUserDto user) {

        List<UserSale> usersSales = userSaleRepository.findUserSaleByCompletedIsTrueAndUser_Id(user.getId());

        return usersSales.stream()
                .map(userSale -> addSaleStory(userLongitude, userLatitude, userSale))
                .collect(Collectors.toList());
    }

    private SaleStoryDto addSaleStory(Double userLongitude, Double userLatitude, UserSale userSale) {
        Sale sale = userSale.getSale();
        Establishment establishment = sale.getEstablishment();
        return saleMapper.toSaleStoryDto(sale, establishment, userLongitude, userLatitude);
    }


    //TODO переделать для взаимодействия со страницей кассира
    @Transactional
    public void updateSaleConditions(Long saleId, DealFinderUserDto user) {
        if (saleRepository.findById(saleId).isEmpty()){
            throw new SaleNotFoundException("не получилось поучаствовать в акции с id " + saleId + ". Такой акции не существует");
        }
        if (userSaleRepository.findByUser_IdAndSale_IdAndCompletedIsFalse(user.getId(), saleId).isEmpty()) {
            setNewSaleProgressToUser(saleId, user);
        }

        List<UserProgressCondition> userProgressConditions = userProgressConditionRepository
                .findAllByUserSale_Sale_IdAndUserSale_User_IdAndCompletedIsFalse(saleId, user.getId());

        for (UserProgressCondition userProgressCondition : userProgressConditions) {
            userProgressCondition.setCurrentAmount(userProgressCondition.getCurrentAmount() + 1);
            ProgressCondition progressCondition = userProgressCondition.getProgressCondition();
            if (userProgressCondition.getCurrentAmount() >= progressCondition.getNeededAmount()) {
                userProgressCondition.setCompleted(true);
            }
        }
        userProgressConditionRepository.saveAll(userProgressConditions);
    }

    private void setNewSaleProgressToUser(Long saleId, DealFinderUserDto user) {
        UserSale userSale = new UserSale();
        Sale sale = saleRepository.getReferenceById(saleId);
        userSale.setSale(sale);
        userSale.setUser(userRepository.getReferenceById(user.getId()));
        userSale.setCompleted(false);
        userSale = userSaleRepository.save(userSale);
        for (ManualCondition manualCondition : sale.getManualConditions()) {
            UserManualCondition userManualCondition = new UserManualCondition();
            userManualCondition.setUserSale(userSale);
            userManualCondition.setCompleted(false);
            userManualCondition.setManualCondition(manualCondition);
            userManualConditionRepository.save(userManualCondition);
        }
        for (ProgressCondition progressCondition : sale.getProgressConditions()) {
            UserProgressCondition userProgressCondition = new UserProgressCondition();
            userProgressCondition.setUserSale(userSale);
            userProgressCondition.setProgressCondition(progressCondition);
            userProgressCondition.setCompleted(false);
            userProgressCondition.setCurrentAmount(0);
            userProgressConditionRepository.save(userProgressCondition);
        }
    }
}
