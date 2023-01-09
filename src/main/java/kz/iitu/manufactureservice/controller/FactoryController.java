package kz.iitu.manufactureservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.iitu.cfaslib.controller.AbstractWrapper;
import kz.iitu.cfaslib.dto.manufacture.FactoryInfoDto;
import kz.iitu.cfaslib.dto.manufacture.LightFactoryInfoDto;
import kz.iitu.manufactureservice.model.FactoryInfo;
import kz.iitu.manufactureservice.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Orken
 * <p>
 * Don't forget to wrap with ResponseEntity
 **/
@Tag(name = "Factory", description = "The Factory API")
@RestController("/factory")
@RequiredArgsConstructor
public class FactoryController extends AbstractWrapper {

    private final FactoryService factoryService;

    @Operation(summary = "Getting list of factories")
    @GetMapping()
    public List<FactoryInfo> getAll() {
        return factoryService.findAll();
    }

    @Operation(summary = "Getting list of factories by director's username")
    @GetMapping("/byDirector")
    public List<FactoryInfo> filtered(@RequestParam String director) {
        return factoryService.factoriesByDirector(director);
    }

    @Operation(summary = "Creating a new factory")
    @PostMapping
    public FactoryInfo save(@RequestBody FactoryInfoDto dto) {
        return factoryService.save(dto);
    }

    @Operation(summary = "Updating a factory by id")
    @PutMapping("/{id}")
    public FactoryInfo save(@PathVariable String id, @RequestBody FactoryInfoDto dto) {
        return factoryService.update(id, dto);
    }

    @Operation(summary = "Getting list of factories by director's username")
    @DeleteMapping("/{id}")
    public String save(@PathVariable String id) {
        factoryService.deleteById(id);
        return "ok";
    }

    @Operation(summary = "Getting list of factories by director's username")
    @GetMapping("/light-factory")
    public List<LightFactoryInfoDto> lightFactoryGetAll() {
        return factoryService.map(factoryService.findAll());
    }

    @Operation(summary = "Getting list of factories by director's username")
    @GetMapping("/light-factory/byDirector")
    public List<LightFactoryInfoDto> lightFactoryByDirector(@RequestParam String director) {
        return factoryService.map(factoryService.factoriesByDirector(director));
    }

}
