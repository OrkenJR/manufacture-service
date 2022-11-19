package kz.iitu.manufactureservice.config;

import kz.iitu.manufactureservice.model.Material;
import kz.iitu.manufactureservice.model.MaterialWarehouse;
import kz.iitu.manufactureservice.model.Warehouse;
import kz.iitu.manufactureservice.repository.MatWarRepository;
import kz.iitu.manufactureservice.repository.MaterialRepository;
import kz.iitu.manufactureservice.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * При запуске заполняет базу, только для теста, надо вынести в dev профиль, если возможно
 *
 * @author Orken
 **/
@Component
@RequiredArgsConstructor
public class SetupDb implements ApplicationListener<ContextRefreshedEvent> {

    private final MaterialRepository materialRepository;
    private final WarehouseRepository warehouseRepository;
    private final MatWarRepository matWarRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        matWarRepository.deleteAll();
        materialRepository.deleteAll();
        warehouseRepository.deleteAll();

        Material sand = Material.builder()
                .materialName("Sand")
                .description("window, and in other staffs")
                .build();
        sand = materialRepository.save(sand);

        Warehouse warehouse = Warehouse.builder()
                .departmentId("1")
                .build();
        warehouse = warehouseRepository.save(warehouse);

        MaterialWarehouse materialWarehouse = MaterialWarehouse.builder()
                .material(sand)
                .warehouse(warehouse)
                .available(1000.6)
                .build();

        materialWarehouse = matWarRepository.save(materialWarehouse);

    }
}
