package com.dealfinder.dealfindercommon.dto.page;

import com.dealfinder.dealfindercommon.dto.establishment.EstablishmentDto;
import com.dealfinder.dealfindercommon.dto.sale.SaleShortDto;
import lombok.*;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EstablishmentPageDto {
    public EstablishmentDto establishment;
    public List<SaleShortDto> sales;
}
