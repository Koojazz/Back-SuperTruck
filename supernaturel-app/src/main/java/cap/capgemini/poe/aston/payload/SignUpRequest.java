package cap.capgemini.poe.aston.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SignUpRequest {

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;
    
    @NotBlank
    @Size(max = 40)
    private String firstname;
    
    @NotBlank
    @Size(max = 40)
    private String lastname;
    
}
