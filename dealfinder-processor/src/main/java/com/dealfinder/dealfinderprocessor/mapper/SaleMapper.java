package com.dealfinder.dealfinderprocessor.mapper;

import com.dealfinder.dealfindercommon.dto.page.SalePageDto;
import com.dealfinder.dealfindercommon.dto.page.SaleInfoDto;
import com.dealfinder.dealfindercommon.dto.sale.*;
import com.dealfinder.dealfindercommon.dto.sale.conditions.ManualConditionStatusDto;
import com.dealfinder.dealfindercommon.dto.sale.conditions.ProgressConditionStatusDto;
import com.dealfinder.dealfindercommon.dto.sale.rewards.RewardDto;
import com.dealfinder.dealfindercommon.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class SaleMapper {

    public abstract Sale toCreationEntity(SaleCreationDto saleDto);

    @Mapping(source = "sale.id", target = "saleId")
    @Mapping(source = "sale.name", target = "saleName")
    @Mapping(source = "sale.shortDescription", target = "shortDescription")
    @Mapping(source = "establishment.latitude", target = "latitude")
    @Mapping(source = "establishment.longitude", target = "longitude")
    @Mapping(source = "establishment.address", target = "address")
    @Mapping(source = "establishment.name", target = "establishmentName")
    @Mapping(source = "sale.poster", target = "salePoster")
    @Mapping(target = "distanceInMeters", expression = "java(com.dealfinder.dealfinderprocessor.utils.CoordinatesUtils.calculateDistance(userLatitude, userLongitude, sale.getEstablishment().getLatitude(), sale.getEstablishment().getLongitude()))")
    public abstract SaleStoryDto toSaleStoryDto(Sale sale, Establishment establishment, Double userLongitude, Double userLatitude);

    public List<SaleShortDto> fromListEntityToListSaleShortDto(List<Sale> sales, Double userLongitude, Double userLatitude){
        return sales
                .stream()
                .map(sale -> fromSaleToSaleShortDto(sale, userLongitude, userLatitude))
                .toList();
    }

    @Mapping(target = "distanceInMeters", expression = "java(com.dealfinder.dealfinderprocessor.utils.CoordinatesUtils.calculateDistance(userLatitude, userLongitude, sale.getEstablishment().getLatitude(), sale.getEstablishment().getLongitude()))")
    @Mapping(target = "establishmentName", expression = "java(sale.getEstablishment().getName())")
    @Mapping(target = "address", expression = "java(sale.getEstablishment().getAddress())")
    public abstract SaleShortDto fromSaleToSaleShortDto(Sale sale, Double userLongitude, Double userLatitude);


    @Mapping(target = "establishmentId", source = "sale.establishment.id")
    @Mapping(target = "establishmentAddress", source = "sale.establishment.address")
    @Mapping(target = "establishmentName", source = "sale.establishment.name")
    @Mapping(target = "QR", expression = "java(com.dealfinder.dealfinderprocessor.utils.FileUtils.readPictureFromPath(sale.getQr()))")
    public abstract SaleInfoDto fromSaleToSaleInfoDto(Sale sale);

    @Mapping(target = "establishmentId", source = "sale.establishment.id")
    @Mapping(target = "establishmentAddress", source = "sale.establishment.address")
    @Mapping(target = "establishmentName", source = "sale.establishment.name")
    @Mapping(target = "QR", expression = "java(com.dealfinder.dealfinderprocessor.utils.FileUtils.readPictureFromPath(sale.getQr()))")
    @Mapping(target = "distanceInMeters", expression = "java(com.dealfinder.dealfinderprocessor.utils.CoordinatesUtils.calculateDistance(userLatitude, userLongitude, sale.getEstablishment().getLatitude(), sale.getEstablishment().getLongitude()))")
    @Mapping(target = "progressConditions", source = "progressConditions")
    @Mapping(target = "manualConditions", source = "manualConditions")
    @Mapping(target = "rewards", source = "rewards")
    public abstract SalePageDto fromSaleToSaleDto(Sale sale, Double userLongitude, Double userLatitude, Set<ManualConditionStatusDto> manualConditions, Set<ProgressConditionStatusDto> progressConditions, Set<RewardDto> rewards);

    public void updateSaleByNotNullFieldsOfSaleDto(Sale saleToChange, SalePageDto saleDto) {
        if (saleDto.getName() != null) {
            saleToChange.setName(saleDto.getName());
        }
        if (saleDto.getDescription() != null) {
            saleToChange.setDescription(saleDto.getDescription());
        }
        if (saleDto.getShortDescription() != null) {
            saleToChange.setShortDescription(saleDto.getShortDescription());
        }
    }

    @Mapping(target = "distanceInMeters", expression = "java(com.dealfinder.dealfinderprocessor.utils.CoordinatesUtils.calculateDistance(userLatitude, userLongitude, sale.getEstablishment().getLatitude(), sale.getEstablishment().getLongitude()))")
    @Mapping(target = "latitude", source = "sale.establishment.latitude")
    @Mapping(target = "longitude", source = "sale.establishment.longitude")
    @Mapping(target = "address", source = "sale.establishment.address")
    @Mapping(target = "establishmentName", source = "sale.establishment.name")
    public abstract SaleProgressDto fromSaleToSaleProgressDto(Sale sale, long numCompleteConditions, long numTotalConditions, Double userLatitude, Double userLongitude);
}
