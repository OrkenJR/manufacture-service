package kz.iitu.manufactureservice.service;


import kz.iitu.cfaslib.dto.manufacture.MatWarAggregationDto;
import kz.iitu.cfaslib.dto.manufacture.request.MatWarRequestDto;

import java.util.List;

public interface WarehouseService {

    List<MatWarAggregationDto> itemsByFilter(MatWarRequestDto requestDto);

}
