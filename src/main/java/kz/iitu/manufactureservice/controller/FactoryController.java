package kz.iitu.manufactureservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.iitu.manufactureservice.model.FactoryInfo;
import kz.iitu.manufactureservice.service.FactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Orken
 * <p>
 * Don't forget to wrap with ResponseEntity
 **/
@Tag(name = "Factory", description = "The Factory API")
@RestController
@RequiredArgsConstructor
public class FactoryController {

    private final FactoryService factoryService;

    @Operation(summary = "Getting list of factories by director's username")
    @GetMapping("/byDirector")
    public List<FactoryInfo> filtered(@RequestParam String director) {
        return factoryService.factoriesByDirector(director);
    }

}
