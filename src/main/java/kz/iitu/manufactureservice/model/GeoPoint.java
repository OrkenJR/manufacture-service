package kz.iitu.manufactureservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Orken
 * <p>
 * Entity to hold coords
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeoPoint {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private Double longitude;
    private Double latitude;
    @OneToOne(mappedBy = "geoPoint")
    private FactoryInfo factory;
}
