package contracts.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Data
@ToString
public class LocationDto implements Serializable {

    private int id;
    private String name;
    private String description;
    private int capacity;
    private LocationType locationType;
    private Collection<Timeslot> timeslots;
}
