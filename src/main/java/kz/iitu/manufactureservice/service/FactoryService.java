package kz.iitu.manufactureservice.service;

import kz.iitu.cfaslib.dto.manufacture.FactoryInfoDto;
import kz.iitu.cfaslib.dto.manufacture.LightFactoryInfoDto;
import kz.iitu.manufactureservice.model.FactoryInfo;

import java.util.List;

public interface FactoryService {
    List<FactoryInfo> factoriesByDirector(String director);

    List<FactoryInfo> findAll();

    FactoryInfo save(FactoryInfoDto dto);

    FactoryInfo update(String id, FactoryInfoDto dto);

    void deleteById(String id);

    LightFactoryInfoDto map(FactoryInfoDto dto);

    List<LightFactoryInfoDto> map(List<FactoryInfo> list);
}
