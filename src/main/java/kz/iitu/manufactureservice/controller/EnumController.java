package kz.iitu.manufactureservice.controller;

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
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/enum")
public class EnumController {
    @GetMapping("/countries")
    public List<Country> countries() {
        return Arrays.asList(Country.values());
    }

    @GetMapping("/icons")
    public List<Icon> icons() {
        return Arrays.asList(Icon.values());
    }
}
