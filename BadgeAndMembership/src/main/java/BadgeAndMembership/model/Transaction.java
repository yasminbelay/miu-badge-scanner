package BadgeAndMembership.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime datetime;
    @ManyToOne
    @JoinColumn(name = "badgeId")
    private Badge badge;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "membershipId")
    private Membership membership;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TransactionStatus status;
    @Column(name = "remark")
    private String remark;

}
