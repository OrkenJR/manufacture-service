package kz.iitu.manufactureservice.model;

import kz.iitu.manufactureservice.model.enums.Country;
import kz.iitu.manufactureservice.model.enums.Icon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Сущность для маркета
 *
 * @author Orken
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarketMaterial {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private Double price;

    @Enumerated(EnumType.STRING)
    private Icon icon;

    @Enumerated(EnumType.STRING)
    private Country country;

}
