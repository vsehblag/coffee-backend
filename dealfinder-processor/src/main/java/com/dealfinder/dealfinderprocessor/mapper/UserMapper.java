package com.dealfinder.dealfinderprocessor.mapper;

import com.dealfinder.dealfindercommon.dto.DealFinderUserDto;
import com.dealfinder.dealfindercommon.model.DealFinderUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    DealFinderUserDto dealFinderUserToDealFinderUserDto(DealFinderUser dealFinderUser);
}
