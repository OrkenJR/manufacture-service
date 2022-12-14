package kz.iitu.manufactureservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Orken
 * <p>
 * Entity to hold factory info
 **/
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FactoryInfo {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private Integer number;

    private String director;

    private Integer workersCount;

    private Integer departmentCount;

    private Integer year;

    @OneToOne
    @JoinColumn(name = "geo_id", referencedColumnName = "id")
    private GeoPoint geoPoint;

}
