package cap.capgemini.poe.aston.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class JwtAuthenticationResponse {

	private String accessToken;
	private final String tokenType= "Bearer";

}
