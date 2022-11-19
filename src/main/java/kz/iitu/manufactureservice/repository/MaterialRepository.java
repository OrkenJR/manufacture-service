package kz.iitu.manufactureservice.repository;

import kz.iitu.manufactureservice.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MaterialRepository extends JpaRepository<Material, String>, JpaSpecificationExecutor<Material> {
}
