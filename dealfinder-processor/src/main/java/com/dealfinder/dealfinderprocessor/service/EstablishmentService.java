package com.dealfinder.dealfinderprocessor.service;

import com.dealfinder.dealfindercommon.dto.establishment.EstablishmentDto;
import com.dealfinder.dealfindercommon.dto.page.EstablishmentPageDto;
import com.dealfinder.dealfindercommon.dto.page.EstablishmentSearchDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleShortDto;
import com.dealfinder.dealfindercommon.model.Establishment;
import com.dealfinder.dealfindercommon.repository.EstablishmentRepository;
import com.dealfinder.dealfinderprocessor.mapper.EstablishmentMapper;
import com.dealfinder.dealfinderprocessor.mapper.SaleMapper;
import com.dealfinder.dealfinderprocessor.platform.exception.EstablishmentNotFoundException;
import com.dealfinder.dealfinderprocessor.utils.CoordinatesUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EstablishmentService {

    EstablishmentRepository repository;

    EstablishmentMapper establishmentMapper;
    SaleMapper saleMapper;

    public EstablishmentService(EstablishmentRepository repository, EstablishmentMapper establishmentMapper, SaleMapper saleMapper) {
        this.repository = repository;
        this.establishmentMapper = establishmentMapper;
        this.saleMapper = saleMapper;
    }

    public void addNewEstablishment(EstablishmentDto establishmentDto) {
        Establishment newEstablishment = establishmentMapper.toCreationEntity(establishmentDto);
        repository.save(newEstablishment);
    }

    public Establishment getById(Long id) {
        return repository.getReferenceById(id);
    }

    @Transactional
    public void updateEstablishmentInfo(EstablishmentDto establishmentDto, Long id) {
        Establishment establishmentToChange = repository.findById(id).get();
        establishmentMapper.updateEstablishmentByNotNullFieldsOfEstablishmentDto(establishmentToChange, establishmentDto);
        repository.save(establishmentToChange);
    }

    @Transactional
    public void updateEstablishmentPoster(byte[] poster, Long id) {
        Establishment establishmentToChange = repository.findById(id).get();
        establishmentToChange.setPoster(poster);
        repository.save(establishmentToChange);
    }

    public List<EstablishmentSearchDto> getEstablishmentsWithDistanceToUser(Double userLongitude, Double userLatitude) {
        List<Establishment> establishments = repository.findAll();
        return fillEstablishmentSearchDtoList(userLongitude, userLatitude, establishments);
    }


    private List<EstablishmentSearchDto> fillEstablishmentSearchDtoList(Double userLongitude, Double userLatitude, List<Establishment> establishments) {
        List<EstablishmentSearchDto> establishmentSearchDtos = new ArrayList<>();
        for (Establishment establishment : establishments) {
            establishmentSearchDtos.add(EstablishmentSearchDto.builder()
                    .id(establishment.getId())
                    .name(establishment.getName())
                    .latitude(establishment.getLatitude())
                    .longitude(establishment.getLongitude())
                    .address(establishment.getAddress())
                    .description(establishment.getDescription())
//                    .poster(establishment.getPoster())
                    .distanceToUser(CoordinatesUtils.calculateDistance(
                            userLatitude, userLongitude,
                            establishment.getLatitude(), establishment.getLongitude()))
                    .workingDays(establishment.getWorkingDays())
                    .build()
            );
        }
        return establishmentSearchDtos;
    }


    public EstablishmentPageDto getEstablishmentPageById(Long establishmentId) {
        Establishment establishment = repository.findById(establishmentId)
                .orElseThrow(() -> new EstablishmentNotFoundException("заведение с id " + establishmentId + " не зарегистрировано"));

        EstablishmentDto establishmentDto = establishmentMapper.fromEntityToEstablishmentDto(establishment);
        List<SaleShortDto> sales = saleMapper.fromListEntityToListSaleShortDto(establishment.getSales().stream().toList(),  null, null);
        return EstablishmentPageDto.builder()
                .establishment(establishmentDto)
                .sales(sales)
                .build();
    }

    public Establishment getEstablishmentByPasswordAndLogin(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }
}
