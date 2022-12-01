package kz.iitu.manufactureservice.controller;

import kz.iitu.cfaslib.dto.MarketMaterialDto;
import kz.iitu.cfaslib.dto.request.MarketFilterRequestDto;
import kz.iitu.manufactureservice.model.MarketMaterial;
import kz.iitu.manufactureservice.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Orken
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {

    private final MarketService marketService;

    @PostMapping("/filtered")
    public List<MarketMaterial> filtered(@RequestBody MarketFilterRequestDto requestDto) {
        return marketService.itemsByFilter(requestDto);
    }

    @PostMapping("/save")
    public MarketMaterial save(MarketMaterialDto dto){
        return marketService.save(dto);
    }

    @PutMapping("/{id}")
    public MarketMaterial update(@PathVariable String id, MarketMaterialDto dto){
        return marketService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id){
        marketService.deleteById(id);
        return "ok";
    }

    @DeleteMapping
    public String deleteByIds(@RequestBody List<String> ids){
        marketService.delete(ids);
        return "ok";
    }
}
