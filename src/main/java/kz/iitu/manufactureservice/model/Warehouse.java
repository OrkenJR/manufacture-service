package kz.iitu.manufactureservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Сущность для склада департмента
 *
 * @author Orken
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    /**
     * ID для департмента в user-management
     **/
    private String departmentId;

    /**
     * Связь с MaterialWarehouse
     **/
    @OneToMany(mappedBy = "warehouse")
    private Set<MaterialWarehouse> items;
}
