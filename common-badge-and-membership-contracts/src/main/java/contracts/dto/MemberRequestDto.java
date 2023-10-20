package contracts.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Data
@ToString
@NoArgsConstructor
public class MemberRequestDto implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Collection<Role> roles;

    public MemberRequestDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
