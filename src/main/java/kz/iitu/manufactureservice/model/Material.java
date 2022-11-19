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
 * Сущность для материалов
 *
 * @author Orken
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    /**
     * ID для иконки в file-service
     **/
    private String iconId;

    /**
     * Название материала
     **/
    private String materialName;

    /**
     * Краткое описание/used in
     **/
    private String description;

    @OneToMany(mappedBy = "material")
    private Set<MaterialWarehouse> items;
}
