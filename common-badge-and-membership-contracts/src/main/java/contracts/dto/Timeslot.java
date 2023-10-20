package contracts.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;

@Data
public class Timeslot implements Serializable {
    private int id;
    private LocalTime startTime;
    private LocalTime endTime;
    private int dayOfWeek;
}
