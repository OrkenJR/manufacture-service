package kz.iitu.manufactureservice.repository;

import kz.iitu.manufactureservice.model.FactoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FactoryRepository extends JpaRepository<FactoryInfo, String> {
    List<FactoryInfo> findFactoryInfosByDirector(String director);

    Optional<FactoryInfo> findFactoryInfoById(String id);
}
