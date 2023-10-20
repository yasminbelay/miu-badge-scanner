package contracts.dto;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
public class BadgeDto implements Serializable {

    private int id;
    private String badgeUid;
    private MemberDto member;
    private LocalDateTime createdDate;
    private Boolean isActive;
    private LocalDateTime modifiedDate;


}
