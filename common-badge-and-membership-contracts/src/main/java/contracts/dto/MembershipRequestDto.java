package contracts.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class MembershipRequestDto implements Serializable {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int planId;
    private int memberId;
    private MembershipType membershipType;
    private int numberOfAllowanceLimit;
    private LimitResetTimePeriod limitResetTimePeriod;
    private Boolean isSingleEntryPerTimeSlot;
}
