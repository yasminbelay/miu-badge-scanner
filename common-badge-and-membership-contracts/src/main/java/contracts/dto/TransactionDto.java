package contracts.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
public class TransactionDto implements Serializable {
    private int id;
    private LocalDateTime datetime;
    private BadgeDto badge;
    private MembershipDto membership;
    private LocationDto location;
    private TransactionStatus status;
    private String remark;

}
