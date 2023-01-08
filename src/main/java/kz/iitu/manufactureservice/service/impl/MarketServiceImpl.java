package kz.iitu.manufactureservice.service.impl;


import kz.iitu.cfaslib.dto.manufacture.MarketMaterialDto;
import kz.iitu.cfaslib.dto.manufacture.request.MarketFilterRequestDto;
import kz.iitu.manufactureservice.model.MarketMaterial;
import kz.iitu.manufactureservice.model.enums.Country;
import kz.iitu.manufactureservice.model.enums.Icon;
import kz.iitu.manufactureservice.repository.MarketMaterialRepository;
import kz.iitu.manufactureservice.repository.specification.MarMatSpecification;
import kz.iitu.manufactureservice.repository.specification.SearchCriteria;
import kz.iitu.manufactureservice.repository.specification.SearchOperation;
import kz.iitu.manufactureservice.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    private final MarketMaterialRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<MarketMaterial> itemsByFilter(MarketFilterRequestDto requestDto) {
        return repository.findAll(buildSpecification(requestDto));
    }

    @Override
    public MarketMaterial save(MarketMaterialDto requestDto) {
        return repository.save(modelMapper.map(requestDto, MarketMaterial.class));
    }

    @Override
    public MarketMaterial update(String id, MarketMaterialDto requestDto) {
        Optional<MarketMaterial> modelOpt = repository.findMarketMaterialById(id);
        if (modelOpt.isPresent()) {
            MarketMaterial model = modelOpt.get();
            if (StringUtils.isNotBlank(requestDto.getCountry()) && Country.isCountry(requestDto.getCountry())) {
                model.setCountry(Country.valueOf(requestDto.getCountry().toUpperCase()));
            }
            if (StringUtils.isNotBlank(requestDto.getIcon()) && Icon.isIcon(requestDto.getIcon())) {
                model.setIcon(Icon.valueOf(requestDto.getIcon().toUpperCase()));
            }
            if (StringUtils.isNotBlank(requestDto.getName())) {
                model.setName(requestDto.getName());
            }
            return model;
        }
        throw new NoSuchElementException(String.format("Material with %s id does not exist", id));
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(List<String> ids) {
        repository.deleteAllById(ids);
    }

    private MarMatSpecification buildSpecification(MarketFilterRequestDto requestDto) {
        MarMatSpecification specification = new MarMatSpecification();
        if (StringUtils.isNotBlank(requestDto.getCountry())) {
            specification.add(buildCriteria("country", requestDto.getCountry().toUpperCase(), SearchOperation.EQUAL));
        }
        if (StringUtils.isNotBlank(requestDto.getIcon())) {
            specification.add(buildCriteria("icon", requestDto.getIcon().toUpperCase(), SearchOperation.EQUAL));
        }
        if (StringUtils.isNotBlank(requestDto.getName())) {
            specification.add(buildCriteria("name", requestDto.getName(), SearchOperation.EQUAL));
        }
        specification.add(buildCriteria("price", String.valueOf(Optional.of(requestDto).map(MarketFilterRequestDto::getMin).orElse(0.0)), SearchOperation.GREATER_THAN_EQUAL));
        specification.add(buildCriteria("price", String.valueOf(Optional.of(requestDto).map(MarketFilterRequestDto::getMax).orElse(Double.MAX_VALUE)), SearchOperation.LESS_THAN));
        return specification;
    }

    private SearchCriteria buildCriteria(String key, Object value, SearchOperation operation) {
        return SearchCriteria.builder()
                .key(key)
                .value(value)
                .operation(operation)
                .build();
    }
}
