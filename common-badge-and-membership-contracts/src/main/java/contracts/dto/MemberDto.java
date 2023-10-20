package contracts.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
public class MemberDto implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private Collection<Role> roles;

    public MemberDto(String firstName, String lastName, String emaild) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
