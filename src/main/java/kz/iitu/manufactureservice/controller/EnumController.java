package kz.iitu.manufactureservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.iitu.manufactureservice.model.enums.Country;
import kz.iitu.manufactureservice.model.enums.Icon;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author Orken
 * <p>
 * Don't forget to wrap with ResponseEntity
 **/
@Tag(name = "Enum", description = "The ENUM API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/enum")
public class EnumController {

    @Operation(summary = "Getting all countries")
    @GetMapping("/countries")
    public List<Country> countries() {
        return Arrays.asList(Country.values());
    }

    @Operation(summary = "Getting all icons")
    @GetMapping("/icons")
    public List<Icon> icons() {
        return Arrays.asList(Icon.values());
    }
}
