package BadgeAndMembership.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "membership")
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "startDate")
    private LocalDate startDate;
    @Column(name = "endDate")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "planId")
    private Plan plan;
    @ManyToOne
    @JoinColumn(name="memberId")
    private Member member;
    @Enumerated(EnumType.STRING)
    @Column(name = "membershipType")
    private MembershipType membershipType;
    @Column(name = "numberOfAllowanceLimit")
    private int numberOfAllowanceLimit;
    @Enumerated(EnumType.STRING)
    @Column(name = "limitResetTimePeriod")
    private LimitResetTimePeriod limitResetTimePeriod;
    @Column(name = "isSingleEntryPerTimeSlot")
    private Boolean isSingleEntryPerTimeSlot;

    //domain specific methods
    public boolean isMembershipActive(){
        LocalDate currentDate = LocalDate.now();
        if(currentDate.isBefore(startDate)) return false;
        if(currentDate.isAfter(endDate)) return false;
        return true;
    }
}
