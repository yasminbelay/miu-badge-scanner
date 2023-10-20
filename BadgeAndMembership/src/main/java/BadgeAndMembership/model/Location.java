package BadgeAndMembership.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "capacity")
    private int capacity;
    @Enumerated(EnumType.STRING)
    @Column(name = "locationType")
    private LocationType locationType;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="locationId")
    private Collection<Timeslot> timeslots;
    @ManyToOne
    @JoinColumn(name="planId")
    private Plan plan;
}
