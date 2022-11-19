package kz.iitu.manufactureservice.repository;

import kz.iitu.manufactureservice.model.Material;
import kz.iitu.manufactureservice.model.MaterialWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MatWarRepository extends JpaRepository<MaterialWarehouse, String>, JpaSpecificationExecutor<MaterialWarehouse> {

    List<MaterialWarehouse> findAllByMaterialId(String id);

}
