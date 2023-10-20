package BadgeAndMembership.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "timeslot")
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "startTime")
    private LocalTime startTime;
    @Column(name = "endTime")
    private LocalTime endTime;
    @Column(name = "dayOfWeek")
    private int dayOfWeek;
}
