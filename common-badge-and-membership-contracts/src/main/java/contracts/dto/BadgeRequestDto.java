package contracts.dto;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
public class BadgeRequestDto implements Serializable {
    private int id;
    private String badgeUid;
    private int memberId;
    private Boolean isActive;
}
