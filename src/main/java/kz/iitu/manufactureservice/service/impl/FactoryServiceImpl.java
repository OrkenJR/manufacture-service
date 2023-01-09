package kz.iitu.manufactureservice.service.impl;

import kz.iitu.cfaslib.dto.manufacture.FactoryInfoDto;
import kz.iitu.cfaslib.dto.manufacture.LightFactoryInfoDto;
import kz.iitu.manufactureservice.model.FactoryInfo;
import kz.iitu.manufactureservice.repository.FactoryRepository;
import kz.iitu.manufactureservice.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FactoryServiceImpl implements FactoryService {

    private final FactoryRepository factoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<FactoryInfo> factoriesByDirector(String director) {
        return factoryRepository.findFactoryInfosByDirector(director);
    }

    @Override
    public List<FactoryInfo> findAll() {
        return factoryRepository.findAll();
    }

    @Override
    public FactoryInfo save(FactoryInfoDto dto) {
        return factoryRepository.save(modelMapper.map(dto, FactoryInfo.class));
    }

    @Override
    public FactoryInfo update(String id, FactoryInfoDto dto) {
        Optional<FactoryInfo> optionalFactoryInfo = factoryRepository.findFactoryInfoById(id);
        if (optionalFactoryInfo.isPresent()) {
            FactoryInfo factoryInfo = optionalFactoryInfo.get();
            if (StringUtils.isNotBlank(dto.getName())) {
                factoryInfo.setName(dto.getName());
            }
            if (StringUtils.isNotBlank(dto.getDirector())) {
                factoryInfo.setDirector(dto.getDirector());
            }
            if (dto.getNumber() != null) {
                factoryInfo.setNumber(dto.getNumber());
            }
        }
        throw new NoSuchElementException(String.format("FactoryInfo with %s id does not exist", id));
    }

    @Override
    public void deleteById(String id) {
        factoryRepository.deleteById(id);
    }

    @Override
    public LightFactoryInfoDto map(FactoryInfoDto dto) {
        return modelMapper.map(dto, LightFactoryInfoDto.class);
    }

    @Override
    public List<LightFactoryInfoDto> map(List<FactoryInfo> list) {
        return list.stream()
                .map(element -> modelMapper.map(element, LightFactoryInfoDto.class))
                .toList();
    }
}
