package kz.iitu.manufactureservice.controller;

import kz.iitu.cfaslib.dto.MatWarAggregationDto;
import kz.iitu.cfaslib.dto.request.MatWarRequestDto;
import kz.iitu.manufactureservice.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Orken
 **/
@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @PostMapping("/filtered")
    public List<MatWarAggregationDto> filtered(@RequestBody MatWarRequestDto requestDto) {
        return warehouseService.itemsByFilter(requestDto);
    }
}
