package cap.capgemini.poe.aston.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ApiResponse {

	private Boolean success;
	private String msg;
	
}
