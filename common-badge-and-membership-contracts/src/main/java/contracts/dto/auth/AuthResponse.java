package contracts.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String token;

}
