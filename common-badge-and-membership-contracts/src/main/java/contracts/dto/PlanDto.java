package contracts.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
public class PlanDto implements Serializable {
     private int id;
     private String description;
     private  String name;
     private Collection<Role> allowedRoles;
}
