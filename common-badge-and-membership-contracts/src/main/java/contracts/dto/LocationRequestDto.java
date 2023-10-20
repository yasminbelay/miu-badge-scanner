package contracts.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

@Data
public class LocationRequestDto implements Serializable {
    private int id;
    private String name;
    private String description;
    private int capacity;
    private LocationType locationType;
    private Collection<Timeslot> timeslots;
    private int planId;
}
