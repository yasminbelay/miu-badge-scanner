package contracts.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Role implements Serializable {
    private String name;
}
