package kz.iitu.manufactureservice.service;

import kz.iitu.cfaslib.dto.MatWarAggregationDto;
import kz.iitu.cfaslib.dto.request.MatWarRequestDto;

import java.util.List;

public interface WarehouseService {

    List<MatWarAggregationDto> itemsByFilter(MatWarRequestDto requestDto);

}
