package kz.iitu.manufactureservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Сущность для связи материалов со складом
 *
 * @author Orken
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialWarehouse {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    /**
     * Материал
     **/
    @ManyToOne
    @JoinColumn(name = "material_id")
    Material material;

    /**
     * Склад
     **/
    @ManyToOne
    @JoinColumn(name = "warehourse_id")
    Warehouse warehouse;

    /**
     * Доступно в кг
     **/
    private Double available;
}
