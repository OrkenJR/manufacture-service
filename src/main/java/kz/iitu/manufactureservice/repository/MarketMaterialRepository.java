package kz.iitu.manufactureservice.repository;

import kz.iitu.manufactureservice.model.MarketMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MarketMaterialRepository extends JpaRepository<MarketMaterial, String>, JpaSpecificationExecutor<MarketMaterial> {
    Optional<MarketMaterial> findMarketMaterialById(String s);
}
