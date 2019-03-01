package cap.capgemini.poe.aston.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class LoginRequest {

	@NotBlank
	@Size(max = 40)
	private String email;
	
	@NotBlank
	@Size(min = 4, max = 20)
	private String password;
	
}
