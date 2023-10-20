package contracts.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class MembershipDto implements Serializable {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private PlanDto plan;
    private MembershipType membershipType;
    private int numberOfAllowanceLimit;
    private LimitResetTimePeriod limitResetTimePeriod;
    private Boolean isSingleEntryPerTimeSlot;
}
