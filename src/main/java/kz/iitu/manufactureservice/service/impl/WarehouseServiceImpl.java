package kz.iitu.manufactureservice.service.impl;

import kz.iitu.cfaslib.dto.MatWarAggregationDto;
import kz.iitu.cfaslib.dto.MaterialDto;
import kz.iitu.cfaslib.dto.request.MatWarRequestDto;
import kz.iitu.manufactureservice.model.Material;
import kz.iitu.manufactureservice.model.MaterialWarehouse;
import kz.iitu.manufactureservice.repository.MatWarRepository;
import kz.iitu.manufactureservice.repository.MaterialRepository;
import kz.iitu.manufactureservice.repository.specification.MatWarSpecification;
import kz.iitu.manufactureservice.repository.specification.SearchCriteria;
import kz.iitu.manufactureservice.repository.specification.SearchOperation;
import kz.iitu.manufactureservice.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {

    private final MatWarRepository matWarRepository;
    private final MaterialRepository materialRepository;

    @Override
    public List<MatWarAggregationDto> itemsByFilter(MatWarRequestDto requestDto) {
        List<MatWarAggregationDto> aggregatedList = new ArrayList<>();
        List<MaterialWarehouse> result = matWarRepository.findAll(buildMatWarSpecification(requestDto));
        Double all = result.stream()
                .mapToDouble(MaterialWarehouse::getAvailable)
                .sum();
        result.forEach(matWar -> {
            Map<String, Double> departmentShare = new HashMap<>();
            departmentShare.put(matWar.getWarehouse().getDepartmentId(),
                    calculatePercent(all, matWar.getAvailable()));
            aggregatedList.add(MatWarAggregationDto
                    .builder()
                    .material(toDto(matWar.getMaterial()))
                    .amount(all)
                    .departmentShare(departmentShare)
                    .build());
        });
        return aggregatedList;
    }

    private MatWarSpecification buildMatWarSpecification(MatWarRequestDto requestDto) {
        MatWarSpecification specification = new MatWarSpecification();
        specification.add(buildCriteria("material::materialName", requestDto.getName(), SearchOperation.EQUAL));
        specification.add(buildCriteria("available", String.valueOf(Optional.of(requestDto).map(MatWarRequestDto::getMin).orElse(0)), SearchOperation.GREATER_THAN_EQUAL));
        specification.add(buildCriteria("available", String.valueOf(Optional.of(requestDto).map(MatWarRequestDto::getMax).orElse(Integer.MAX_VALUE)), SearchOperation.LESS_THAN));
        specification.add(buildCriteria("warehouse::departmentId", requestDto.getDepartment(), SearchOperation.IN));
        return specification;
    }

    private SearchCriteria buildCriteria(String key, Object value, SearchOperation operation) {
        return SearchCriteria.builder()
                .key(key)
                .value(value)
                .operation(operation)
                .build();
    }

    private Double calculatePercent(Double all, Double number) {
        return 100 / all * number;
    }

    private MaterialDto toDto(Material material) {
        return MaterialDto.builder()
                .materialName(material.getMaterialName())
                .description(material.getDescription())
                .iconId(material.getIconId())
                .build();
    }
}
