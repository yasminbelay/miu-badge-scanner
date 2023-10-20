package BadgeAndMembership.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "badge")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "badgeUid", unique = true)
    private String badgeUid;
    @ManyToOne
    @JoinColumn(nullable = false, name = "member")
    private Member member;
    @Column(name = "createdDate")
    private LocalDateTime createdDate;
    @Column(name = "isActive")
    private Boolean isActive;
    @Column(name = "modifiedDate")
    private LocalDateTime modifiedDate;
}
