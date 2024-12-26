package com.dealfinder.dealfinderprocessor.mapper;

import com.dealfinder.dealfindercommon.dto.establishment.EstablishmentDto;
import com.dealfinder.dealfindercommon.model.Establishment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class EstablishmentMapper {

    public abstract Establishment toCreationEntity(EstablishmentDto establishmentDto);

    public abstract EstablishmentDto fromEntityToEstablishmentDto(Establishment establishment);

    public void updateEstablishmentByNotNullFieldsOfEstablishmentDto(Establishment establishmentToChange, EstablishmentDto establishmentDto) {
        if (establishmentDto.name != null) {
            establishmentToChange.setName(establishmentDto.name);
        }
        if (establishmentDto.email != null) {
            establishmentToChange.setEmail(establishmentDto.email);
        }
        if (establishmentDto.address != null) {
            establishmentToChange.setAddress(establishmentDto.address);
        }
        if (establishmentDto.phoneNumber != null) {
            establishmentToChange.setPhoneNumber(establishmentDto.phoneNumber);
        }
        if (establishmentDto.additionalInfo != null) {
            establishmentToChange.setAdditionalInfo(establishmentDto.additionalInfo);
        }
        if (establishmentDto.latitude != null) {
            establishmentToChange.setLatitude(establishmentDto.latitude);
        }
        if (establishmentDto.longitude != null) {
            establishmentToChange.setLongitude(establishmentDto.longitude);
        }
        if (establishmentDto.socialNetwork != null) {
            establishmentToChange.setSocialNetwork(establishmentDto.socialNetwork);
        }
        if (establishmentDto.description != null) {
            establishmentToChange.setDescription(establishmentDto.description);
        }
    }
}
