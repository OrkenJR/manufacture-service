package kz.iitu.manufactureservice.repository;

import kz.iitu.manufactureservice.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, String> {
}
