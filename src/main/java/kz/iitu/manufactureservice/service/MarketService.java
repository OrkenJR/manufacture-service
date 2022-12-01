package kz.iitu.manufactureservice.service;

import kz.iitu.cfaslib.dto.MarketMaterialDto;
import kz.iitu.cfaslib.dto.request.MarketFilterRequestDto;
import kz.iitu.manufactureservice.model.MarketMaterial;

import java.util.List;

public interface MarketService {
    List<MarketMaterial> itemsByFilter(MarketFilterRequestDto requestDto);

    MarketMaterial save(MarketMaterialDto requestDto);

    MarketMaterial update(String id, MarketMaterialDto requestDto);

    void deleteById(String id);

    void delete(List<String> ids);

}
