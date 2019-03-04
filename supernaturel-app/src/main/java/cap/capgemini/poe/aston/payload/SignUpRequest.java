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
//    @Size(min = 4, max = 20)
    private String password;
    
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;
    

    @Size(max = 40)
    private String firstname;
    

    @Size(max = 40)
    private String lastname;
    
    @Size(max = 10)
    private String phone;
    
    private String picture;
    
}
