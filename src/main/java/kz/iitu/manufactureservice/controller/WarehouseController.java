package kz.iitu.manufactureservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * <p>
 * Don't forget to wrap with ResponseEntity
 **/
@Tag(name = "Warehouse", description = "The Warehouse API")
@RestController
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService warehouseService;

    @Operation(summary = "Getting filtered list of materials available on the warehouse")
    @PostMapping("/filtered")
    public List<MatWarAggregationDto> filtered(@RequestBody MatWarRequestDto requestDto) {
        return warehouseService.itemsByFilter(requestDto);
    }
}
