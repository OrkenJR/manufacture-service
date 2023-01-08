package kz.iitu.manufactureservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.iitu.cfaslib.dto.manufacture.MarketMaterialDto;
import kz.iitu.cfaslib.dto.manufacture.request.MarketFilterRequestDto;
import kz.iitu.manufactureservice.model.MarketMaterial;
import kz.iitu.manufactureservice.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Orken
 * <p>
 * Don't forget to wrap with ResponseEntity
 **/
@Tag(name = "Market", description = "The Market API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;

    @Operation(summary = "Getting filtered list of materials available on the market")
    @PostMapping("/filtered")
    public List<MarketMaterial> filtered(@RequestBody MarketFilterRequestDto requestDto) {
        return marketService.itemsByFilter(requestDto);
    }

    @Operation(summary = "Creating new instance of MarketMaterial")
    @PostMapping("/save")
    public MarketMaterial save(MarketMaterialDto dto) {
        return marketService.save(dto);
    }

    @Operation(summary = "Updating material info by ID")
    @PutMapping("/{id}")
    public MarketMaterial update(@PathVariable String id, MarketMaterialDto dto) {
        return marketService.update(id, dto);
    }

    @Operation(summary = "Deleting material by ID")
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        marketService.deleteById(id);
        return "ok";
    }

    @Operation(summary = "Deleting materials by IDs")
    @DeleteMapping
    public String deleteByIds(@RequestBody List<String> ids) {
        marketService.delete(ids);
        return "ok";
    }
}
