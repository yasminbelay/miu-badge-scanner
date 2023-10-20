package BadgeAndMembership.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name="plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    @ManyToMany
    @JoinTable(name = "PlanAllowedRoles"
            , joinColumns = {@JoinColumn(name = "planId")}
            , inverseJoinColumns = {@JoinColumn(name = "allowedRoleId")})
    private Collection<Role> allowedRoles;
//    @OneToMany
//    @JoinTable(name="PlanLocations"
//            , joinColumns = {@JoinColumn(name = "PlanId")}
//            , inverseJoinColumns = {@JoinColumn(name = "locationId")})
//    private Collection<Location> locations;
}
