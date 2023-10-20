package contracts.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class PlanRequestDto {
    private String description;
    private  String name;
    private Collection<Role> allowedRoles;
}
